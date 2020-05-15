package c.tgm.booksapplication.any;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import c.tgm.booksapplication.models.data.AuthData;
import c.tgm.booksapplication.models.data.DaoMaster;
import c.tgm.booksapplication.models.data.DaoSession;

/**
 * Created by Герман on 20.05.2018.
 */

public class DataStore {
    
    private static final String TAG = "DataStore";
    public static final String PREF_NAME = "bookprefs";
    public static final String PREF_CURRENT_USER_ID = "current_user_id";
    public static final String PREF_TOKEN = "token";
    
    public static final String PREF_SESSID = "phpsessid";
    
    public static final String PREF_TOKEN_LIFETIME = "token_lifetime";
    public static final String PREF_RULE = "rule";
    public static final String PREF_LOGIN = "login";
    public static final String PREF_PASSWORD = "password";
    
    static public final SimpleDateFormat mFormatForTokenLifetime =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.forLanguageTag("ru"));
    static public final SimpleDateFormat mFormatFromServer =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSZ", Locale.getDefault());
    private final String DB_NAME = "rast-db";

    static public final SimpleDateFormat mFormatForDisplay =
            new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.forLanguageTag("ru"));
    
    private DaoSession sDaoSession;
    private SharedPreferences preferences;
    
    public int getUserId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(PREF_CURRENT_USER_ID, -1);
    }
    
    public int getUserId() {
        return preferences.getInt(PREF_CURRENT_USER_ID, -1);
    }
    
    public void setUserId(int userId) {
        preferences.edit().putInt(PREF_CURRENT_USER_ID, userId).apply();
    }
    
//    public static String getToken(Context context) {
//        if(checkToken(preferences))
//            return preferences.getString(PREF_TOKEN, null);
//        else
//            return null;
//    }
    
    public String getSessionId() {
        return preferences.getString(PREF_SESSID, null);
    }
    
    public void setSessionId(String sessionId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_SESSID, sessionId).apply();
        editor.clear();
    }
    
    public String getToken() {
        if(preferences == null)
            Log.d(TAG,"preferences is null");
        
        if(checkToken(preferences)) {
            Log.d(TAG,"token is true");
            return preferences.getString(PREF_TOKEN, null);
        }
        else {
            Log.d(TAG,"token-а нет. Епт.");
            return null;
        }
    }
    
    public void setToken(Context context, String token) {
        String newToken = "";
        newToken+=token;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_TOKEN, newToken).apply();
        editor.clear();
    }
    
    public void setTokenLifetime(Context context, int lifetime) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(PREF_TOKEN_LIFETIME, lifetime).apply();
        editor.clear();
    }
    
    public void setRole(Context context, String role) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_RULE, role).apply();
        editor.clear();
    }
    
    public void setLogin(String login) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_LOGIN, login).apply();
        editor.clear();
    }
    
    public String getLogin() {
        return preferences.getString(PREF_LOGIN, null);
    }
    
    public String getRole() {
        return preferences.getString(PREF_RULE, null);
    }
    
    public void setAuthorizationInfo(Context context, AuthData authData, String login) {
        setToken(context,authData.getToken());
        setRole(context,authData.getRole());
        setTokenLifetime(context,authData.getLifeTime());
        setLogin(login);
        setUserId(authData.getUserId());
    }
    
    /*public static String getActivationCode(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(PREF_LOGIN,null);
    }
    
    public static String getPassword(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(PREF_PASSWORD,null);
    }*/
    
    public boolean isAuthorized() {
//        String token = preferences.getString(PREF_TOKEN, null);
//        String lifetime = preferences.getString(PREF_TOKEN_LIFETIME, null);
        boolean result = checkToken(preferences);
        Log.d(TAG,"Результат проверки токена = "+ result);
        return result;
    }
    
    public boolean isAuthorized(Context context) {
        //SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        //
//        String token = preferences.getString(PREF_TOKEN, null);
//        String lifetime = preferences.getString(PREF_TOKEN_LIFETIME, null);
        
        //Toast.makeText(context,token!=null?token:"null",Toast.LENGTH_SHORT).show();
        //Toast.makeText(context,lifetime!=null?lifetime:"null",Toast.LENGTH_SHORT).show();
        //
        return checkToken(preferences);
    }
    
    private boolean checkToken(SharedPreferences preferences){
        String token = preferences.getString(PREF_TOKEN, null);
        int lifetime = preferences.getInt(PREF_TOKEN_LIFETIME, 0);
    
        Log.d(TAG,"Зашел в checkToken");
        Log.d(TAG,"token="+(token==null?"null":token));
        Log.d(TAG,"lifetime="+(lifetime==0?"null":lifetime));
    
        if (token != null && lifetime != 0) {
            try {
                Date date = new Date();

                date.setTime(lifetime*1000L);

                Log.d(TAG,"lifetime в переводе="+date.toString());
                Date now = new Date();
            
                if(date.after(now)){
                    return true;
                } else{
                    Log.d(TAG,"Время токена вышло! Надо его удалить, блин lifetime = "+(lifetime==0?"null":lifetime));
                    preferences.edit().remove(PREF_TOKEN).apply();
                    preferences.edit().remove(PREF_TOKEN_LIFETIME).apply();
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG,"Ошибка проверки даты. Токен также удален.");
                preferences.edit().remove(PREF_TOKEN).apply();
                preferences.edit().remove(PREF_TOKEN_LIFETIME).apply();
                return false;
            }
        }
        return false;
    }
    
    public void clearDB() {
    }
    
    public void clearPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Log.d(TAG,"Очистил preferences");
        preferences.edit().clear().apply();
    }
    
    public void clearAll(Context context) {
        clearDB();
        clearPreferences(context);
    }
    
    public DataStore(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        Database db = helper.getWritableDb();
        sDaoSession = new DaoMaster(db).newSession();
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
    
    public DaoSession getDaoSession() {
        return sDaoSession;
    }
    
//    public static void clearDbToNewServiceList() {
//        BookApplication.getDataStore().getDaoSession().getServiceDao().deleteAll();
//        BookApplication.getDataStore().getDaoSession().getServicesImagesDao().deleteAll();
//        BookApplication.getDataStore().getDaoSession().getTradePointDao().deleteAll();
//        BookApplication.getDataStore().getDaoSession().getServicesCategoriesDao().deleteAll();
//    }
}
