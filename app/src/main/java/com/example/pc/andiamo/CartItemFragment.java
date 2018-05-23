package com.example.pc.andiamo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartItemFragment extends Fragment{

    private CartItemComm mainActivity;
    private ItemToCartComm cart;

    private final String editTag = "PROG_EDIT";

    private ImageButton increment;
    private ImageButton decrement;
    private ImageButton removeButton;
    private EditText quantityView;
    private TextView titleView;
    private TextView costView;

    private int index;
    private String title;
    private int quantity;
    private float cost;

    // used to catch scenario where user deletes item quantity
    private TextWatcher textWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
            if(s.toString().trim().equals("")){
                quantityView.setText("0");
            }
            else{
                // user edited quantity, update new values
                if(quantityView.getTag() == null) {
                    // set user input to 0 if < 0, and cap it at 50 if > 0
                    int inputVal = Integer.parseInt(s.toString());
                    updateViews((inputVal >= 0 ? (inputVal <= 50 ? inputVal : 50) : 0));
                }
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }
    };

    public CartItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        index = args.getInt("INDEX", 0);
        quantity = args.getInt("QUANTITY", 0);
        title = Constants.MenuItem.values()[index].getName();
        cost = Constants.MenuItem.values()[index].getPrice();
    }

    // used for making the numbers visible in the quantity
    private class NumericKeyBoardTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return source;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_cart_item, container, false);
        titleView = (TextView) myView.findViewById(R.id.item_title);
        quantityView = (EditText) myView.findViewById(R.id.item_quantity);
        quantityView.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        costView = (TextView) myView.findViewById(R.id.item_cost);
        increment = (ImageButton) myView.findViewById(R.id.increment_item);
        decrement = (ImageButton) myView.findViewById(R.id.decrement_item);
        removeButton = (ImageButton) myView.findViewById(R.id.remove_item_button);
        titleView.setText(title);
        quantityView.setText("" + quantity);
        costView.setText("$" + cost);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateViews((quantity < 50 ? quantity + 1 : quantity));
            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateViews((quantity > 0 ? quantity - 1 : quantity));
            }
        });
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateViews(0);
                mainActivity.removeCartItem(index);
                removeSelf();
            }
        });
        quantityView.addTextChangedListener(textWatcher);
        return myView;
    }

    private void updateViews(int newQuantity){
        quantity = newQuantity;
        quantityView.setTag(editTag);
        quantityView.setText("" + quantity);
        quantityView.setTag(null);
        mainActivity.updateCartItem(index, quantity);
        cart.updateTotal(mainActivity.calculateTotal());
    }

    private void removeSelf(){
        getParentFragment().getChildFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (CartItemComm) context;
        cart = (ItemToCartComm) getParentFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface CartItemComm{
        // when this item needs to be updated, it contacts the cartFragment, which in turn notifies the mainActivity
        void updateCartItem(int index, int newVal);
        void removeCartItem(int index);
        float calculateTotal();
    }

    public interface ItemToCartComm{
        void updateTotal(float total);
    }
}
