
package pl.burno.project4.model.api.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Person
{

    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("name")
    @Expose
    public Name name;
    @SerializedName("location")
    @Expose
    public Location location;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("login")
    @Expose
    public Login login;
    @SerializedName("registered")
    @Expose
    public Integer registered;
    @SerializedName("dob")
    @Expose
    public Integer dob;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("cell")
    @Expose
    public String cell;
    @SerializedName("picture")
    @Expose
    public Picture picture;
    @SerializedName("nat")
    @Expose
    public String nat;

}
