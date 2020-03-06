package c.tgm.booksapplication.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import c.tgm.booksapplication.models.data.Pagination;

public class OnlyPaginationResponse<SomeData> implements Serializable {
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;
    
    @SerializedName("data")
    @Expose
    private SomeData data;
    
    public SomeData getData() {
        return data;
    }
    
    public void setData(SomeData data) {
        this.data = data;
    }
    
    public Pagination getPagination() {
        return pagination;
    }
    
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
