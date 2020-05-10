package c.tgm.booksapplication.promotion.list;

import c.tgm.booksapplication.models.data.Promotion;
import c.tgm.booksapplication.pagination.IPaginationView;

public interface PromotionView extends IPaginationView<Promotion> {
    void setEnable(boolean enable);
}
