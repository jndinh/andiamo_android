package com.example.pc.andiamo;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.os.SystemClock.sleep;
import static com.example.pc.andiamo.Constants.AUTHORIZATION_HEADER;
import static com.example.pc.andiamo.Constants.GET_ORDER_EP;
import static com.example.pc.andiamo.Constants.REGISTER_EP;

/**
 * Created by johnlewis on 5/5/18.
 */

public class TrackerPopup extends Activity implements View.OnClickListener{
    private Button exitButton;
    private Button trackButton;
    private EditText trackingEditText;
    private int  trackingNumber1;
    private ImageView image1, image2, image3, image4, image5;
    private boolean foundOrder;
    private Date timeStamp;
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

        getWindow().setLayout((int)(.9*width), (int)(.9 * height));
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
            trackingNumber1 = Integer.parseInt(stringTrackNum);

            Track track = new Track(stringTrackNum);

            track.execute();

            /*
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
            */

        }catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Please Enter a Number", Toast.LENGTH_SHORT).show();
        }


    }

    private void changeImages(){

        image1.setImageResource(R.drawable.trackericon_01_yellow);

        image2.setImageResource(R.drawable.tracker_3dotsbetween_yellow);
        image3.setImageResource(R.drawable.trackericon_02_yellow);

        image4.setImageResource(R.drawable.tracker_3dotsbetween_yellow);
        image5.setImageResource(R.drawable.trackericon_03_yellow);


    }
    public void onDestroy() {

        super.onDestroy();
        startActivity(new Intent(TrackerPopup.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));



    }

    private class Track extends AsyncTask<Void, Void, Boolean>
    {
        String trackingNumber;
        JSONObject responseData;

        Track(String trackn) {
            trackingNumber=  trackn;

        }



        @Override
        protected Boolean doInBackground(Void... params) {
            //this method will be running on background thread so don't update UI from here
            //do your long running http tasks here,you don't want to pass argument and u can access the parent class' variable url over here

            try {
                // building json...
                /*
                JSONObject data = new JSONObject();
                System.out.println("tracking number = " + trackingNumber);

                data.put("order_number=", trackingNumber);
            */
                String data = "order_number=" + trackingNumber;


                // optional parameter


                // building post request
                OkHttpClient client = new OkHttpClient();
                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, data);
                Request request = new Request.Builder()
                        .url(GET_ORDER_EP)
                        .post(body)
                        .addHeader("AUTHORIZATION", AUTHORIZATION_HEADER)
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .build();
                System.out.println(request.body().toString());

                // parsing response...
                Response response = client.newCall(request).execute();
                if (response.body() == null) return false;
                String strResponse = response.body().string();
                final int code = response.code();
                responseData = new JSONObject(strResponse);

                Log.d("webtag", strResponse);

                if (code == 200) {
                    return true;
                }

                return false;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if (result) {

                try {
                    int status = (int)responseData.get("status");
                    if(status == 1){
                        Toast.makeText(getApplicationContext(), "Tracking Number Found", Toast.LENGTH_SHORT).show();
                        foundOrder = true;
                        JSONObject temp = responseData.getJSONObject("data");
                        String ts = (String) temp.get("timestamp");
                        /*
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
                        timeStamp = df.parse(ts);
                        */
                        changeImages();

                    }
                    else
                        Toast.makeText(getApplicationContext(), "Tracking Number Not Found", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();

                }

//                catch (ParseException e) {
//                    e.printStackTrace();
//                }


            } else {
                try {
                    // something went wrong
                    String data = (String) responseData.get("data");
                    Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /*
                try {
                    // something didnt go wrong
                    String data = (String) responseData.get("status");
                    if(data.equals("1")){
                        foundOrder = true;
                        JSONObject inner = (JSONObject) responseData.get("data");
                        String ts = (String) responseData.get("timestamp");

                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
                        timeStamp = df.parse(ts);
                    }
                    if(data.equals("0")){
                        Toast.makeText(getApplicationContext(), "Tracking Number not found.", Toast.LENGTH_SHORT).show();
                    }


                    //Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                */
            }

        }
    }
}
