package c.tgm.booksapplication.book_lists;

import java.util.ArrayList;

import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.repositories.book_list.BookListRepo;
import c.tgm.booksapplication.repositories.book_list.BookListRepositoryImpl;

public class BookListsListPresenter extends NavigatorPresenter<BookListsListView> implements BookListRepo {

    protected BookListsModel mModel;
    protected BookListRepositoryImpl mRepository;

    public BookListsListPresenter() {
        mModel = new BookListsModel();
        mRepository = new BookListRepositoryImpl(this);
    }

    @Override
    public void onGetLists(ArrayList<BookList> lists) {
        mModel.setCurLists(lists);
        getView().updateList(mModel.getCurLists());
    }
    @Override
    public void onError(String error) {
         mRepository.retry();
    }

    public ArrayList<BookList> getLists() {
        return mModel.getCurLists();
    }

    public void updateListsList() {
        mRepository.getBookLists();
    }

    public void createList(String listName) {
        mRepository.createNewList(listName);
    }

    @Override
    public void onCreateList(BookList list) {
        mModel.addList(list);

        getView().updateList(mModel.getCurLists());
    }

    public void deleteList() {
        getView().progressChanged(true);
        mRepository.deleteList(
                mModel.getCurrentListId()
        );
    }

    public void setCurrentListId(int listId) {
        mModel.setCurrentListId(listId);
    }

    @Override
    public void onDeleteList(String deleted) {
        mModel.deleteListById(mModel.getCurrentListId());
        getView().updateList(mModel.getCurLists());
        getView().progressChanged(false);
    }
}
