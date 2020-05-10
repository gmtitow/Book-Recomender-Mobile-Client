package c.tgm.booksapplication.promotion.add;

import java.util.Calendar;
import java.util.List;

import c.tgm.booksapplication.models.request.promotion.PromotionAddRequest;

public class PromotionAddModel {
    private Calendar timeStart = null;

    private Calendar timeEnd = null;

    private String description;

    private List<PromotionAddRequest.BookDescription> bookDescriptions;

    public Calendar getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Calendar timeStart) {
        this.timeStart = timeStart;
    }

    public Calendar getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Calendar timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PromotionAddRequest.BookDescription> getBookDescriptions() {
        return bookDescriptions;
    }

    public void setBookDescriptions(List<PromotionAddRequest.BookDescription> bookDescriptions) {
        this.bookDescriptions = bookDescriptions;
    }
}
