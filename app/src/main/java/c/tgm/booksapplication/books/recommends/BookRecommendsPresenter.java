package c.tgm.booksapplication.books.recommends;

import java.util.List;

import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.books.list.BookListPresenter;
import c.tgm.booksapplication.filters.IFIlterHandler;
import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.models.data.BookListDao;
import c.tgm.booksapplication.repositories.RepositoryCall;

public class BookRecommendsPresenter extends BookListPresenter implements IFIlterHandler {
    private long mCurrentListId;

    public BookRecommendsPresenter() {
        super();

        BookListDao dao = BookApplication.INSTANCE.getDataStore().getDaoSession().getBookListDao();

        List<BookList> list = dao.loadAll();

        mCurrentListId = list.get(0).getListId();
    }

    @Override
    protected void getNewBooks(boolean rewrite) {
        this.rewrite = rewrite;
        mRepository.getRecommends(mModel.getQuery(),mCurrentListId,mModel.getGenreId(),mModel.getCurPage(),mModel.getPageSize());
    }

    public void updateRecommendList() {
        mRepository.updateRecommendList((int)mCurrentListId);
    }

    @Override
    public void onError(String errorDescription, RepositoryCall call) {
        call.call();
        getView().showMessage(errorDescription);
    }

    @Override
    public void onUpdateRecommends(String answer) {
        getView().showMessage("Новые рекомендации будут подготовлены через некоторое время");
    }

    @Override
    public void onBookListChange(BookList list) {
        mCurrentListId = list.getListId();
        getNewBooks(true);
    }
}
