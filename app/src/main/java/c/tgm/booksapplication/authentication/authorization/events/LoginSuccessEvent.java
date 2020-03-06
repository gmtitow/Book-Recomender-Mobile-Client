package c.tgm.booksapplication.authentication.authorization.events;

import c.tgm.booksapplication.models.data.AuthData;

public class LoginSuccessEvent {
    AuthData mAuthData;
    
    public LoginSuccessEvent(AuthData authData) {
        mAuthData = authData;
    }
    
    public AuthData getAuthData() {
        return mAuthData;
    }
}
