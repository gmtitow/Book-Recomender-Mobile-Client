package c.tgm.booksapplication.books;

import c.tgm.booksapplication.repositories.Repository;

public interface BookRepository extends Repository {
    void getBooks(String query, Integer authorId, Long genreId, int page, int page_size);
    
    void cancelRequest();
    
    void getRecommends(String query, Long listId, Long genreId, int page, int page_size);
    
    void getBookInfo(int book_id);

    void updateRecommendList(int listId);

    void downloadFile(final String filename, final int bookId);
    
    void retry();
}
