package c.tgm.booksapplication.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import c.tgm.booksapplication.models.response.AbstractResponse;

public class BookShort {
    @SerializedName("book_id")
    @Expose
    private Integer bookId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("author_name")
    @Expose
    private String authorName;
    
    public Integer getBookId() {
        return bookId;
    }
    
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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
