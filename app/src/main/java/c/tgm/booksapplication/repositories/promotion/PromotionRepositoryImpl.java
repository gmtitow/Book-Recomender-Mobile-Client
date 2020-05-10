package c.tgm.booksapplication.repositories.promotion;

import java.util.HashMap;
import java.util.List;

import c.tgm.booksapplication.api.BookListsAPI;
import c.tgm.booksapplication.api.PromotionAPI;
import c.tgm.booksapplication.models.request.promotion.PromotionAddRequest;
import c.tgm.booksapplication.models.response.BooksResponse;
import c.tgm.booksapplication.models.response.CommonResponse;
import c.tgm.booksapplication.models.response.PromotionListResponse;
import c.tgm.booksapplication.models.response.PromotionResponse;
import c.tgm.booksapplication.repositories.RepositoryCallImpl;
import c.tgm.booksapplication.repositories.RepositoryImpl;
import c.tgm.booksapplication.repositories.book_list.BookListRepo;

public class PromotionRepositoryImpl extends RepositoryImpl implements PromotionRepository {

    private PromotionRepo mHandler;
    private PromotionAPI mApi;

    public PromotionRepositoryImpl(PromotionRepo handler) {
        this.mHandler = handler;
        mApi = getAPI(PromotionAPI.class);
    }

    @Override
    public void getBooksForPromotion(int promotionId, int page, int pageSize) {
        RepositoryCallImpl call = new RepositoryCallImpl<>(mApi.getBooks(promotionId,page,pageSize),
                BooksResponse.class,
                mHandler::onGetBookForPromotion, mHandler::onError, this);
        call.call();
    }

    @Override
    public void getPromotions(int page, int pageSize) {
        RepositoryCallImpl call = new RepositoryCallImpl<>(mApi.get(page,pageSize), PromotionListResponse.class,
                mHandler::onGetPromotions, mHandler::onError, this);
        call.call();
    }

    @Override
    public void deletePromotion(int promotionId) {
        HashMap<String, Object> map = new HashMap();
        map.put("promotion_id",promotionId);

        RepositoryCallImpl call = new RepositoryCallImpl<>(mApi.delete(map), CommonResponse.class,
                mHandler::onDeletePromotion, mHandler::onError, this);
        call.call();
    }

    @Override
    public void addPromotion(String description, Long time_start, Long time_end,
                             List<PromotionAddRequest.BookDescription> descriptions) {
        PromotionAddRequest request = new PromotionAddRequest(time_start.intValue(),time_end.intValue(),description,descriptions);
        RepositoryCallImpl call = new RepositoryCallImpl<>(mApi.add(request), PromotionResponse.class,
                mHandler::onAddPromotion, mHandler::onError, this);
        call.call();
    }
}
