package com.example.agcoo.localrestro;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Agcoo on 26-Dec-16.
 */

public interface MapsRetrofit {


    @GET("https://maps.googleapis.com/maps/api/place/nearbysearch/json?sensor=true&key=AIzaSyDN7RJFmImYAca96elyZlE5s_fhX-MMuhk")
    Call<PlacesDetails> getNearbyPlaces(@Query("type") String type, @Query("location") String location, @Query("radius") int radius);
}
