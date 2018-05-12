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

    private ImageButton increment;
    private ImageButton decrement;
    private TextView quantityView;
    private TextView titleView;

    private String title;
    private int quantity;

    public CartItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        title = args.getString("title", "");
        quantity = args.getInt("quantity", 0);
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
        titleView.setText(title);
        quantityView.setText("" + quantity);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                quantityView.setText("" + quantity);
            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity--;
                quantityView.setText("" + quantity);
            }
        });
        return myView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
