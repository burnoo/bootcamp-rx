package pl.burno.project4.ui.main;

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
import pl.burno.project4.model.RandomPerson;

public class MainActivity extends AppCompatActivity implements MainView
{
    private final String TAG = "aaa";
    @Bind(R.id.personTextView)
    TextView mTextView;
    @Bind(R.id.imageView)
    ImageView mImageView;
    @Bind(R.id.button)
    Button mLoadButton;
    @Bind(R.id.populationTextView)
    TextView populationTextView;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setUpView();

        mPresenter = new MainPresenter(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mTextView.setText(R.string.loading);
        mPresenter.loadRandomPerson();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void showError(Throwable error)
    {
        mTextView.setText(R.string.text_view_error);
        Toast.makeText(MainActivity.this, String.format(getString(R.string.toast_error), error.getMessage()), Toast.LENGTH_LONG).show();
    }

    private void setUpView()
    {
        mLoadButton.setOnClickListener(view -> mPresenter.loadRandomPerson());
    }

    @Override
    public void updateView(RandomPerson randomPerson)
    {
        mTextView.setText(String.format(getString(R.string.person_name), randomPerson.name.first, randomPerson.name.last));
        populationTextView.setText(String.format(getString(R.string.population), randomPerson.location.city, randomPerson.location.population));
        Picasso.with(this).load(String.format("https://api.adorable.io/avatars/285/%s@%s.png", randomPerson.name.first, randomPerson.name.last)).into(mImageView);
    }
}
