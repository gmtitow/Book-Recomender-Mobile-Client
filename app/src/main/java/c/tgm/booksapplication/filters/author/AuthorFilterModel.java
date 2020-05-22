package c.tgm.booksapplication.filters.author;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.models.data.Author;

public class AuthorFilterModel {
    
    private ArrayList<Author> curAuthors = new ArrayList<>();

    private IAuthorChanger changer;
    
    private int curPage = 1;
    
    private int pageSize = 20;
    
    private boolean loading = false;
    
    private String mLastQuery;
    
    private boolean mLastRewrite;

    public IAuthorChanger getChanger() {
        return changer;
    }

    public void setChanger(IAuthorChanger changer) {
        this.changer = changer;
    }

    public boolean isLastRewrite() {
        return mLastRewrite;
    }
    
    public void setLastRewrite(boolean lastRewrite) {
        mLastRewrite = lastRewrite;
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
}
