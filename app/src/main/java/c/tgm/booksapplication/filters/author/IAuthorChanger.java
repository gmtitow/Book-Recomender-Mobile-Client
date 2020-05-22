package c.tgm.booksapplication.filters.author;

import java.io.Serializable;

import c.tgm.booksapplication.models.data.Author;

public interface IAuthorChanger extends Serializable {
    void onChangeAuthor(Author author);
}
