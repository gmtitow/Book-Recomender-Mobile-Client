package c.tgm.booksapplication.books.recommends;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import c.tgm.booksapplication.books.list.BookListFragment;
import c.tgm.booksapplication.books.list.BookListPresenter;

public class BookRecommendsFragment extends BookListFragment {
    
    @InjectPresenter(type = PresenterType.LOCAL)
    BookRecommendsPresenter mPresenter;
    
    @Override
    public String getTitle() {
        return "Рекомендации";
    }
    
    @Override
    public BookRecommendsPresenter getPresenter() {
        return mPresenter;
    }
    
    public static Fragment getInstance() {
        BookRecommendsFragment fragment = new BookRecommendsFragment();
        return fragment;
    }
    
    public static Fragment getInstance(int authorId) {
        BookRecommendsFragment fragment = new BookRecommendsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(AUTHOR_ID,authorId);
        fragment.setArguments(bundle);
        return fragment;
    }
}
