package c.tgm.booksapplication.authentication.registration.password.events;

import c.tgm.booksapplication.models.data.AuthData;

public class RegisterSuccessEvent {
    AuthData mAuthData;
    
    public RegisterSuccessEvent(AuthData authData) {
        mAuthData = authData;
    }
    
    public AuthData getAuthData() {
        return mAuthData;
    }
}
