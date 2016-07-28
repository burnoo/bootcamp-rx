package pl.burno.project4.api;

import pl.burno.project4.model.RandomPeople;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class RandomUserService
{
    private final RandomUserApi mRandomUserApi;

    public RandomUserService()
    {
        mRandomUserApi = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RandomUserApi.class);
    }

    public Observable<RandomPeople> getRandomPeople(int results)
    {
        return mRandomUserApi.getRandomPeople(results);
    }
}
