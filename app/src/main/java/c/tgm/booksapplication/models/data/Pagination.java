package c.tgm.booksapplication.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pagination implements Serializable {
    @SerializedName("total")
    @Expose
    private int total;
    
    public int getTotal() {
        return total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
    
    public void increaseTotal(){
        this.total++;
    }
    
    public void decreaseTotal(){
        this.total--;
    }
}
