package pl.burno.project4.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Location
{
    @SerializedName("city")
    @Expose
    public String city;

    public String population;
}