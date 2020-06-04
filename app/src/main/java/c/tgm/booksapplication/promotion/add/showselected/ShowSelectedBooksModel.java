package c.tgm.booksapplication.promotion.add.showselected;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.request.promotion.BookDescription;
import c.tgm.booksapplication.pagination.APaginationModel;
import c.tgm.booksapplication.promotion.add.selectbooks.IBookDescriptionRemember;

public class ShowSelectedBooksModel extends APaginationModel<Book> {
    private IBookDescriptionRemember remember;

    private IBookDescriptionRemember rememberBack;

    private List<BookDescription> descriptions;

    public IBookDescriptionRemember getRemember() {
        return remember;
    }

    public void setRemember(IBookDescriptionRemember remember) {
        this.remember = remember;
    }

    public List<BookDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<BookDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public IBookDescriptionRemember getRememberBack() {
        return rememberBack;
    }

    public void setRememberBack(IBookDescriptionRemember rememberBack) {
        this.rememberBack = rememberBack;
    }
}
