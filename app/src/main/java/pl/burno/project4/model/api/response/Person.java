package pl.burno.project4.model.api.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Person
{
    @SerializedName("name")
    @Expose
    public Name name;

}
