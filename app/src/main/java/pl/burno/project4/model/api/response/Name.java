package pl.burno.project4.model.api.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Name
{

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("first")
    @Expose
    public String first;
    @SerializedName("last")
    @Expose
    public String last;

}
