package c.tgm.booksapplication.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
public class Genre implements Serializable {

    public static final long serialVersionUID = 536871008;

    @SerializedName("genre_id")
    @Expose
    @Id
    private Long _id;
    @SerializedName("genre_name")
    @Expose
    private String genreName;

    @Generated(hash = 235763487)
    public Genre() {
    }

    @Generated(hash = 2071240567)
    public Genre(Long _id, String genreName) {
        this._id = _id;
        this.genreName = genreName;
    }
    
    public Long getGenreId() {
        return _id;
    }
    
    public void setGenreId(Long genreId) {
        this._id = genreId;
    }
    
    public String getGenreName() {
        return genreName;
    }
    
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }
    
    @Override
    public String toString() {
        return getGenreName();
    }
}
