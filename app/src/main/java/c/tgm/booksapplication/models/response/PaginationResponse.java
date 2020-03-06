package c.tgm.booksapplication.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import c.tgm.booksapplication.models.data.Pagination;

abstract public class PaginationResponse<Data> extends AbstractResponse<Data> {
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;
    
    public Pagination getPagination() {
        return pagination;
    }
    
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
