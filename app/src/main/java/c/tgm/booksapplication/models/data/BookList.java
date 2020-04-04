package c.tgm.booksapplication.models.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class BookList {
    @SerializedName("list_id")
    @Expose
    @Id
    private Long _id;
    @SerializedName("list_name")
    @Expose
    private String listName;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("is_main")
    @Expose
    private Boolean isMain;

    @NonNull
    @Override
    public String toString() {
        return listName;
    }

    @Generated(hash = 660840919)
    public BookList(Long _id, String listName, Integer userId, String createdAt,
            Boolean isMain) {
        this._id = _id;
        this.listName = listName;
        this.userId = userId;
        this.createdAt = createdAt;
        this.isMain = isMain;
    }

    @Generated(hash = 1714324117)
    public BookList() {
    }
    
    public Long getListId() {
        return _id;
    }

    public void setListId(Long listId) {
        this._id = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsMain() {
        return isMain;
    }

    public void setIsMain(Boolean isMain) {
        this.isMain = isMain;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }
}
