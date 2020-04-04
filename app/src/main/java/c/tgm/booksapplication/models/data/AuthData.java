package c.tgm.booksapplication.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AuthData {
    
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("lifetime_int")
    @Expose
    private int lifeTime;
    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("lists")
    @Expose
    private ArrayList<BookList> lists;
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public int getLifeTime() {
        return lifeTime;
    }
    
    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<BookList> getLists() {
        return lists;
    }

    public void setLists(ArrayList<BookList> lists) {
        this.lists = lists;
    }
}
