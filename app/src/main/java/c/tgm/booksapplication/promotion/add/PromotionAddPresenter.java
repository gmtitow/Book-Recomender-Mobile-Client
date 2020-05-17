package c.tgm.booksapplication.promotion.add;

import com.arellomobile.mvp.InjectViewState;

import java.util.Calendar;
import java.util.List;

import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.Screens;
import c.tgm.booksapplication.models.data.Promotion;
import c.tgm.booksapplication.models.request.promotion.BookDescription;
import c.tgm.booksapplication.models.request.promotion.PromotionAddRequest;
import c.tgm.booksapplication.promotion.add.selectbooks.IBookDescriptionRemember;
import c.tgm.booksapplication.repositories.RepositoryCall;
import c.tgm.booksapplication.repositories.promotion.PromotionRepo;
import c.tgm.booksapplication.repositories.promotion.PromotionRepository;
import c.tgm.booksapplication.repositories.promotion.PromotionRepositoryImpl;

@InjectViewState
public class PromotionAddPresenter extends NavigatorPresenter<PromotionAddView> implements PromotionRepo, IBookDescriptionRemember {

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

        getViewState().onSetTimeStart(mModel.getTimeStart());
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
        navigateTo(new Screens.PromotionScreens(Screens.PromotionScreens.SELECT_BOOK_SCREEN,this,getModel().getBookDescriptions()));
    }

    public void createPromotion(String description)
    {
        if (mModel.getTimeStart()!=null && mModel.getTimeEnd()!=null) {
            mRepository.addPromotion(description, mModel.getTimeStart().getTime().getTime() / 1000,
                    mModel.getTimeEnd().getTime().getTime() / 1000, mModel.getBookDescriptions());
        }
    }

    @Override
    public void onError(String errorDescription, RepositoryCall call) {
        call.call();
        getView().showMessage(errorDescription);
    }

    @Override
    public void onAddPromotion(Promotion promotion) {
        exit();
    }

    public void setBookDescriptions(List<BookDescription> descriptions) {
        getModel().setBookDescriptions(descriptions);
    }

    @Override
    public void rememberBookDescriptions(List<BookDescription> descriptions) {
        getModel().setBookDescriptions(descriptions);
    }
}
