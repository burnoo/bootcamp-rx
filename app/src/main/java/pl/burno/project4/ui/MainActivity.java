package pl.burno.project4.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.burno.project4.R;
import pl.burno.project4.model.api.RandomUserService;
import pl.burno.project4.model.api.response.RandomPerson;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
{
    private final String TAG = "aaa";
    @Bind(R.id.textView)
    TextView mTextView;
    @Bind(R.id.imageView)
    ImageView mImageView;
    @Bind(R.id.button)
    Button mLoadButton;
    private Subscription mSubscription;
    private RandomUserService mRandomUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpView();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mRandomUserService = new RandomUserService();
        loadRandomPerson();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }

    private void loadRandomPerson()
    {
        mTextView.setText(R.string.loading);
        mSubscription = mRandomUserService.getRandomPeople(500)
                .map(randomPeople -> randomPeople.people)
                .flatMap(randomPeople -> Observable.from(randomPeople))
                .filter(randomPerson -> randomPerson.name.first.charAt(0) == 'j')
                .take(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateView, this::showError);
    }

    private void showError(Throwable error)
    {
        mTextView.setText(R.string.text_view_error);
        Toast.makeText(MainActivity.this, String.format(getString(R.string.toast_error), error.getMessage()), Toast.LENGTH_LONG).show();
    }

    private void setUpView()
    {
        mLoadButton.setOnClickListener(view -> loadRandomPerson());
    }

    private void updateView(RandomPerson randomPerson)
    {
        mTextView.setText(randomPerson.name.first + " " + randomPerson.name.last);
        Picasso.with(this).load("https://api.adorable.io/avatars/285/" + randomPerson.name.first + "@" + randomPerson.name.last + ".png").into(mImageView);
    }
}
