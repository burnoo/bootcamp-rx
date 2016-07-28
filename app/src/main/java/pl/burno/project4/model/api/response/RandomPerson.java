package pl.burno.project4.model.api.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RandomPerson
{
    @SerializedName("name")
    @Expose
    public Name name;

}
