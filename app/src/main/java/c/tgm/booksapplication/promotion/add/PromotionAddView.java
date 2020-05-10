package c.tgm.booksapplication.promotion.add;

import com.arellomobile.mvp.MvpView;

import java.util.Calendar;

import c.tgm.booksapplication.AbstractView;

public interface PromotionAddView extends AbstractView {
    void onSetTimeEnd(Calendar timeEnd);
    void onSetTimeStart(Calendar timeStart);
}
