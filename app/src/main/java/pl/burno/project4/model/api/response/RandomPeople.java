
package pl.burno.project4.model.api.response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RandomPeople {

    @SerializedName("results")
    @Expose
    public List<Person> people = new ArrayList<Person>();

}
