package c.tgm.booksapplication.promotion.add.selectbooks;

import java.io.Serializable;
import java.util.List;

import c.tgm.booksapplication.models.request.promotion.BookDescription;
import c.tgm.booksapplication.models.request.promotion.PromotionAddRequest;

public interface IBookDescriptionRemember extends Serializable {
    void rememberBookDescriptions(List<BookDescription>descriptions);
}
