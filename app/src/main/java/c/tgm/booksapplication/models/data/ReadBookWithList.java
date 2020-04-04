package c.tgm.booksapplication.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class ReadBookWithList {

    @SerializedName("book_id")
    @Expose
    @Id
    protected Long _id;

    @SerializedName("list_id")
    @Expose
    private int listId;

    @SerializedName("rating")
    @Expose
    protected int rating;

    @SerializedName("image_path")
    @Expose
    protected String imagePath;

    @SerializedName("name")
    @Expose
    protected String name;

    @SerializedName("description")
    @Expose
    protected String description;

    @SerializedName("author_name")
    @Expose
    protected String authorName;

    @Generated(hash = 1936941656)
    public ReadBookWithList(Long _id, int listId, int rating, String imagePath,
                            String name, String description, String authorName) {
        this._id = _id;
        this.listId = listId;
        this.rating = rating;
        this.imagePath = imagePath;
        this.name = name;
        this.description = description;
        this.authorName = authorName;
    }

    @Generated(hash = 500545801)
    public ReadBookWithList() {
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public ReadBook toReadBook() {
        ReadBook book = new ReadBook();
        book.setBookId(getBookId());
        book.setName(getName());
        book.setAuthorName(getAuthorName());
        book.setDescription(getDescription());
        book.setImagePath(getImagePath());
        book.setRating(getRating());
        return book;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getBookId() {
        return this._id;
    }

    public void setBookId(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }
}
