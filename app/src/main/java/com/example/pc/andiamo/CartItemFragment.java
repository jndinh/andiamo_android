package com.example.pc.andiamo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartItemFragment extends Fragment{

    private CartItemComm mainActivity;

    private ImageButton increment;
    private ImageButton decrement;
    private ImageButton removeButton;
    private TextView quantityView;
    private TextView titleView;

    private int index;
    private String title;
    private int quantity;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_cart_item, container, false);
        titleView = (TextView) myView.findViewById(R.id.item_title);
        quantityView = (TextView) myView.findViewById(R.id.item_quantity);
        increment = (ImageButton) myView.findViewById(R.id.increment_item);
        decrement = (ImageButton) myView.findViewById(R.id.decrement_item);
        removeButton = (ImageButton) myView.findViewById(R.id.remove_item_button);

        titleView.setText(title);
        quantityView.setText("" + quantity);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                quantityView.setText("" + quantity);
                mainActivity.updateCartItem(index, true);
            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity > 0) quantity--;
                quantityView.setText("" + quantity);
                mainActivity.updateCartItem(index, false);
            }
        });
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.removeCartItem(index);
                removeSelf();
            }
        });
        return myView;
    }

    private void removeSelf(){
        getParentFragment().getChildFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (CartItemComm) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface CartItemComm{
        // when this item needs to be updated, it contacts the cartFragment, which in turn notifies the mainActivity
        void updateCartItem(int index, boolean increment);
        void removeCartItem(int index);
    }
}
