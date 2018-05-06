package com.example.pc.andiamo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import static com.example.pc.andiamo.Constants.DES_OFFSET;
import static com.example.pc.andiamo.Constants.MenuItem;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, DessertDrinkMenuFragment.DessertMenuListener {
    int[] totalCart = new int[29];
    TextView txtHome, txtCart, txtTracker;
    Spinner spinner;
    ImageButton btnAccount;
    String currentFragment;

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
        spinner = (Spinner) findViewById(R.id.spinner_menu);

        // Initializing a String Array
        String[] menu = new String[]{
                "MENU",
                "PIZZA",
                "SANDWICHES",
                "DESSERTS & DRINKS",
        };

        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, menu);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }

    //  setListeners()
    //      Sets onClickListeners to each of the variables txtHome, txtMenu, txtCart,
    //          txtTracker, and btnAccount
    private void setListeners() {
        spinner.setOnItemSelectedListener(this);
        txtHome.setOnClickListener(this);
        txtCart.setOnClickListener(this);
        txtTracker.setOnClickListener(this);
        btnAccount.setOnClickListener(this);
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

        currentFragment = "DESSERT & DRINKS";
    }

    private void fragmentPizza() {
        Log.d("my_ fragmentPizza", "Entered Fragment Pizza");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PizzaMenuFragment pizzaFragment = new PizzaMenuFragment();
        fragmentTransaction.replace(R.id.fragment_container, pizzaFragment);
        fragmentTransaction.commit();

        currentFragment = "PIZZA";
    }

    //--------------------------------End Fragment Switch Functions--------------------------------

    //--------------------------------Start Login/Register Functions--------------------------------

    private void loginRegister() {

    }

    //---------------------------------End Login/Register Functions---------------------------------

    //---------------------------------Start Cart Transactions---------------------------------





    //---------------------------------End Cart Transactions---------------------------------

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
                    fragmentCart();
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
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedItem = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), "Selected" + selectedItem + " " + Integer.toString(i), Toast.LENGTH_SHORT).show();

        if (Objects.equals(selectedItem, "PIZZA")) {
            fragmentPizza();
        } else if (Objects.equals(selectedItem, "SANDWICHES")) {

        } else if (Objects.equals(selectedItem, "DESSERTS & DRINKS")) {
            fragmentDessertDrink();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void getDesCart(int[] cart) {
        if (cart.length > 0) {
            // Add to total cart
            for(int i = 0; i < cart.length; i++) {
                totalCart[i + DES_OFFSET] = cart[i];
                Log.d("cart", MenuItem.values()[i + DES_OFFSET].getName() + " " + Integer.toString(cart[i]));
            }
        }
    }

    //--------------------------------End onClick Listener Functions--------------------------------
}
