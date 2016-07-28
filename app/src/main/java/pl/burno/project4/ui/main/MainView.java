package pl.burno.project4.ui.main;

import pl.burno.project4.model.RandomPerson;

public interface MainView
{
    void showError(Throwable error);
    void updateView(RandomPerson randomPerson);
}
