package com.example.agcoo.localrestro;

import java.io.Serializable;

/**
 * Created by Agcool23 on 25-Dec-16.
 */

public class PlacesDetails implements Serializable{
    String placeName = "-NA-";
    String vicinity = "-NA-";
    String latitude = "";
    String longitude = "";
    String reference = "";

    public PlacesDetails(String placeName, String vicinity, String latitude, String longitude, String reference) {
        this.placeName = placeName;
        this.vicinity = vicinity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.reference = reference;
    }
    public PlacesDetails(){

    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "PlacesDetails{" +
                "placeName='" + placeName + '\'' +
                ", vicinity='" + vicinity + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}
