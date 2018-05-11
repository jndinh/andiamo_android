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

public class LoginRegister extends DialogFragment implements View.OnClickListener {

    //UI Variables
    EditText eTxtUsername, eTxtPassword, eTxtFirstName, eTxtLastName, eTxtStreet, eTxtApt,
            eTxtCity, eTxtState, eTxtZip, eTxtEmail, eTxtConfirmEmail, eTxtCreatePassword,
            eTxtConfirmPassword;

    TextView txtName, txtEmail, txtAddress;

    Button btnLogin, btnCreateAccount;

    //Data Variables
    String username, password, firstName, lastName, street, apt, city, state, zip, email,
            confirmEmail, createPassword, confirmPassword;

    boolean loggedIn;
    String name, address;


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
        getLoginInfo();
    }

    private void getLoginInfo() {
        boolean validInput = false;

        username = eTxtUsername.getText().toString();
        password = eTxtPassword.getText().toString();

        Log.d("dialogInterface", "\n\nUsername: " + username +
                "\nPassword: " + password);
    }

    //startCreateAccount
    //  begins the creation of the account
    private void startCreateAccount() {
        getRegisterInfo();
    }

    private void getRegisterInfo() {
        boolean validInput = false;
    }

    @Override
    public void onPause() {
        super.onPause();

        //destroy listeners
        btnLogin.setOnClickListener(null);
        btnCreateAccount.setOnClickListener(null);
    }
}
