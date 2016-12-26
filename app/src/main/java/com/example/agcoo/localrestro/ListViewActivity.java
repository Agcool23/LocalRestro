package com.example.agcoo.localrestro;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    ListView listRestaurants;
    ArrayList<PlacesDetails> placesDetailslist;
    private static ListViewAdapter adapter;
    ProgressBar pbarList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        /*listRestaurants = (ListView)findViewById(R.id.listViewRestaurants);
        pbarList = (ProgressBar)findViewById(R.id.progressBarList);
        placesDetailslist = new ArrayList<>();
        //populate this arraylist

        adapter= new ListViewAdapter(placesDetailslist,getApplicationContext());

        listRestaurants.setAdapter(adapter);
        listRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlacesDetails dataModel= placesDetailslist.get(position);

            }
        });*/
        StringBuilder sbValue = new StringBuilder(placesApiForRestaurant());
        ListViewActivity.RestaurantTask restaurantTask = new ListViewActivity.RestaurantTask();
        restaurantTask.execute(sbValue.toString());
    }
    public StringBuilder placesApiForRestaurant() {

        Intent in = getIntent();
        double latitude = Double.parseDouble(in.getStringExtra("key1"));
        double longitude = Double.parseDouble(in.getStringExtra("key2"));

        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        sb.append("location=" + latitude + "," + longitude);
        sb.append("&rankby=distance");
        sb.append("&types=" + "restaurant");
        sb.append("&sensor=true");
        sb.append("&key=AIzaSyDmnA_SQsmHJCbre-ZJf-Xr18IuSM0KATU");

        return sb;
    }


    private class RestaurantTask extends AsyncTask<String, Integer, String> {

        String data = null;

        // Invoked by execute() method of this object
        @Override
        protected String doInBackground(String... url) {
            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(String result) {
            ListViewActivity.ParserTask parserTask = new ListViewActivity.ParserTask();

            // Start parsing the Google places in JSON format
            // Invokes the "doInBackground()" method of the class ParserTask
            parserTask.execute(result);
        }
    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
    private class ParserTask extends AsyncTask<String, Integer, List<PlacesDetails>> {

        JSONObject jObject;

        // Invoked by execute() method of this object
        @Override
        protected List<PlacesDetails> doInBackground(String... jsonData) {

            List<PlacesDetails>restaurantList = null;
            RestaurantJSON restaurantJSON = new RestaurantJSON();

            try {
                jObject = new JSONObject(jsonData[0]);

                restaurantList = restaurantJSON.parse(jObject);

            } catch (Exception e) {
                Log.d("Exception", e.toString());
            }

            return restaurantList;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(List<PlacesDetails> list) {

            listRestaurants = (ListView)findViewById(R.id.listViewRestaurants);
            pbarList = (ProgressBar)findViewById(R.id.progressBarList);
            placesDetailslist = new ArrayList<>();
            //populate this arraylist
            placesDetailslist.addAll(list);

            adapter= new ListViewAdapter(placesDetailslist,getApplicationContext());

            listRestaurants.setAdapter(adapter);
            listRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    PlacesDetails dataModel= placesDetailslist.get(position);

                }
            });
        }
    }
}
