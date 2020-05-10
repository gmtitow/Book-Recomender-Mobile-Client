package c.tgm.booksapplication.promotion.add;

import java.util.Calendar;

import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.models.data.Promotion;
import c.tgm.booksapplication.repositories.RepositoryCall;
import c.tgm.booksapplication.repositories.promotion.PromotionRepo;
import c.tgm.booksapplication.repositories.promotion.PromotionRepository;
import c.tgm.booksapplication.repositories.promotion.PromotionRepositoryImpl;

public class PromotionAddPresenter extends NavigatorPresenter<PromotionAddView> implements PromotionRepo {

    private PromotionRepository mRepository;
    private PromotionAddModel mModel;

    public PromotionAddPresenter() {
        mModel = new PromotionAddModel();
        mRepository = new PromotionRepositoryImpl(this);
    }

    public PromotionAddModel getModel() {
        return mModel;
    }

    public void setTimeStart(Calendar timeStart) {
        mModel.setTimeStart(timeStart);
    }

    public void setTimeEnd(Calendar timeEnd) {
        mModel.setTimeEnd(timeEnd);

        getViewState().onSetTimeEnd(mModel.getTimeEnd());
    }

    public Calendar getTimeEnd() {
        return mModel.getTimeEnd();
    }

    public Calendar getTimeStart() {
        return mModel.getTimeStart();
    }

    public String getDescription() {
        return mModel.getDescription();
    }

    public void setDescription(String description) {
        mModel.setDescription(description);
    }

    public void selectBooks() {

    }

    public void createPromotion(String description)
    {
        mRepository.addPromotion(description,mModel.getTimeStart().getTime().getTime()/1000,
                mModel.getTimeEnd().getTime().getTime()/1000,mModel.getBookDescriptions());
    }

    @Override
    public void onError(String errorDescription, RepositoryCall call) {
        call.call();
        getView().showMessage(errorDescription);
    }

    @Override
    public void onAddPromotion(Promotion promotion) {

    }
}
