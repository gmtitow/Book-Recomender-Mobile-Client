package c.tgm.booksapplication.books;

public interface BookRepository {
    void getBooks(String query, Integer authorId, Long genreId, int page, int page_size, final boolean rewrite);
    
    void cancelRequest();
    
    void getRecommends(String query, Long genreId, int page, int page_size, final boolean rewrite);
    
    void getBookInfo(int book_id);
    
    void downloadFile(final String filename, final int bookId);
    
    void retry();
}
