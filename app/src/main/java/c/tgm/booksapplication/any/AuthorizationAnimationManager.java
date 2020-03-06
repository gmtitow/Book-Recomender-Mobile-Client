package c.tgm.booksapplication.any;

public class AuthorizationAnimationManager {
    private static int currentAnimation = -1;
    public static final int NO_SPECIFIC = -1;
    public static final int ONLY_LEFT = 0;
    public static final int ONLY_RIGHT = 1;
    
    public static int getCurrentAnimation() {
        return currentAnimation;
    }
    
    public static void setCurrentAnimation(int currentAnimation) {
        AuthorizationAnimationManager.currentAnimation = currentAnimation;
    }
}
