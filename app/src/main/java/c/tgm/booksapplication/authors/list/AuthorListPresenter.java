package c.tgm.booksapplication.authors.list;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.models.data.GenreDao;

public class AuthorListPresenter extends NavigatorPresenter<AuthorListView> implements AuthorPresenterRepo {
    
    protected AuthorListModel mModel;
    protected AuthorRepository mRepository;
    
    public AuthorListPresenter() {
        mModel = new AuthorListModel();
        mRepository = new AuthorRepositoryImpl(this);
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
        mRepository.getAuthors(query,mModel.getGenreId(),mModel.getCurPage(),mModel.getPageSize(), rewrite);
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
    
    public void setGenre(Long genreId, String query) {
        
        if (genreId < 0)
            genreId = null;
        
        if ((genreId==null && mModel.getGenreId() != null) ||
                (genreId!= null && !genreId.equals(mModel.getGenreId()))) {
            mModel.setGenreId(genreId);
            updateAuthorList(query,true);
        }
    }
    
    public List<Genre> getGenres() {
        GenreDao genreDao = DataStore.getDaoSession().getGenreDao();
        
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(-1l,"Любой жанр"));
    
        genres.addAll(genreDao.queryBuilder().build().list());
        
        return genres;
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
}
