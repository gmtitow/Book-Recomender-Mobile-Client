package c.tgm.booksapplication.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class AbstractResponse<SomeData> implements Serializable {
    @SerializedName("error")
    @Expose
    private Integer error;
    
    @SerializedName("error_description")
    @Expose
    private String errorDescription;
    
    @SerializedName("success")
    @Expose
    private Boolean success;
    
    @SerializedName("msg")
    @Expose
    private String msg;
    
    @SerializedName("data")
    @Expose
    private SomeData data;
    
    public Integer getError() {
        return error;
    }
    
    public void setError(Integer error) {
        this.error = error;
    }
    
    public String getErrorDescription() {
        return errorDescription;
    }
    
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
    
    public Boolean getSuccess() {
        return success;
    }
    
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public SomeData getData() {
        return data;
    }
    
    public void setData(SomeData data) {
        this.data = data;
    }
}
