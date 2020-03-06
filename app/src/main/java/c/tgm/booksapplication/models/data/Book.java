package c.tgm.booksapplication.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Book {
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
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    @SerializedName("rating")
    @Expose
    private Double rating;
    
    @SerializedName("image_path")
    @Expose
    private String imagePath;
    
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
    
    public List<Genre> getGenres() {
        return genres;
    }
    
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
    
    public Double getRating() {
        if (rating == null)
            rating = 0.0;
        return rating;
    }
    
    public void setRating(Double rating) {
        this.rating = rating;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
