package com.example.pc.andiamo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by jlagnese on 5/13/2018.
 */

public class CartFragment extends DialogFragment implements CartItemFragment.ItemToCartComm{

    CartComm mainActivity;

    private int[] cartItems;
    private String initSpecialRequest;
    private float initTotal;
    private Button addMore;
    private Button checkout;
    private EditText specialRequests;
    private TextView totalView;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mainActivity = (CartComm) context;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //  NEED TO IMPLEMENT: https://stackoverflow.com/questions/42042248/how-to-pass-data-from-activity-to-dialogfragment
        //      Get the bundle, check if logged in --> if not, then do what is currently setup
        //          if they are logged in, then need to open up a different layout displaying the user
        //          information and a logout button?

        Bundle bundle = getArguments();
        cartItems = bundle.getIntArray("CART");
        initSpecialRequest = bundle.getString("SPECIAL");
        initTotal = bundle.getFloat("TOTAL");
        View view = inflater.inflate(R.layout.cart_fragment, container, false);
        addCartItems();
        totalView = (TextView) view.findViewById(R.id.total_cost);
        updateTotal(initTotal);
        specialRequests = (EditText) view.findViewById(R.id.cart_special_requests);
        specialRequests.setText(initSpecialRequest);
        addMore = (Button) view.findViewById(R.id.addmore_button);
        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view != null) {
                    InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (inputManager != null) {
                        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
                dismiss();
                mainActivity.updateSpecialRequests(specialRequests.getText().toString());
                mainActivity.returnToMenu();
            }
        });
        checkout = (Button) view.findViewById(R.id.checkout_button);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view != null) {
                    InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (inputManager != null) {
                        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
                if(mainActivity.handleCheckout()) dismiss();
            }
        });

        return view;
    }

    private void addCartItems(){
        for (int i = 0; i < cartItems.length; i++){
            if(cartItems[i] > 0) {
                Fragment newItem = new CartItemFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("INDEX", i);
                bundle.putInt("QUANTITY", cartItems[i]);
                newItem.setArguments(bundle);
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.cart_list, newItem, "item" + i);
                transaction.commit();
            }
        }
    }

    public interface CartComm{
        void returnToMenu();
        boolean handleCheckout(); // returns true if checkout occurred
        void updateSpecialRequests(String newText);
    }

    public void updateTotal(float total){
        totalView.setText("$" + String.format("%.2f", total));
    }
}
