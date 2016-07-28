package pl.burno.project4.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class RandomPeople
{

    @SerializedName("results")
    @Expose
    public List<RandomPerson> people = new ArrayList<RandomPerson>();

}
