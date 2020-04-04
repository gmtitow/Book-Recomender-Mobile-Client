package c.tgm.booksapplication.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import c.tgm.booksapplication.BookApplication;
import c.tgm.booksapplication.any.DataStore;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by NorD on 16.11.2017.
 */

public class Controller implements Serializable {
    
    protected static OkHttpClient client = null;
    
    private static final String TAG = "Controller";
    
    public Controller(final Context context) {
        if (client == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            client = builder.followRedirects(false).cookieJar(new CookieJar() {
                private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    if (cookieStore.get(url.host()) != null) {
                        List<Cookie> curCookies = cookieStore.get(url.host());
                        List<Cookie> result = new ArrayList<Cookie>();
                        result.addAll(curCookies);
                        int cur_size = curCookies.size();
                        for (int j = 0; j < cookies.size(); j++) {
                            for (int i = 0; i < cur_size; i++) {
                                if (result.get(i).name().equals(cookies.get(j).name())) {
                                    result.remove(i);
                                    cur_size -= 1;
                                    i--;
                                }
                            }
                            result.add(cookies.get(j));
                        }
                        cookieStore.put(url.host(), result);
                    } else {
                        cookieStore.put(url.host(), cookies);
                    }
    
                    for (int j = 0; j < cookies.size(); j++) {
                        if(cookies.get(j).name().equals("PHPSESSID"))
                            Log.d(TAG,"Записал id сессии ="+cookies.get(j).value()+" в datastore");
                            BookApplication.INSTANCE.getDataStore().setSessionId(cookies.get(j).value());
                    }
                }
                
                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> cookies = cookieStore.get(url.host());
                    
                    if(cookies == null)
                        cookies = new ArrayList<Cookie>();
                    
                    if(cookies.size() == 0){
                        if(BookApplication.INSTANCE.getDataStore().getSessionId()!=null) {
                            Log.d(TAG,"Записал в куки из DataStore id сессии = "+BookApplication.INSTANCE.getDataStore().getSessionId());
                            Cookie cookie = Cookie.parse(url, "PHPSESSID=" + BookApplication.INSTANCE.getDataStore().getSessionId());
                            cookies.add(cookie);
                        }
                    }
                    
                    return cookies;
                }
            }).connectTimeout(30, TimeUnit.SECONDS)/*.authenticator(new Authenticator() {
                @Override
                public Request authenticate(Route route, Response response) throws IOException {
                    
                    serviceAPI api = getGsonAPI();
                    
                    if(BookApplication.INSTANCE.getDataStore().getActivationCode(context)!= null || BookApplication.INSTANCE.getDataStore().getPassword(context)!=null)
                        return null;
                    
                    AuthData data = api.login(BookApplication.INSTANCE.getDataStore().getActivationCode(context), decrypt(BookApplication.INSTANCE.getDataStore().getPassword(context),context))
                            .execute().body();
                    
                    BookApplication.INSTANCE.getDataStore().setToken(context,data.getToken());
                    
                    return response.request().newBuilder()
                            .header("Authorization", data.getToken())
                            .build();
                }
            })*/.addInterceptor(new TokenInterceptor()).build();
        }
    }
    
//    @Nullable
//    private String crypt(String pass, Context context) {
//        byte[] key = generateKey(context);
//        if(key == null){
//            return null;
//        }
//        try {
//            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
//            return byteArrToString(cipher.doFinal(pass.getBytes()));
//        }catch(Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
    
//    @Nullable
//    private String decrypt(String cryptedPass, Context context) {
//        byte[] key = generateKey(context);
//        if(key == null){
//            return null;
//        }
//        try {
//            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
//            return byteArrToString(cipher.doFinal(cryptedPass.getBytes()));
//        }catch(Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
    
//    @Nullable
//    private byte[] generateKey(Context context){
//        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        try {
//            String imei = telephonyManager.getDeviceId();
//            String phoneModel = android.os.Build.MODEL;
//            String androidVersion = android.os.Build.VERSION.RELEASE;
//            KeyGenerator kgen = KeyGenerator.getInstance("AES");
//            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
//
//            sr.setSeed(md5((imei + phoneModel + androidVersion)).getBytes());
//            kgen.init(128, sr);
//
//            SecretKey skey = kgen.generateKey();
//            return skey.getEncoded();
//        } catch (SecurityException e) {
//            e.printStackTrace();
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    
    @NonNull
    private String byteArrToString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            hexString.append(Integer.toHexString(0xFF & b[i]));
        return hexString.toString();
    }
    
    @Nullable
    private String md5(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            
            return byteArrToString(messageDigest);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private static class TokenInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            
            if (!BookApplication.INSTANCE.getDataStore().isAuthorized()) {
                return chain.proceed(originalRequest);
            } else {
                Request authorisedRequest;


                Log.d(TAG,"Тупое говнище говорит, что оно авторизовано, хм = "+String.valueOf(BookApplication.INSTANCE.getDataStore().isAuthorized()));

                try {
                    if (BookApplication.INSTANCE.getDataStore().getToken() == null) {
                        Log.d(TAG,"Говно тупое, какого хера ты тут делаешь!?");
                        return chain.proceed(originalRequest);
                    }

                    if (BookApplication.INSTANCE.getDataStore().getToken() == null) {
                        Log.d(TAG,"Тупое говно тупого говна, какого хера????");
                        return chain.proceed(originalRequest);
                    }

                    authorisedRequest = originalRequest.newBuilder()
                            .header("Authorization", BookApplication.INSTANCE.getDataStore().getToken())
                            .build();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
                return chain.proceed(authorisedRequest);
            }
        }
    }
}