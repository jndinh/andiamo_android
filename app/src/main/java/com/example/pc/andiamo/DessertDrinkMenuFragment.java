package com.example.pc.andiamo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.pc.andiamo.Constants.DES_OFFSET;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DessertDrinkMenuFragment.AddtoCart} interface
 * to handle interaction events.
 */
public class DessertDrinkMenuFragment extends Fragment implements View.OnClickListener{
    ImageButton increment0, increment1, increment2, increment3, increment4, increment5, increment6, increment7, increment8, increment9, increment10, increment11, increment12;
    ImageButton decrement0, decrement1, decrement2, decrement3, decrement4, decrement5, decrement6, decrement7, decrement8, decrement9, decrement10, decrement11, decrement12;
    TextView item0, item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12;

    Button updateCart;

    int numItems[] = new int[13];
    ArrayList<TextView> itemQuantities = new ArrayList<>();


    private AddtoCart addtoCart;

    public DessertDrinkMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dessert_drink_menu, container, false);

        // dumb increment/decrement buttons
        increment0 = view.findViewById(R.id.rvincrement);
        increment1 = view.findViewById(R.id.vanincrement);
        increment2 = view.findViewById(R.id.chocincrement);
        increment3 = view.findViewById(R.id.keyincrement);
        increment4 = view.findViewById(R.id.creamincrement);
        increment5 = view.findViewById(R.id.ccincrement);
        increment6 = view.findViewById(R.id.oatincrement);
        increment7 = view.findViewById(R.id.brownieincrement);
        increment8 = view.findViewById(R.id.cokeincrement);
        increment9 = view.findViewById(R.id.dietincrement);
        increment10 = view.findViewById(R.id.spriteincrement);
        increment11 = view.findViewById(R.id.gingerincrement);
        increment12 = view.findViewById(R.id.lemonincrement);

        decrement0 = view.findViewById(R.id.rvdecrement);
        decrement1 = view.findViewById(R.id.vandecrement);
        decrement2 = view.findViewById(R.id.chocdecrement);
        decrement3 = view.findViewById(R.id.keydecrement);
        decrement4 = view.findViewById(R.id.creamdecrement);
        decrement5 = view.findViewById(R.id.ccdecrement);
        decrement6 = view.findViewById(R.id.oatdecrement);
        decrement7 = view.findViewById(R.id.browniedecrement);
        decrement8 = view.findViewById(R.id.cokedecrement);
        decrement9 = view.findViewById(R.id.dietdecrement);
        decrement10 = view.findViewById(R.id.spritedecrement);
        decrement11 = view.findViewById(R.id.gingerdecrement);
        decrement12 = view.findViewById(R.id.lemondecrement);

        // quantity value
        item0 = view.findViewById(R.id.rvquantity);
        item1 = view.findViewById(R.id.vanquantity);
        item2 = view.findViewById(R.id.chocquantity);
        item3 = view.findViewById(R.id.keyquantity);
        item4 = view.findViewById(R.id.creamquantity);
        item5 = view.findViewById(R.id.ccquantity);
        item6 = view.findViewById(R.id.oatquantity);
        item7 = view.findViewById(R.id.browniequantity);
        item8 = view.findViewById(R.id.cokequantity);
        item9 = view.findViewById(R.id.dietquantity);
        item10 = view.findViewById(R.id.spritequantity);
        item11 = view.findViewById(R.id.gingerquantity);
        item12 = view.findViewById(R.id.lemonquantity);

        // cart
        updateCart = view.findViewById(R.id.updateCartButton);

        // All these damn listeners..
        updateCart.setOnClickListener(this);

        increment0.setOnClickListener(this);
        increment1.setOnClickListener(this);
        increment2.setOnClickListener(this);
        increment3.setOnClickListener(this);
        increment4.setOnClickListener(this);
        increment5.setOnClickListener(this);
        increment6.setOnClickListener(this);
        increment7.setOnClickListener(this);
        increment8.setOnClickListener(this);
        increment9.setOnClickListener(this);
        increment10.setOnClickListener(this);
        increment11.setOnClickListener(this);
        increment12.setOnClickListener(this);

        decrement0.setOnClickListener(this);
        decrement1.setOnClickListener(this);
        decrement2.setOnClickListener(this);
        decrement3.setOnClickListener(this);
        decrement4.setOnClickListener(this);
        decrement5.setOnClickListener(this);
        decrement6.setOnClickListener(this);
        decrement7.setOnClickListener(this);
        decrement8.setOnClickListener(this);
        decrement9.setOnClickListener(this);
        decrement10.setOnClickListener(this);
        decrement11.setOnClickListener(this);
        decrement12.setOnClickListener(this);

        // Getting all quantities
        itemQuantities.add(item0);
        itemQuantities.add(item1);
        itemQuantities.add(item2);
        itemQuantities.add(item3);
        itemQuantities.add(item4);
        itemQuantities.add(item5);
        itemQuantities.add(item6);
        itemQuantities.add(item7);
        itemQuantities.add(item8);
        itemQuantities.add(item9);
        itemQuantities.add(item10);
        itemQuantities.add(item11);
        itemQuantities.add(item12);

        return view;
    }

    private void incrementValue(TextView t){
        int current = Integer.valueOf(t.getText().toString());
        if(current < 50) {
            current++;
            t.setText(Integer.toString(current));
        }
    }

    private void decrementValue(TextView t){
        int current = Integer.valueOf(t.getText().toString());
        if(current > 0) {
            current--;
            t.setText(Integer.toString(current));
        }
    }

    private void pushItemsToCart() {
        // every index corresponds to an item on the menu
        // every value corresponds to a quantity of that item
        for(int i=0;i<itemQuantities.size();i++){
            numItems[i] = Integer.parseInt(itemQuantities.get(i).getText().toString());
            itemQuantities.get(i).setText("0");
        }

        // Passing cart to activity...
        addtoCart.getQuantities(numItems, DES_OFFSET, "");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddtoCart) {
            addtoCart = (AddtoCart) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AddtoCatListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        addtoCart = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rvdecrement:          // decrement
                decrementValue(item0);
                break;
            case R.id.vandecrement:
                decrementValue(item1);
                break;
            case R.id.chocdecrement:
                decrementValue(item2);
                break;
            case R.id.keydecrement:
                decrementValue(item3);
                break;
            case R.id.creamdecrement:
                decrementValue(item4);
                break;
            case R.id.ccdecrement:
                decrementValue(item5);
                break;
            case R.id.oatdecrement:
                decrementValue(item6);
                break;
            case R.id.browniedecrement:
                decrementValue(item7);
                break;
            case R.id.cokedecrement:
                decrementValue(item8);
                break;
            case R.id.dietdecrement:
                decrementValue(item9);
                break;
            case R.id.spritedecrement:
                decrementValue(item10);
                break;
            case R.id.gingerdecrement:
                decrementValue(item11);
                break;
            case R.id.lemondecrement:
                decrementValue(item12);
                break;
            case R.id.rvincrement:           // increment
                incrementValue(item0);
                break;
            case R.id.vanincrement:
                incrementValue(item1);
                break;
            case R.id.chocincrement:
                incrementValue(item2);
                break;
            case R.id.keyincrement:
                incrementValue(item3);
                break;
            case R.id.creamincrement:
                incrementValue(item4);
                break;
            case R.id.ccincrement:
                incrementValue(item5);
                break;
            case R.id.oatincrement:
                incrementValue(item6);
                break;
            case R.id.brownieincrement:
                incrementValue(item7);
                break;
            case R.id.cokeincrement:
                incrementValue(item8);
                break;
            case R.id.dietincrement:
                incrementValue(item9);
                break;
            case R.id.spriteincrement:
                incrementValue(item10);
                break;
            case R.id.gingerincrement:
                incrementValue(item11);
                break;
            case R.id.lemonincrement:
                incrementValue(item12);
                break;
            case R.id.updateCartButton:     // shopping cart
                pushItemsToCart();
                break;
        }

    }

    public interface AddtoCart{
        void getQuantities(int cart[], int offset, String requests);
    }
}
