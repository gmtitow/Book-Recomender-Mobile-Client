package c.tgm.booksapplication.filters;

import java.io.Serializable;

public class FilterViewOptions implements Serializable {

    private boolean authorGone;
    private boolean genreGone;
    private boolean bookListsGone;

    private String handler;

    public FilterViewOptions(boolean authorGone, boolean genreGone, boolean bookListsGone) {
        this.authorGone = authorGone;
        this.genreGone = genreGone;
        this.bookListsGone = bookListsGone;
    }

    public FilterViewOptions(boolean authorGone, boolean genreGone, boolean bookListsGone, String handler) {
        this.authorGone = authorGone;
        this.genreGone = genreGone;
        this.bookListsGone = bookListsGone;
        this.handler = handler;
    }

    public FilterViewOptions() {
        this.authorGone = false;
        this.genreGone = false;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public boolean isAuthorGone() {
        return authorGone;
    }

    public void setAuthorGone(boolean authorGone) {
        this.authorGone = authorGone;
    }

    public boolean isGenreGone() {
        return genreGone;
    }

    public void setGenreGone(boolean genreGone) {
        this.genreGone = genreGone;
    }

    public boolean isBookListsGone() {
        return bookListsGone;
    }

    public void setBookListsGone(boolean bookListsGone) {
        this.bookListsGone = bookListsGone;
    }
}
