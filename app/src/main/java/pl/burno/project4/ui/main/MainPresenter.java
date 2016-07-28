package pl.burno.project4.ui.main;

import pl.burno.project4.api.RandomUserService;
import pl.burno.project4.ui.main.MainView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter
{
    private RandomUserService mRandomUserService;
    private Subscription mSubscription;
    private MainView mainView;

    public MainPresenter(MainView mainView)
    {
        mRandomUserService = new RandomUserService();
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mainView::updateView, mainView::showError);
    }
}
