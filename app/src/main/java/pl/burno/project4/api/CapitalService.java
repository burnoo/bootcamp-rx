package pl.burno.project4.api;

import java.util.List;

import pl.burno.project4.model.Capital;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class CapitalService
{
    private final CapitalApi mCapitalApi;

    public CapitalService()
    {
        mCapitalApi = new Retrofit.Builder()
            .baseUrl("https://restcountries.eu/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CapitalApi.class);
    }

    public Observable<List<Capital>> getInfoFromCapital(String city)
    {
        return mCapitalApi.getInfoFromCapital(city);
    }
}
