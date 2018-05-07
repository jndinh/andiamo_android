package com.example.pc.andiamo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, PizzaMenuFragment.AddtoCart, DessertDrinkMenuFragment.AddtoCart {

    TextView txtHome, txtCart, txtTracker, txtMenu;
    ImageButton btnAccount;
    String currentFragment;

    int masterCart[] = new int[29];

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

    //--------------------------------End Fragment Switch Functions--------------------------------

    //--------------------------------Start Login/Register Functions--------------------------------

    private void loginRegister() {

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
                        break;
                    case R.id.subChoice:
                        //fragmentSubs();
                        break;
                    case R.id.dndChoice:
                        fragmentDessertDrink();
                        break;
                }
                return true;
            }
        });

        popupMenu.show();
    }

    @Override
    public void getQuanities(int[] cart, int offset) {
        if (cart.length > 0) {
            for(int i=0;i<cart.length;i++){
                masterCart[i+offset] += cart[i];
                Log.d("cart", Constants.MenuItem.values()[i + offset].name() + " " + Integer.toString(cart[i]));
            }
        }

    }
}
