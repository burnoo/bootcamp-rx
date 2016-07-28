package pl.burno.project4.model.api;

import pl.burno.project4.model.api.response.RandomPeople;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RandomUserApi
{
    @GET("api")
    Observable<RandomPeople> getRandomPeople(@Query("results") int results);
}
