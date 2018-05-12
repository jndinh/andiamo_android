package com.example.pc.andiamo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.os.AsyncTask;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import static com.example.pc.andiamo.Constants.AUTHORIZATION_HEADER;
import static com.example.pc.andiamo.Constants.REGISTER_EP;
import static com.example.pc.andiamo.Constants.LOGIN_EP;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PizzaMenuFragment.AddtoCart, DessertDrinkMenuFragment.AddtoCart, SandwichMenuFragment.AddtoCart {

    TextView txtHome, txtCart, txtTracker, txtMenu;
    Button addMore;
    ImageButton btnAccount;
    String currentFragment;

    int itemCount = 0;
    int lastMenuChoice = 0; // 0 = pizza ; 1 = subs ; 2 = desserts/drinks
    int masterCart[] = new int[29];
    String userSpecialRequests = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUI();
        setListeners();
        fragmentHome();
    }

    //--------------------------------Start Initialization Functions--------------------------------

    //  initializeUI()
    //      Binds the TextView variables txtHome, txtMenu, txtCart, txtTracker, and
    //          btnAccount to the appropriate UI element
    private void initializeUI() {
        txtHome = (TextView) findViewById(R.id.txt_home);
        txtCart = (TextView) findViewById(R.id.txt_cart);
        txtTracker = (TextView) findViewById(R.id.txt_delivery_tracker);
        btnAccount = (ImageButton) findViewById(R.id.btn_account);
        txtMenu = (TextView) findViewById(R.id.txt_menu);
    }

    //  setListeners()
    //      Sets onClickListeners to each of the variables txtHome, txtMenu, txtCart,
    //          txtTracker, and btnAccount
    private void setListeners() {
        txtHome.setOnClickListener(this);
        txtCart.setOnClickListener(this);
        txtTracker.setOnClickListener(this);
        btnAccount.setOnClickListener(this);
        txtMenu.setOnClickListener(this);
    }

    //---------------------------------End Initialization Functions---------------------------------

    //-------------------------------Start Fragment Switch Functions-------------------------------

    private void fragmentHome() {
        Log.d("my_ fragmentHome", "Entered Fragment Home");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
        fragmentTransaction.commit();

        currentFragment = "HOME";
    }

    private void fragmentMenu() {
        Log.d("my_ fragmentMenu", "Entered Fragment Menu");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PizzaMenuFragment menuFragment = new PizzaMenuFragment();
        fragmentTransaction.replace(R.id.fragment_container, menuFragment);
        fragmentTransaction.commit();

        currentFragment = "MENU";
    }

    private void fragmentCart() {
        Log.d("my_ fragmentCart", "Entered Fragment Cart");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CartFragment cartFragment = new CartFragment();
        fragmentTransaction.replace(R.id.fragment_container, cartFragment);
        fragmentTransaction.commit();

        currentFragment = "CART";
    }

    private void fragmentTracker() {
        Log.d("my_ fragmentTracker", "Entered Fragment Tracker");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TrackerFragment trackerFragment = new TrackerFragment();
        fragmentTransaction.replace(R.id.fragment_container, trackerFragment);
        fragmentTransaction.commit();

        currentFragment = "TRACKER";
    }

    private void fragmentDessertDrink() {
        Log.d("my_ fragmentDnD", "Entered Fragment Desserts & Drinks");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DessertDrinkMenuFragment dessertFragment = new DessertDrinkMenuFragment();
        fragmentTransaction.replace(R.id.fragment_container, dessertFragment);
        fragmentTransaction.commit();

        currentFragment = "DESSERT";
    }

    private void fragmentPizza() {
        Log.d("my_ fragmentPizza", "Entered Fragment Pizza");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PizzaMenuFragment pizzaFragment = new PizzaMenuFragment();
        fragmentTransaction.replace(R.id.fragment_container, pizzaFragment, "pizza");
        fragmentTransaction.commit();

        currentFragment = "PIZZA";
    }

    private void fragmentSubs(){
        Log.d("my_ fragmentSubs", "Entered Fragment Subs");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SandwichMenuFragment subsFragment = new SandwichMenuFragment();
        fragmentTransaction.replace(R.id.fragment_container, subsFragment, "subs");
        fragmentTransaction.commit();

        currentFragment = "SUBS";
    }

    //--------------------------------End Fragment Switch Functions--------------------------------

    //--------------------------------Start Login/Register Functions--------------------------------

    //loginRegister()
    //  create a dialogFragment that showcases the LoginRegister Dialog
    private void loginRegister() {
        FragmentManager fragmentManager = getFragmentManager();
        LoginRegister loginRegisterDialog = new LoginRegister();
        loginRegisterDialog.show(fragmentManager, "login_register");
    }

    //---------------------------------End Login/Register Functions---------------------------------

    //-------------------------------Start onClick Listener Functions-------------------------------

    //  onClick
    //      Implementation of View.onClickListener
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_home:
                if(!currentFragment.equals("HOME")) {
                    fragmentHome();
                }
                break;
            case R.id.txt_cart:
                if(!currentFragment.equals("CART")) {
                   // fragmentCart();
                    final PopupWindow pw;
                    Button addMore;
                    Button checkout;
                    try {
                        // We need to get the instance of the LayoutInflater
                        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        final View layout = inflater.inflate(R.layout.cart_popup,
                                (ViewGroup) findViewById(R.id.cart_shell));
                        pw = new PopupWindow(layout, 800, 1000, true);
                        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
                        addMore = (Button) layout.findViewById(R.id.addmore_button);
                        addMore.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                pw.dismiss();
                                if (lastMenuChoice == 0)fragmentPizza();
                                else if (lastMenuChoice == 1)fragmentSubs();
                                else fragmentDessertDrink();
                            }
                        });
                        checkout = (Button) layout.findViewById(R.id.checkout_button);
                        checkout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                pw.dismiss();
                                Toast.makeText(getApplicationContext(), "Your order was placed!", Toast.LENGTH_LONG).show();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.txt_delivery_tracker:
                if(!currentFragment.equals("TRACKER")) {
                    fragmentTracker();
                }
                break;
            case R.id.btn_account:
                loginRegister();
                break;
            case R.id.txt_menu:
                runPopupMenu();
                break;
        }
    }

    //--------------------------------End onClick Listener Functions--------------------------------

    public void runPopupMenu(){
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, txtMenu);
        popupMenu.getMenuInflater().inflate(R.menu.menu_choices, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.pizzaChoice:
                        fragmentPizza();
                        lastMenuChoice = 0;
                        break;
                    case R.id.subChoice:
                        fragmentSubs();
                        lastMenuChoice = 1;
                        break;
                    case R.id.dndChoice:
                        fragmentDessertDrink();
                        lastMenuChoice = 2;
                        break;
                }
                return true;
            }
        });

        popupMenu.show();
    }

    @Override
    public void getQuantities(int[] cart, int offset, String requests) {
        if (cart.length > 0) {
            userSpecialRequests += "\n" + requests;
            Log.d("requests", userSpecialRequests);
            for(int i=0;i<cart.length;i++){
                masterCart[i+offset] += cart[i];
                Log.d("cart", Constants.MenuItem.values()[i + offset].getName() + " " + Integer.toString(cart[i]));
            }
        }
    }

    
    //---------------------------------WEB ASYNC SERVICES---------------------------------
    /**
     * To use this, simply call: new Register(fname, lname, email, password, street_address, city, state, zip_code).execute();
     * or if the user has a line number: new Register(fname, lname, email, password, street_address, city, state, zip_code, line_number).execute()
     */
    private class Register extends AsyncTask<Void, Void, Boolean>
    {
        String fname, lname, email, password, street_address, city, state, zip_code, line_number;
        JSONObject responseData;

        Register(String fname, String lname, String email, String password, String street_address, String city, String state, String zip_code) {
            this.fname = fname;
            this.lname = lname;
            this.email = email;
            this.password = password;
            this.street_address = street_address;
            this.city = city;
            this.state = state;
            this.zip_code = zip_code;
        }

        Register(String fname, String lname, String email, String password, String street_address, String city, String state, String zip_code, String line_number) {
            this.fname = fname;
            this.lname = lname;
            this.email = email;
            this.password = password;
            this.street_address = street_address;
            this.city = city;
            this.state = state;
            this.zip_code = zip_code;
            this.line_number = line_number;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //this method will be running on background thread so don't update UI from here
            //do your long running http tasks here,you don't want to pass argument and u can access the parent class' variable url over here

            try {
                // building json...
                JSONObject data = new JSONObject();
                data.put("fname", fname);
                data.put("lname", lname);
                data.put("email", email);
                data.put("password", password);
                data.put("street_address", street_address);
                data.put("city", city);
                data.put("state", state);
                data.put("zip_code", zip_code);

                // optional parameter
                if (line_number != null)
                    data.put("line_number", line_number);

                // building post request
                OkHttpClient client = new OkHttpClient();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, data.toString());
                Request request = new Request.Builder()
                        .url(REGISTER_EP)
                        .post(body)
                        .addHeader("AUTHORIZATION", AUTHORIZATION_HEADER)
                        .addHeader("Content-Type", "application/json")
                        .build();

                // parsing response...
                Response response = client.newCall(request).execute();
                if (response.body() == null) return false;
                String strResponse = response.body().string();
                final int code = response.code();
                responseData = new JSONObject(strResponse);

                Log.d("webtag", strResponse);

                if (code == 200) {
                    return true;
                }

                return false;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if (result) {
                Toast.makeText(getApplicationContext(), "Successfully registered.", Toast.LENGTH_SHORT).show();

            } else {
                try {
                    // something went wrong
                    String data = (String) responseData.get("data");
                    Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * To use this, simply call: new Login(email, password).execute();
     */
    private class Login extends AsyncTask<Void, Void, Boolean>
    {
        String email;
        String password;

        Login(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //this method will be running on background thread so don't update UI from here
            //do your long running http tasks here,you don't want to pass argument and u can access the parent class' variable url over here

            OkHttpClient client = new OkHttpClient();
            String query = "email=" + email + "&password=" + password;

            // building get request
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, query);
            Request request = new Request.Builder()
                    .url(LOGIN_EP)
                    .post(body)
                    .addHeader("AUTHORIZATION", AUTHORIZATION_HEADER)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            // parsing response
            try {
                Response response = client.newCall(request).execute();
                if (response.body() == null) return false;
                String strResponse = response.body().string();
                final int code = response.code();

                Log.d("webtag", strResponse);

                if (code == 200) {
                    return true;
                }

                return false;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if (result) {
                Toast.makeText(getApplicationContext(), "Logged in.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Invalid email/password.", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
