package c.tgm.booksapplication.models.request.promotion;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import c.tgm.booksapplication.models.request.AbstractRequest;

public class PromotionAddRequest extends AbstractRequest {
    @SerializedName("time_start")
    @Expose
    private Integer time_start;

    @SerializedName("time_end")
    @Expose
    private Integer time_end;

    @SerializedName("description")
    @Expose
    private String description = null;

    @SerializedName("book_descriptions")
    @Expose
    private List<BookDescription> book_descriptions;

    public Integer getTime_start() {
        return time_start;
    }

    public void setTime_start(Integer time_start) {
        this.time_start = time_start;
    }

    public Integer getTime_end() {
        return time_end;
    }

    public void setTime_end(Integer time_end) {
        this.time_end = time_end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BookDescription> getBook_descriptions() {
        return book_descriptions;
    }

    public void setBook_descriptions(List<BookDescription> book_descriptions) {
        this.book_descriptions = book_descriptions;
    }

    public PromotionAddRequest(Integer time_start, Integer time_end, String description, List<BookDescription> book_descriptions) {
        this.time_start = time_start;
        this.time_end = time_end;
        this.description = description;
        this.book_descriptions = book_descriptions;
    }

    public PromotionAddRequest(Integer time_start, Integer time_end, List<BookDescription> book_descriptions) {
        this.time_start = time_start;
        this.time_end = time_end;
        this.book_descriptions = book_descriptions;
    }
}
