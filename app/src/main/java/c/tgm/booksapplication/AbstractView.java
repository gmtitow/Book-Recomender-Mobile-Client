package c.tgm.booksapplication;

import com.arellomobile.mvp.MvpView;

public interface AbstractView extends MvpView {

    void showMessage(String message);
}
