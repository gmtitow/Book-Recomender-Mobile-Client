package c.tgm.booksapplication.review.list;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.Review;
import c.tgm.booksapplication.models.data.ReviewWithBook;

public class ReviewListModel {
    
    private ArrayList<ReviewWithBook> curReviews = new ArrayList<>();
    
    private int curPage = 1;
    
    private int pageSize = 20;
    
    private boolean loading = false;
    
    public ArrayList<ReviewWithBook> getCurReviews() {
        return curReviews;
    }
    
    public void setCurReviews(List<ReviewWithBook> curReviews) {
        this.curReviews.clear();
        this.curReviews.addAll(curReviews);
    }
    
    public void addReviews(List<ReviewWithBook> curBooks) {
        this.curReviews.addAll(curBooks);
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
