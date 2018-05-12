package com.example.pc.andiamo;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Patterns;
import android.graphics.Color;

public class LoginRegister extends DialogFragment implements View.OnClickListener {
    //UI Variables login_register_dialog.xml
    private EditText eTxtUsername, eTxtPassword, eTxtFirstName, eTxtLastName, eTxtStreet, eTxtApt,
            eTxtCity, eTxtState, eTxtZip, eTxtEmail, eTxtConfirmEmail, eTxtCreatePassword,
            eTxtConfirmPassword;

    private Button btnLogin, btnCreateAccount;

    private TextView txtLoginStatus, txtSection1, txtSection2, txtSection3;

    //UI variables user_info.xml
    private TextView txtName, txtEmail, txtAddress;

    //Data Variables
    private String username, password, firstName, lastName, street, apt, city, state, zip, email,
            confirmEmail, createPassword, confirmPassword;

    private boolean loggedIn;
    private String name, address;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //  NEED TO IMPLEMENT: https://stackoverflow.com/questions/42042248/how-to-pass-data-from-activity-to-dialogfragment
        //      Get the bundle, check if logged in --> if not, then do what is currently setup
        //          if they are logged in, then need to open up a different layout displaying the user
        //          information and a logout button?

        Bundle bundle = getArguments();
        loggedIn = bundle.getBoolean("LOGGED_IN");
        if(!loggedIn) {
            View view = inflater.inflate(R.layout.login_register_dialog, container, false);
            initializeUI(view, loggedIn);
            return view;
        }
        else {
            View view = inflater.inflate(R.layout.user_info, container, false);
            initializeUI(view, loggedIn);
            name = bundle.getString("NAME");
            email = bundle.getString("EMAIL");
            address = bundle.getString("ADDRESS");
            return view;
        }
    }

    //initializeUI
    //  pass the view, and initialize the UI
    //      NOTE: view, as an object, should be passed by reference, but it's possible I could be
    //          wrong. If there is an issue then initialize the UI in onCreateView
    private void initializeUI(View view, boolean loggedIn) {
        if(!loggedIn) {
            txtLoginStatus = view.findViewById(R.id.txt_loginStatus);
            txtSection1 = view.findViewById(R.id.txt_section1);
            txtSection2 = view.findViewById(R.id.txt_section2);
            txtSection3 = view.findViewById(R.id.txt_section3);

            eTxtUsername = view.findViewById(R.id.eTxt_username);
            eTxtPassword = view.findViewById(R.id.eTxt_password);

            eTxtFirstName = view.findViewById(R.id.eTxt_firstName);
            eTxtLastName = view.findViewById(R.id.eTxt_lastName);
            eTxtStreet = view.findViewById(R.id.eTxt_streetAddress);
            eTxtApt = view.findViewById(R.id.eTxt_aptNumber);
            eTxtCity = view.findViewById(R.id.eTxt_city);
            eTxtState = view.findViewById(R.id.eTxt_state);
            eTxtZip = view.findViewById(R.id.eTxt_zip);

            eTxtEmail = view.findViewById(R.id.eTxt_email);
            eTxtConfirmEmail = view.findViewById(R.id.eTxt_confirmEmail);

            eTxtCreatePassword = view.findViewById(R.id.eTxt_passwordCreate);
            eTxtConfirmPassword = view.findViewById(R.id.eTxt_confirmPasswordCreate);

            btnLogin = view.findViewById(R.id.btn_login);
            btnCreateAccount = view.findViewById(R.id.btn_createAccount);
        }
        else {
            txtName = view.findViewById(R.id.txt_name);
            txtEmail = view.findViewById(R.id.txt_email);
            txtAddress = view.findViewById(R.id.txt_address);

            setText();
        }
    }

    //setText
    //  sets the TextViews in user_info.xml to the appropriate values
    private void setText() {
        txtName.setText(name);
        txtEmail.setText(email);
        txtAddress.setText(address);
    }

    @Override
    public void onResume() {
        super.onResume();

        //sets the dialogFragment size to the size of the device screen
        //  it is possible for getAttributes to throw a NullPointerException, so we do this in a
        //  try/catch block
        try {
            ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        }
        catch (NullPointerException e) {
            Log.d("NULLPOINTEREXCEPTION", "LoginRegister failed to execute:\n" +
                    "ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();");
        }

        //  NEED TO IMPLEMENT: pass a boolean, loggedIn, into setListeners (true/false)
        //      true: listener on a logout button
        //      false: currentSetup
        if(!loggedIn) {
            setListeners();
        }
    }

    //setListeners
    //  sets onClickListeners on btnLogin and btnCreateAccount
    private void setListeners() {
        btnLogin.setOnClickListener(this);
        btnCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                startLogin();
                break;
            case R.id.btn_createAccount:
                startCreateAccount();
                break;
        }
    }

    //startLogin
    //  begins the login process
    private void startLogin() {
        int RETURN_CODE = getLoginInfo();
        switch (RETURN_CODE) {
            case 0:
                ((comms)getActivity()).loginMethod(username, password);
                dismiss();
                break;
            case 1:
                invalidLoginInput();
                break;
        }
    }

    //getLoginInfo
    //  gets the login info from eTxtUsername and eTxtPassword
    //  RETURN CODES:
    //      | 0 = VALID INPUT | 1 = INVALID INPUT |
    private int getLoginInfo() {
        int validInput;

        username = eTxtUsername.getText().toString();
        password = eTxtPassword.getText().toString();

        //sanitize input
        if(username.length() != 0) {
            boolean validEmail = Patterns.EMAIL_ADDRESS.matcher(username).matches();

            if(validEmail) {
                if(password.length() >= Constants.PASS_LENGTH_MIN &&
                        password.length() <= Constants.PASS_LENGTH_MAX &&
                        password.matches("[A-Za-z0-9]+")) {
                    validInput = 0;
                }
                else {
                    validInput = 1;
                }
            }
            else {
                validInput = 1;
            }
        }
        else {
            validInput = 1;
        }

        return validInput;
    }

    //invalidLoginInput
    //  displays to the user, that their input is invalid
    private void invalidLoginInput() {
        txtLoginStatus.post(new Runnable() {
            @Override
            public void run() {
                txtLoginStatus.setText(R.string.invalid_input1);
                txtLoginStatus.setTextColor(Color.RED);
            }
        });
        txtLoginStatus.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtLoginStatus.setText(R.string.invalid_input2);
            }
        }, 2500);
        txtLoginStatus.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtLoginStatus.setText(R.string.not_logged_in);
                txtLoginStatus.setTextColor(Color.BLACK);
            }
        }, 5000);
    }

    //startCreateAccount
    //  begins the creation of the account
    private void startCreateAccount() {
        int RETURN_CODE = getRegisterInfo();
        switch (RETURN_CODE) {
            case 0:
                ((comms)getActivity()).registerMethod(firstName, lastName, street, apt, city,
                        state, zip, email, createPassword);
                dismiss();
                break;
            case 1:
                section1Invalid();
                break;
            case 2:
                section2Invalid();
                break;
            case 3:
                section3Invalid();
                break;
        }
    }

    //getRegisterInfo
    //  gets the users info from the register section
    //  RETURN CODES:
    //      | 0 = VALID INPUT | 1 = SECTION 1 INVALID | 2 = EMAIL INVALID |
    //      | 3 = PASSWORD INVALID |
    private int getRegisterInfo() {
        int RETURN_CODE = 0;

        firstName = eTxtFirstName.getText().toString();
        lastName = eTxtLastName.getText().toString();
        street = eTxtStreet.getText().toString();
        apt = eTxtApt.getText().toString();
        city = eTxtCity.getText().toString();
        state = eTxtState.getText().toString();
        zip = eTxtZip.getText().toString();
        if(firstName.length() == 0 || lastName.length() == 0 || street.length() == 0 ||
                city.length() == 0 || state.length() == 0 || zip.length() == 0) {
            return 1;
        }

        email = eTxtEmail.getText().toString();
        confirmEmail = eTxtConfirmEmail.getText().toString();
        if(email.length() == 0 || confirmEmail.length() == 0 || !email.matches(confirmEmail)) {
            return 2;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return 2;
        }

        createPassword = eTxtCreatePassword.getText().toString();
        confirmPassword = eTxtConfirmPassword.getText().toString();
        if(createPassword.length() < Constants.PASS_LENGTH_MIN ||
                createPassword.length() > Constants.PASS_LENGTH_MAX ||
                confirmPassword.length() < Constants.PASS_LENGTH_MIN ||
                confirmPassword.length() > Constants.PASS_LENGTH_MAX ||
                !createPassword.matches(confirmPassword)) {
            return 3;
        }

        return RETURN_CODE;
    }

    private void section1Invalid() {
        txtSection1.post(new Runnable() {
            @Override
            public void run() {
                txtSection1.setText(R.string.section1Invalid);
                txtSection1.setTextColor(Color.RED);
            }
        });
        txtSection1.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtSection1.setText(R.string.register_info);
                txtSection1.setTextColor(Color.BLACK);
            }
        }, 3000);
    }

    private void section2Invalid() {
        txtSection2.post(new Runnable() {
            @Override
            public void run() {
                txtSection2.setText(R.string.section2Invalid);
                txtSection2.setTextColor(Color.RED);
            }
        });
        txtSection2.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtSection2.setText(R.string.enter_email);
                txtSection2.setTextColor(Color.BLACK);
            }
        }, 3000);
    }

    private void section3Invalid() {
        txtSection3.post(new Runnable() {
            @Override
            public void run() {
                txtSection3.setText(R.string.invalid_input2);
                txtSection3.setTextColor(Color.RED);
            }
        });
        txtSection3.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtSection3.setText(R.string.create_a_pass);
                txtSection3.setTextColor(Color.BLACK);
            }
        }, 3000);
    }

    @Override
    public void onPause() {
        super.onPause();

        //destroy listeners
        btnLogin.setOnClickListener(null);
        btnCreateAccount.setOnClickListener(null);
    }

    //INTERFACE TO WORK WITH ACTIVITY
    public interface comms {
        void loginMethod(String username_, String password_);
        void registerMethod(String firstName_, String lastName_, String street_, String apt_,
                            String city_, String state_, String zip_, String email_,
                            String password_);
    }

}
