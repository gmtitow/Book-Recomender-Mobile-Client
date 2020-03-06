package c.tgm.booksapplication.any;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomValidationUtils {
    public static boolean checkLogin(String login) {
//        String phonePattern = "^(\\+7|7|8)\\(?([0-9]{3,4})\\)?[- ]?([0-9]{2,3})[- ]?([0-9]{2,3})[- ]?([0-9]{2})$";
//        Pattern patternPhone = Pattern.compile(phonePattern);
//        Matcher matcher = patternPhone.matcher(login);
//
//        if(!matcher.add()){
        String emailPattern = "^.*@.*$";
        Pattern patternEmail = Pattern.compile(emailPattern);
        Matcher matcher = patternEmail.matcher(login);
    
        if (!matcher.find())
            return false;
//        }
        
        return true;
    }
    
    public static boolean checkPassword(String password){
        if(password.length()<4)
            return false;
        return true;
    }
}
