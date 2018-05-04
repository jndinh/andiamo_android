package com.example.pc.andiamo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtHome, txtMenu, txtCart, txtLocator, txtTracker;
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
    //      Binds the TextView variables txtHome, txtMenu, txtCart, txtLocator, txtTracker, and
    //          btnAccount to the appropriate UI element
    private void initializeUI() {
        txtHome = (TextView) findViewById(R.id.txt_home);
        txtMenu = (TextView) findViewById(R.id.txt_menu);
        txtCart = (TextView) findViewById(R.id.txt_cart);
        txtLocator = (TextView) findViewById(R.id.txt_store_locator);
        txtTracker = (TextView) findViewById(R.id.txt_delivery_tracker);
        btnAccount = (ImageButton) findViewById(R.id.btn_account);
    }

    //  setListeners()
    //      Sets onClickListeners to each of the variables txtHome, txtMenu, txtCart, txtLocator,
    //          txtTracker, and btnAccount
    private void setListeners() {
        txtHome.setOnClickListener(this);
        txtMenu.setOnClickListener(this);
        txtCart.setOnClickListener(this);
        txtLocator.setOnClickListener(this);
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
        MenuFragment menuFragment = new MenuFragment();
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

    private void fragmentLocator() {
        Log.d("my_ fragmentLocator", "Entered Fragment Locator");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LocatorFragment locatorFragment = new LocatorFragment();
        fragmentTransaction.replace(R.id.fragment_container, locatorFragment);
        fragmentTransaction.commit();

        currentFragment = "LOCATOR";
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

    //--------------------------------End Fragment Switch Functions--------------------------------

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
            case R.id.txt_menu:
                if(!currentFragment.equals("MENU")) {
                    fragmentMenu();
                }
                break;
            case R.id.txt_cart:
                if(!currentFragment.equals("CART")) {
                    fragmentCart();
                }
                break;
            case R.id.txt_store_locator:
                if(!currentFragment.equals("LOCATOR")) {
                    fragmentLocator();
                }
                break;
            case R.id.txt_delivery_tracker:
                if(!currentFragment.equals("TRACKER")) {
                    fragmentTracker();
                }
                break;
            case R.id.btn_account:
                //implement btn
                break;
        }
    }

    //--------------------------------End onClick Listener Functions--------------------------------
}
