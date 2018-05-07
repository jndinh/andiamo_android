package com.example.pc.andiamo;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PizzaMenuFragment extends Fragment implements View.OnClickListener{

    private ImageButton increment0, increment1, increment2, increment3, increment4, increment5, increment6, increment7;
    private ImageButton decrement0, decrement1, decrement2, decrement3, decrement4, decrement5, decrement6, decrement7;
    private TextView item0, item1, item2, item3, item4, item5, item6, item7 ;

    private Button updateCart;
    private EditText specialRequest;

    private AddtoCart addtoCart;

    int numItems[] = new int[8];
    ArrayList<TextView> itemQuantities = new ArrayList<>();

    public PizzaMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pizza_fragment_menu, container, false);
        increment0 = view.findViewById(R.id.increment0);
        increment1 = view.findViewById(R.id.increment1);
        increment2 = view.findViewById(R.id.increment2);
        increment3 = view.findViewById(R.id.increment3);
        increment4 = view.findViewById(R.id.increment4);
        increment5 = view.findViewById(R.id.increment5);
        increment6 = view.findViewById(R.id.increment6);
        increment7 = view.findViewById(R.id.increment7);

        decrement0 = view.findViewById(R.id.decrement0);
        decrement1 = view.findViewById(R.id.decrement1);
        decrement2 = view.findViewById(R.id.decrement2);
        decrement3 = view.findViewById(R.id.decrement3);
        decrement4 = view.findViewById(R.id.decrement4);
        decrement5 = view.findViewById(R.id.decrement5);
        decrement6 = view.findViewById(R.id.decrement6);
        decrement7 = view.findViewById(R.id.decrement7);

        item0 = view.findViewById(R.id.item0quantity);
        item1 = view.findViewById(R.id.item1quantity);
        item2 = view.findViewById(R.id.item2quantity);
        item3 = view.findViewById(R.id.item3quantity);
        item4 = view.findViewById(R.id.item4quantity);
        item5 = view.findViewById(R.id.item5quantity);
        item6 = view.findViewById(R.id.item6quantity);
        item7 = view.findViewById(R.id.item7quantity);

        updateCart = view.findViewById(R.id.updateCartButton);

        specialRequest = view.findViewById(R.id.specialRequest);

        updateCart.setOnClickListener(this);

        increment0.setOnClickListener(this);
        increment1.setOnClickListener(this);
        increment2.setOnClickListener(this);
        increment3.setOnClickListener(this);
        increment4.setOnClickListener(this);
        increment5.setOnClickListener(this);
        increment6.setOnClickListener(this);
        increment7.setOnClickListener(this);

        decrement0.setOnClickListener(this);
        decrement1.setOnClickListener(this);
        decrement2.setOnClickListener(this);
        decrement3.setOnClickListener(this);
        decrement4.setOnClickListener(this);
        decrement5.setOnClickListener(this);
        decrement6.setOnClickListener(this);
        decrement7.setOnClickListener(this);

        itemQuantities.add(item0);
        itemQuantities.add(item1);
        itemQuantities.add(item2);
        itemQuantities.add(item3);
        itemQuantities.add(item4);
        itemQuantities.add(item5);
        itemQuantities.add(item6);
        itemQuantities.add(item7);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh here??
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.decrement0:
                decrementValue(item0);
                break;
            case R.id.decrement1:
                decrementValue(item1);
                break;
            case R.id.decrement2:
                decrementValue(item2);
                break;
            case R.id.decrement3:
                decrementValue(item3);
                break;
            case R.id.decrement4:
                decrementValue(item4);
                break;
            case R.id.decrement5:
                decrementValue(item5);
                break;
            case R.id.decrement6:
                decrementValue(item6);
                break;
            case R.id.decrement7:
                decrementValue(item7);
                break;
            case R.id.increment0:
                incrementValue(item0);
                break;
            case R.id.increment1:
                incrementValue(item1);
                break;
            case R.id.increment2:
                incrementValue(item2);
                break;
            case R.id.increment3:
                incrementValue(item3);
                break;
            case R.id.increment4:
                incrementValue(item4);
                break;
            case R.id.increment5:
                incrementValue(item5);
                break;
            case R.id.increment6:
                incrementValue(item6);
                break;
            case R.id.increment7:
                incrementValue(item7);
                break;
            case R.id.updateCartButton:
                pushItemsToCart();
                break;
        }
    }

    private void pushItemsToCart() {
        for(int i=0;i<itemQuantities.size();i++){
            numItems[i] = Integer.parseInt(itemQuantities.get(i).getText().toString());
            itemQuantities.get(i).setText("0");
        }

        addtoCart.getQuanities(numItems, 0);


        for(int i=0;i<numItems.length;i++){
            numItems[i] = 0;
        }
    }

    private void incrementValue(TextView t){
        int current = Integer.valueOf(t.getText().toString());
        current++;
        t.setText(Integer.toString(current));
    }

    private void decrementValue(TextView t){
        int current = Integer.valueOf(t.getText().toString());
        if(current > 0) {
            current--;
            t.setText(Integer.toString(current));
        }
    }

    public interface AddtoCart{
        void getQuanities(int cart[], int offset);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addtoCart = (AddtoCart) context;
    }

}
