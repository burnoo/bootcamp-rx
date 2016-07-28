
package pl.burno.project4.model.api.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Picture {

    @SerializedName("large")
    @Expose
    public String large;
    @SerializedName("medium")
    @Expose
    public String medium;
    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;

}
