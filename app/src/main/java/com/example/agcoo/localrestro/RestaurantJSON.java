package com.example.agcoo.localrestro;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestaurantJSON {

    /**
     * Receives a JSONObject and returns a list
     */
    public List<PlacesDetails> parse(JSONObject jObject) {

        JSONArray jPlaces = null;
        try {

            jPlaces = jObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return getPlace(jPlaces);
    }

    private List<PlacesDetails> getPlace(JSONArray jPlaces) {

        List<PlacesDetails> place = new ArrayList<>();

        JSONObject obj;
        for (int i = 0; i < jPlaces.length()-1; i++) {
            try {

                obj = jPlaces.getJSONObject(i);
                PlacesDetails pd = new PlacesDetails();
                for (int k = 0; k<obj.length();k++)
                if (!obj.isNull("name")) {
                    pd.setPlaceName(obj.getString("name"));
                }

                if (!obj.isNull("vicinity")) {
                    pd.setVicinity(obj.getString("vicinity"));
                }

                pd.setLatitude(obj.getJSONObject("geometry").getJSONObject("location").getString("lat"));
                pd.setLongitude(obj.getJSONObject("geometry").getJSONObject("location").getString("lng"));
                pd.setReference(obj.getString("reference"));
                pd.setRating(obj.getString("rating"));

                place.add(pd);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return place;
    }
}
