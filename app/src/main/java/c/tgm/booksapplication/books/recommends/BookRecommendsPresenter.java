package c.tgm.booksapplication.books.recommends;

import c.tgm.booksapplication.books.list.BookListPresenter;

public class BookRecommendsPresenter extends BookListPresenter {
    @Override
    protected void getNewBooks(String query, boolean rewrite) {
        mRepository.getRecommends(query,mModel.getGenreId(),mModel.getCurPage(),mModel.getPageSize(),rewrite);
    }
}
