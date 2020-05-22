package c.tgm.booksapplication.filters.author;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.authors.list.AuthorPresenterRepo;
import c.tgm.booksapplication.authors.list.AuthorRepository;
import c.tgm.booksapplication.authors.list.AuthorRepositoryImpl;
import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.models.data.GenreDao;


@InjectViewState
public class AuthorFilterPresenter extends NavigatorPresenter<AuthorFilterView> implements AuthorPresenterRepo {
    
    protected AuthorFilterModel mModel;
    protected AuthorRepository mRepository;
    
    public AuthorFilterPresenter() {
        mModel = new AuthorFilterModel();
        mRepository = new AuthorRepositoryImpl(this);
    }

    public void setHandler(IAuthorChanger handler) {
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
        getAuthors(query,rewrite);
    }
    
    protected void getAuthors(String query, boolean rewrite) {
        mModel.setLastQuery(query);
        mModel.setLastRewrite(rewrite);
        mRepository.getAuthors(query,null,mModel.getCurPage(),mModel.getPageSize(), rewrite);
    }
    
    @Override
    public void rememberAuthors(List<Author> authors, boolean rewrite) {
        mModel.setLoading(false);
        
        if (rewrite)
            mModel.setCurAuthors(authors);
        else
            mModel.addAuthors(authors);
        
        if (getView()!=null)
            getView().updateList(mModel.getCurAuthors());
    }
    
    public void getNextPage(String query) {
        mModel.increasePage();
        updateAuthorList(query, false);
    }
    
    public int getPageSize() {
        return mModel.getPageSize();
    }
    
    @Override
    public void onError(String error) {
        mRepository.retry();
    }

    public void onSelectAuthor(Author author) {
        mModel.getChanger().onChangeAuthor(author);
        exit();
    }
}
