package c.tgm.booksapplication.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Id;

import java.util.List;

import c.tgm.booksapplication.models.response.AbstractResponse;

public class BookShort {
    @SerializedName("book_id")
    @Expose
    @Id
    protected Long _id;
    @SerializedName("name")
    @Expose
    protected String name;
    @SerializedName("description")
    @Expose
    protected String description;
    @SerializedName("author_name")
    @Expose
    protected String authorName;
    
    public Long getBookId() {
        return _id;
    }
    
    public void setBookId(Long bookId) {
        this._id = bookId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getAuthorName() {
        return authorName;
    }
    
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
