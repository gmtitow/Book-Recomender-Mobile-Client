package c.tgm.booksapplication.any;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Герман on 20.05.2018.
 */

public class DataStoreTemp {
    
    private static final String TAG = "DataStoreTemp";
    public static final String PREF_NAME = "rastprefstemp";
    
    //Мелочь
    public static final String PREF_LAST_TIME_ACTIVATION_CODE_SENT = "last_time_by_activation_code_sent";
    public static final String PREF_LAST_TIME_RESET_PASSWORD_CODE_SENT = "last_time_by_reset_password_code_sent";
    
    public static final String PREF_LOGIN_SUFFIX = "login_temp_preferences";
    
    public static final int ACTIVATION_CODE_RESEND_TIME = 300000;
    public static final int RESET_PASSWORD_CODE_RESEND_TIME = 300000;
    
    static public final SimpleDateFormat mFormatFromServer = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.getDefault());
    
    private final String DB_NAME = "rast-db";
    
    private static SharedPreferences preferences;
    
    public static Long getLastTimeForActivationCode() {
        return preferences.getLong(PREF_LAST_TIME_ACTIVATION_CODE_SENT, 0);
    }
    
    public static Long getLastTimeForResetPasswordCode() {
        return preferences.getLong(PREF_LAST_TIME_RESET_PASSWORD_CODE_SENT, 0);
    }
    
    public static void setLastTimeForResetPasswordCode(long lasttime) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(PREF_LAST_TIME_RESET_PASSWORD_CODE_SENT, lasttime).apply();
        editor.clear();
    }
    
    public static void setLastTimeForActivationCode(long lasttime) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(PREF_LAST_TIME_ACTIVATION_CODE_SENT, lasttime).apply();
        editor.clear();
    }
    
    public static void clearPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Log.d(TAG,"Очистил preferences");
        preferences.edit().clear().apply();
    }
    
   
    public DataStoreTemp(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
}
