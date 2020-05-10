package c.tgm.booksapplication.pagination;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

public interface IPaginationView<MainObject> extends MvpView {
    void updateList(ArrayList<MainObject> objects);

    void showMessage(String message);
}
