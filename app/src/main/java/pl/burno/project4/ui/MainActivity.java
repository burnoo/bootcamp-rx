package pl.burno.project4.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.burno.project4.R;
import pl.burno.project4.model.api.RandomUserService;
import pl.burno.project4.model.api.response.Person;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
{
    private final String TAG = "aaa";
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSubscription = new RandomUserService().getRandomPeople(500)
                .map(randomPeople -> randomPeople.people)
                .flatMap(randomPeople -> Observable.from(randomPeople))
                .filter(randomPerson -> randomPerson.name.first.charAt(0) == 'j')
                .take(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setUpView);
    }

    private void setUpView(Person person)
    {

    }
}
