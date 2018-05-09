package com.example.pc.andiamo;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SandwichMenuFragment extends Fragment implements View.OnClickListener{

    private ImageButton increment0, increment1, increment2, increment3, increment4, increment5, increment6, increment7;
    private ImageButton decrement0, decrement1, decrement2, decrement3, decrement4, decrement5, decrement6, decrement7;
    private TextView item0, item1, item2, item3, item4, item5, item6, item7 ;

    private Button updateCart;
    private EditText specialRequest;

    private AddtoCart addtoCart;

    int numItems[] = new int[8];
    ArrayList<TextView> itemQuantities = new ArrayList<>();

    public SandwichMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sandwich_menu, container, false);
        increment0 = view.findViewById(R.id.sincrement0);
        increment1 = view.findViewById(R.id.sincrement1);
        increment2 = view.findViewById(R.id.sincrement2);
        increment3 = view.findViewById(R.id.sincrement3);
        increment4 = view.findViewById(R.id.sincrement4);
        increment5 = view.findViewById(R.id.sincrement5);
        increment6 = view.findViewById(R.id.sincrement6);
        increment7 = view.findViewById(R.id.sincrement7);

        decrement0 = view.findViewById(R.id.sdecrement0);
        decrement1 = view.findViewById(R.id.sdecrement1);
        decrement2 = view.findViewById(R.id.sdecrement2);
        decrement3 = view.findViewById(R.id.sdecrement3);
        decrement4 = view.findViewById(R.id.sdecrement4);
        decrement5 = view.findViewById(R.id.sdecrement5);
        decrement6 = view.findViewById(R.id.sdecrement6);
        decrement7 = view.findViewById(R.id.sdecrement7);

        item0 = view.findViewById(R.id.sitem0quantity);
        item1 = view.findViewById(R.id.sitem1quantity);
        item2 = view.findViewById(R.id.sitem2quantity);
        item3 = view.findViewById(R.id.sitem3quantity);
        item4 = view.findViewById(R.id.sitem4quantity);
        item5 = view.findViewById(R.id.sitem5quantity);
        item6 = view.findViewById(R.id.sitem6quantity);
        item7 = view.findViewById(R.id.sitem7quantity);

        updateCart = view.findViewById(R.id.supdateCartButton);

        specialRequest = view.findViewById(R.id.sspecialRequest);

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

        hideSoftKeyboard(view);

        switch (view.getId()){
            case R.id.sdecrement0:
                decrementValue(item0);
                break;
            case R.id.sdecrement1:
                decrementValue(item1);
                break;
            case R.id.sdecrement2:
                decrementValue(item2);
                break;
            case R.id.sdecrement3:
                decrementValue(item3);
                break;
            case R.id.sdecrement4:
                decrementValue(item4);
                break;
            case R.id.sdecrement5:
                decrementValue(item5);
                break;
            case R.id.sdecrement6:
                decrementValue(item6);
                break;
            case R.id.sdecrement7:
                decrementValue(item7);
                break;
            case R.id.sincrement0:
                incrementValue(item0);
                break;
            case R.id.sincrement1:
                incrementValue(item1);
                break;
            case R.id.sincrement2:
                incrementValue(item2);
                break;
            case R.id.sincrement3:
                incrementValue(item3);
                break;
            case R.id.sincrement4:
                incrementValue(item4);
                break;
            case R.id.sincrement5:
                incrementValue(item5);
                break;
            case R.id.sincrement6:
                incrementValue(item6);
                break;
            case R.id.sincrement7:
                incrementValue(item7);
                break;
            case R.id.supdateCartButton:
                pushItemsToCart();
                break;
        }
    }

    private void pushItemsToCart() {
        for(int i=0;i<itemQuantities.size();i++){
            numItems[i] = Integer.parseInt(itemQuantities.get(i).getText().toString());
            itemQuantities.get(i).setText("0");
        }
        String request = new String(specialRequest.getText().toString());
        specialRequest.setText("");

        addtoCart.getQuantities(numItems, Constants.SUB_OFFSET, request);


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
        void getQuantities(int cart[], int offset, String requests);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addtoCart = (AddtoCart) context;
    }

    public static void hideSoftKeyboard(View view) {
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}
