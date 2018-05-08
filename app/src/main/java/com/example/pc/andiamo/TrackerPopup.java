package com.example.pc.andiamo;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import static android.os.SystemClock.sleep;

/**
 * Created by johnlewis on 5/5/18.
 */

public class TrackerPopup extends Activity implements View.OnClickListener{
    private Button exitButton;
    private Button trackButton;
    private EditText trackingEditText;
    private int  trackingNumber;
    private ImageView image1, image2, image3, image4, image5;
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);



        setContentView(R.layout.trackerpopupwindow);


        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(this);

        trackButton = findViewById(R.id.trackButton);
        trackButton.setOnClickListener(this);

        trackingEditText = findViewById(R.id.trackingEditText);

        image1 = findViewById(R.id.imageView1);
        image2 = findViewById(R.id.imageView2);
        image3 = findViewById(R.id.imageView3);
        image4 = findViewById(R.id.imageView4);
        image5 = findViewById(R.id.imageView5);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(.8*width), (int)(.8 * height));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exitButton:
                finish();
                break;
            case R.id.trackButton:
                startTracking();
                break;
        }
    }

    private void startTracking(){
        String stringTrackNum = trackingEditText.getText().toString();
        try {
            trackingNumber = Integer.parseInt(stringTrackNum);
            image1.setImageResource(R.drawable.trackericon_01_yellow);
            Thread t = new Thread(new Runnable(){
                public void run(){
                    try{
                        //Thread.sleep(50);
                       // image2.setImageResource(R.drawable.tracker_3dotsbetween_yellow);
                        image3.setImageResource(R.drawable.trackericon_02_yellow);
                        //Thread.sleep(50);
                        image4.setImageResource(R.drawable.tracker_3dotsbetween_yellow);
                        image5.setImageResource(R.drawable.trackericon_03_yellow);
                    }catch(Exception e){
                        System.out.println("exception");
                    }
                }
            });
            t.start();
//            image1.setImageResource(R.drawable.trackericon_01_yellow);
//
//            image2.setImageResource(R.drawable.tracker_3dotsbetween_yellow);
//
//            image3.setImageResource(R.drawable.trackericon_02_yellow);
//
//            image4.setImageResource(R.drawable.tracker_3dotsbetween_yellow);
//
//            image5.setImageResource(R.drawable.trackericon_03_yellow);

        }catch(NumberFormatException e){
            System.out.println("enter a number");
        }


    }
}
