package c.tgm.booksapplication.books.list;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.models.data.Book;

public class BookListModel {
    private Integer authorId;
    
    private Long genreId;
    
    private ArrayList<Book> curBooks = new ArrayList<>();
    
    private int curPage = 1;
    
    private int pageSize = 20;
    
    private boolean loading = false;
    
    public Integer getAuthorId() {
        return authorId;
    }
    
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
    
    public Long getGenreId() {
        return genreId;
    }
    
    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
    
    public ArrayList<Book> getCurBooks() {
        return curBooks;
    }
    
    public void setCurBooks(List<Book> curBooks) {
        this.curBooks.clear();
        this.curBooks.addAll(curBooks);
    }
    
    public void addBooks(List<Book> curBooks) {
        this.curBooks.addAll(curBooks);
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
