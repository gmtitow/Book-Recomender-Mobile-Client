package c.tgm.booksapplication.models.request.book_list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import c.tgm.booksapplication.models.request.AbstractRequest;

public class CommonBookListRelationRequest extends AbstractRequest {

    @SerializedName("list_id")
    @Expose
    private Integer list_id = null;

    @SerializedName("book_id")
    @Expose
    private Integer book_id = null;

    @SerializedName("rating")
    @Expose
    private Integer rating = null;

    public void setListId(Integer list_id) {
        this.list_id = list_id;
    }

    public void setBookId(Integer book_id) {
        this.book_id = book_id;
    }

    public void setRating(Integer rating) {
        if (rating < 0 || rating > 10)
            throw new RuntimeException("Rating must be more then 0 and less then 10");
        this.rating = rating;
    }

    public CommonBookListRelationRequest(Integer list_id, Integer book_id, Integer rating) {
        this.setListId(list_id);
        this.setBookId(book_id);
        this.setRating(rating);
    }

    public CommonBookListRelationRequest(Integer list_id, Integer book_id) {
        this.setListId(list_id);
        this.setBookId(book_id);
    }
}
