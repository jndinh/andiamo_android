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

public class LoginRegister extends DialogFragment {

    //UI Variables
    EditText eTxtUsername, eTxtPassword, eTxtFirstName, eTxtLastName, eTxtStreet, eTxtApt,
            eTxtCity, eTxtState, eTxtZip, eTxtEmail, eTxtConfirmEmail, eTxtCreatePassword,
            eTxtConfirmPassword;
    Button btnLogin, btnCreateAccount;

    //Data Variables
    String username, password, firstName, lastName, street, apt, city, state, zip, email,
            confirmEmail, createPassword, confirmPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_register_dialog, container, false);
        return view;
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
    }
}
