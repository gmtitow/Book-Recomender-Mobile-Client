package c.tgm.booksapplication.repositories.promotion;

import java.util.List;

import c.tgm.booksapplication.models.request.promotion.PromotionAddRequest;
import c.tgm.booksapplication.repositories.Repository;

public interface PromotionRepository extends Repository {

    void getBooksForPromotion(int promotionId, int page, int pageSize);

    void getPromotions(int page, int pageSize);

    void deletePromotion(int promotionId);

    void addPromotion(String description, Long time_start, Long time_end,
                      List<PromotionAddRequest.BookDescription> descriptions);
}
