package c.tgm.booksapplication.any;

import android.content.Context;
import android.content.Intent;

import c.tgm.booksapplication.authentication.AuthenticationActivity;

public class ActivityGlobalNavigator {
    public static final String AUTHENTICATION_ACTIVITY = "AuthenticationActivity";
    
    private Context mContext;
    
    public ActivityGlobalNavigator(Context context) {
        this.mContext = context;
    }
    
    public void goToActivity(String activityKey){
        Intent intent;
        switch(activityKey){
            case AUTHENTICATION_ACTIVITY:
                intent = AuthenticationActivity.getInstance(mContext);
                mContext.startActivity(intent);
                break;
            default:
                throw new RuntimeException("Unknown activity key!!");
        }
    }
    
    public void goToActivity(String activityKey, Object data){
        Intent intent;
        switch(activityKey){
            case AUTHENTICATION_ACTIVITY:
                intent = AuthenticationActivity.getInstance(mContext,(String)data);
                mContext.startActivity(intent);
                break;
            default:
                throw new RuntimeException("Unknown activity key!!");
        }
    }
}
