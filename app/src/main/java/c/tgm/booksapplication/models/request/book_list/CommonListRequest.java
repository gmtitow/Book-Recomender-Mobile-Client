package c.tgm.booksapplication.models.request.book_list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import c.tgm.booksapplication.models.request.AbstractRequest;

public class CommonListRequest extends AbstractRequest {
    @SerializedName("list_name")
    @Expose
    private String list_name;

    @SerializedName("list_id")
    @Expose
    private Integer list_id = null;

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public CommonListRequest(String list_name) {
        this.list_name = list_name;
    }

    public void setList_id(int list_id) {
        this.list_id = list_id;
    }

    public CommonListRequest(String list_name, int list_id) {
        this.list_name = list_name;
        this.list_id = list_id;
    }

    public CommonListRequest(Integer list_id) {
        this.list_id = list_id;
    }
}
