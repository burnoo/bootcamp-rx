package pl.burno.project4.ui.main;

import android.util.Log;

import pl.burno.project4.api.CapitalService;
import pl.burno.project4.api.RandomUserService;
import pl.burno.project4.model.Capital;
import pl.burno.project4.model.RandomPerson;
import pl.burno.project4.ui.main.MainView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainPresenter
{
    private RandomUserService mRandomUserService;
    private CapitalService mCapitalService;

    private Subscription mSubscription;
    private MainView mainView;

    public MainPresenter(MainView mainView)
    {
        mRandomUserService = new RandomUserService();
        mCapitalService = new CapitalService();
        this.mainView = mainView;
    }

    public void onStop()
    {
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }

    public void loadRandomPerson()
    {
        mSubscription = mRandomUserService.getRandomPeople(500)
                .map(randomPeople -> randomPeople.people)
                .flatMap(Observable::from)
                .filter(randomPerson -> randomPerson.name.first.charAt(0) == 'j')
                .take(1)
                .flatMap(randomPerson -> mCapitalService.getInfoFromCapital(randomPerson.location.city/*randomPerson.location.city = "warsaw"*/)
                        .map(capital -> {
                            randomPerson.location.population = capital.get(0).population;
                            return randomPerson;
                        })
                        .onErrorReturn((Func1<Throwable, RandomPerson>) throwable -> {
                            randomPerson.location.population = "not found";//getString(R.string.not_found); ale jak, jak nie mam contextu :/
                            return randomPerson;
                        })
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mainView::updateView, mainView::showError);

    }
}
