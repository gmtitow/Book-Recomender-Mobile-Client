package c.tgm.booksapplication.promotion.list;

import c.tgm.booksapplication.models.data.Promotion;
import c.tgm.booksapplication.pagination.APaginationModel;

public class PromotionListModel extends APaginationModel<Promotion> {
    public void deletePromotionById(int promotionId) {
        for (int i = 0; i < getObjects().size(); i++) {
            if (getObjects().get(i).getPromotionId() == promotionId) {
                getObjects().remove(i);
                break;
            }
        }
    }
}
