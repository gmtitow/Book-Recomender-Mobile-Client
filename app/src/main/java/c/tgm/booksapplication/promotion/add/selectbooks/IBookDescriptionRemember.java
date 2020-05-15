package c.tgm.booksapplication.promotion.add.selectbooks;

import java.util.List;

import c.tgm.booksapplication.models.request.promotion.PromotionAddRequest;

public interface IBookDescriptionRemember {
    void rememberBookDescriptions(List<PromotionAddRequest.BookDescription>descriptions);
}
