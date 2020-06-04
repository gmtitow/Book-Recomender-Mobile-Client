package c.tgm.booksapplication.authors.list;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.filters.IFIlterHandler;
import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.models.data.GenreDao;

public class AuthorListPresenter extends NavigatorPresenter<AuthorListView> implements AuthorPresenterRepo,
        IFIlterHandler {
    
    protected AuthorListModel mModel;
    protected AuthorRepository mRepository;
    
    public AuthorListPresenter() {
        mModel = new AuthorListModel();
        mRepository = new AuthorRepositoryImpl(this);
    }
    
    public void updateAuthorList(boolean rewrite) {
        if(mModel.isLoading())
            mRepository.cancelRequest();
        
        if (rewrite){
            mModel.setCurPage(1);
        }

        mModel.setLastRewrite(rewrite);
        
        mModel.setLoading(true);
        getAuthors(rewrite);
    }
    
    protected void getAuthors(boolean rewrite) {
        mModel.setLastRewrite(rewrite);
        mRepository.getAuthors(mModel.getQuery(),mModel.getGenreId(),mModel.getCurPage(),mModel.getPageSize(), rewrite);
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
    
    public void getNextPage() {
        mModel.increasePage();
        updateAuthorList(false);
    }
    
    public int getPageSize() {
        return mModel.getPageSize();
    }
    
    public void openBooks(int author_id) {
        navigateTo(new Screens.BookScreens(Screens.BookScreens.FIND_BOOK_SCREEN, author_id));
    }
    
    @Override
    public void onError(String error) {
//        mRepository.getAuthors(mModel.getLastQuery(),mModel.getGenreId(),mModel.getCurPage(),mModel.getPageSize(), mModel.isLastRewrite());
        mRepository.retry();
    }

    @Override
    public void onQueryChange(String query) {
        mModel.setQuery(query);
        getAuthors(true);
    }

    @Override
    public void onGenreChange(Genre genre) {
        mModel.setGenreId(genre.getGenreId());
        getAuthors(true);
    }
}
