package c.tgm.booksapplication.filters.booklist;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;

import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.repositories.RepositoryCall;
import c.tgm.booksapplication.repositories.book_list.BookListRepo;
import c.tgm.booksapplication.repositories.book_list.BookListRepository;
import c.tgm.booksapplication.repositories.book_list.BookListRepositoryImpl;


@InjectViewState
public class BookListFilterPresenter extends NavigatorPresenter<BookListFilterView> implements BookListRepo {
    
    protected BookListFilterModel mModel;
    protected BookListRepository mRepository;
    
    public BookListFilterPresenter() {
        mModel = new BookListFilterModel();
        mRepository = new BookListRepositoryImpl(this);
    }

    public void setHandler(IBookListChanger handler) {
        mModel.setChanger(handler);
    }
    
    public void updateAuthorList(String query, boolean rewrite) {
        if(mModel.isLoading())
            mRepository.cancelRequest();
        
        if (rewrite){
            mModel.setCurPage(1);
        }
        
        mModel.setLastQuery(query);
        mModel.setLastRewrite(rewrite);
        
        mModel.setLoading(true);
        getLists(query,rewrite);
    }
    
    protected void getLists(String query, boolean rewrite) {
        mModel.setLastQuery(query);
        mModel.setLastRewrite(rewrite);
        mRepository.getBookLists();
    }

    @Override
    public void onError(String errorDescription, RepositoryCall call) {
        call.call();
    }

    @Override
    public void onGetLists(ArrayList<BookList> lists) {
        mModel.setLoading(false);

//        BookList emptyList = new BookList();
//
//        emptyList.setListId(-1L);
//        emptyList.setListName("Сбросить");
//
//        lists.add(0,emptyList);

        mModel.setLists(lists);

        if (getView()!=null)
            getView().updateList(mModel.getLists());
    }

    public void onSelectList(BookList bookList) {
        if (bookList.getListId()==-1)
            mModel.getChanger().onChangeList(null);
        else
            mModel.getChanger().onChangeList(bookList);

        exit();
    }
}
