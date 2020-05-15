package c.tgm.booksapplication.promotion.add.selectbooks;

import com.arellomobile.mvp.InjectViewState;

import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.pagination.APaginationPresenter;

@InjectViewState
public class PromotionSelectBooksPresenter extends APaginationPresenter<PromotionSelectBooksView, Book,PromotionSelectBooksModel> {
    @Override
    protected void initializeModel() {
        mModel = new PromotionSelectBooksModel();
    }

    @Override
    protected void initializeRepository() {

    }

    @Override
    protected void getNewObjects(boolean add) {

    }
}
