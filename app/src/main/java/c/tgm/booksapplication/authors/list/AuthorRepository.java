package c.tgm.booksapplication.authors.list;

public interface AuthorRepository {
    void getAuthors(String query, Long genreId, int page, int page_size, final boolean rewrite);
    
    void cancelRequest();
    
    void retry();
}
