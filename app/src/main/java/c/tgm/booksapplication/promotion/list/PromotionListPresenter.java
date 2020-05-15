package c.tgm.booksapplication.promotion.list;

import java.util.List;

import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.models.data.Promotion;
import c.tgm.booksapplication.pagination.APaginationPresenter;
import c.tgm.booksapplication.repositories.RepositoryCall;
import c.tgm.booksapplication.repositories.book_list.BookListRepository;
import c.tgm.booksapplication.repositories.promotion.PromotionRepo;
import c.tgm.booksapplication.repositories.promotion.PromotionRepositoryImpl;

public class PromotionListPresenter extends APaginationPresenter<PromotionView, Promotion, PromotionListModel>
        implements PromotionRepo {

    private PromotionRepositoryImpl mRepository;

    private int lastPromotionId;

    @Override
    protected void initializeModel() {
        mModel = new PromotionListModel();
    }

    @Override
    protected void initializeRepository() {
        mRepository = new PromotionRepositoryImpl(this);
    }

    @Override
    protected void getNewObjects(boolean add) {
        if (!getModel().isLoading()) {
            getModel().setAdd(add);
            getModel().setLoading(true);
            mRepository.getPromotions(getModel().getCurPage(), getModel().getPageSize());
        }
    }

    public void goToAddPromotion() {
        navigateTo(new Screens.PromotionScreens(Screens.PromotionScreens.ADD_SCREEN));
    }

    @Override
    public void onError(String errorDescription, RepositoryCall call) {
        call.call();
        getView().showMessage(errorDescription);
    }

    @Override
    public void onGetPromotions(List<Promotion> promotions) {
        if (mModel.isAdd())
            mModel.addObjects(promotions);
        else
            mModel.setObjects(promotions);

        getModel().setLoading(false);
        getView().setEnable(true);

        getView().updateList(mModel.getObjects());
    }

    public void deletePromotion(int promotionId) {
        if (!getModel().isLoading()) {
            getModel().setLoading(true);
            mRepository.deletePromotion(promotionId);
            getView().setEnable(false);
            lastPromotionId = promotionId;
        }
    }

    @Override
    public void onDeletePromotion(String message) {
        getModel().setLoading(false);
        getView().setEnable(true);
        getModel().deletePromotionById(lastPromotionId);
        getView().updateList(getModel().getObjects());
        getView().showMessage("Акция была удалена");
    }
}
