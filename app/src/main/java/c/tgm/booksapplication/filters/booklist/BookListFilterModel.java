package c.tgm.booksapplication.filters.booklist;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.models.data.BookList;

public class BookListFilterModel {
    
    private ArrayList<BookList> lists = new ArrayList<>();

    private IBookListChanger changer;
    
    private int curPage = 1;
    
    private int pageSize = 20;
    
    private boolean loading = false;
    
    private String mLastQuery;
    
    private boolean mLastRewrite;

    public IBookListChanger getChanger() {
        return changer;
    }

    public void setChanger(IBookListChanger changer) {
        this.changer = changer;
    }

    public boolean isLastRewrite() {
        return mLastRewrite;
    }
    
    public void setLastRewrite(boolean lastRewrite) {
        mLastRewrite = lastRewrite;
    }
    
    public ArrayList<BookList> getLists() {
        return lists;
    }
    
    public void setLists(List<BookList> lists) {
        this.lists.clear();
        this.lists.addAll(lists);
    }
    
    public String getLastQuery() {
        return mLastQuery;
    }
    
    public void setLastQuery(String lastQuery) {
        mLastQuery = lastQuery;
    }
    
    public void addAuthors(List<BookList> curBooks) {
        this.lists.addAll(curBooks);
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
