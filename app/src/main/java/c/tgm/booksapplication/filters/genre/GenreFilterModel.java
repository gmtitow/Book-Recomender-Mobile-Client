package c.tgm.booksapplication.filters.genre;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Genre;

public class GenreFilterModel {
    
    private ArrayList<Genre> genres = new ArrayList<>();

    private IGenreChanger changer;

    private int curPage = 1;

    private int pageSize = 20;

    private boolean loading = false;

    private String mLastQuery;

    private boolean mLastRewrite;

    public IGenreChanger getChanger() {
        return changer;
    }

    public void setChanger(IGenreChanger changer) {
        this.changer = changer;
    }

    public boolean isLastRewrite() {
        return mLastRewrite;
    }
    
    public void setLastRewrite(boolean lastRewrite) {
        mLastRewrite = lastRewrite;
    }
    
    public ArrayList<Genre> getGenres() {
        return genres;
    }
    
    public void setGenres(List<Genre> genres) {
        this.genres.clear();
        this.genres.addAll(genres);
    }
    
    public String getLastQuery() {
        return mLastQuery;
    }
    
    public void setLastQuery(String lastQuery) {
        mLastQuery = lastQuery;
    }
    
    public void addAuthors(List<Genre> genres) {
        this.genres.addAll(genres);
    }
    
    public int getCurPage() {
        return curPage;
    }
    
    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
    
    public int increasePage() {
        return ++this.curPage;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public boolean isLoading() {
        return loading;
    }
    
    public void setLoading(boolean loading) {
        this.loading = loading;
    }
}
