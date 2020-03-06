package c.tgm.booksapplication.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AuthData {
    
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("life_time")
    @Expose
    private String lifeTime;
    @SerializedName("role")
    @Expose
    private String role;
    
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
    
    public String getLifeTime() {
        return lifeTime;
    }
    
    public void setLifeTime(String lifeTime) {
        this.lifeTime = lifeTime;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
}
