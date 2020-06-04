package c.tgm.booksapplication.authors.list;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Book;

public class AuthorListModel {

    private String query;
    private Long genreId;
    
    private ArrayList<Author> curAuthors = new ArrayList<>();
    
    private int curPage = 1;
    
    private int pageSize = 20;
    
    private boolean loading = false;
    
    private String mLastQuery;
    
    private boolean mLastRewrite;
    
    public boolean isLastRewrite() {
        return mLastRewrite;
    }
    
    public void setLastRewrite(boolean lastRewrite) {
        mLastRewrite = lastRewrite;
    }
    
    public Long getGenreId() {
        return genreId;
    }
    
    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
    
    public ArrayList<Author> getCurAuthors() {
        return curAuthors;
    }
    
    public void setCurAuthors(List<Author> curAuthors) {
        this.curAuthors.clear();
        this.curAuthors.addAll(curAuthors);
    }
    
    public String getLastQuery() {
        return mLastQuery;
    }
    
    public void setLastQuery(String lastQuery) {
        mLastQuery = lastQuery;
    }
    
    public void addAuthors(List<Author> curBooks) {
        this.curAuthors.addAll(curBooks);
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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
