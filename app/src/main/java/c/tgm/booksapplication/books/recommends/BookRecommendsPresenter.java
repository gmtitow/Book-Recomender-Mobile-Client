package c.tgm.booksapplication.books.recommends;

import java.util.List;

import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.books.list.BookListPresenter;
import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.models.data.BookListDao;
import c.tgm.booksapplication.repositories.RepositoryCall;

public class BookRecommendsPresenter extends BookListPresenter {
    private long mCurrentListId;
    @Override
    protected void getNewBooks(String query, boolean rewrite) {
        this.rewrite = rewrite;
        mRepository.getRecommends(query,mCurrentListId,mModel.getGenreId(),mModel.getCurPage(),mModel.getPageSize());
    }

    public List<BookList> getBookLists() {
        BookListDao dao = BookApplication.INSTANCE.getDataStore().getDaoSession().getBookListDao();
        return dao.loadAll();
    }

    public void setListId(long listId, String query) {
        mCurrentListId = listId;

        getNewBooks(query,true);
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
}
