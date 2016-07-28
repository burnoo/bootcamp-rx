package pl.burno.project4.api;

import java.util.List;

import pl.burno.project4.model.Capital;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface CapitalApi
{
    @GET("rest/v1/capital/{city}")
    Observable<List<Capital>> getInfoFromCapital(@Path("city") String city);
}
