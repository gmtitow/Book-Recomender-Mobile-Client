package c.tgm.booksapplication.any;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import c.tgm.booksapplication.R;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Command;

public class CustomAppNavigator extends SupportAppNavigator {
    public CustomAppNavigator(FragmentActivity activity, int containerId) {
        super(activity, containerId);
    }
    
    @Override
    protected void setupFragmentTransaction(Command command, Fragment currentFragment, Fragment nextFragment, FragmentTransaction fragmentTransaction) {
        super.setupFragmentTransaction(command, currentFragment, nextFragment, fragmentTransaction);
        
        if(AuthorizationAnimationManager.getCurrentAnimation() == AuthorizationAnimationManager.NO_SPECIFIC) {
            fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_deep_left, R.animator.slide_in_right,
                    R.animator.slide_in_deep_right);
        } else{
            switch (AuthorizationAnimationManager.getCurrentAnimation()){
                case AuthorizationAnimationManager.ONLY_LEFT:
//                    fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_deep_left, R.animator.slide_in_deep_right,
//                            R.animator.slide_in_deep_left);
                    fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_deep_left, R.animator.slide_in_right,
                            R.animator.slide_in_deep_right);
                    break;
                    default:
                        fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_deep_left, R.animator.slide_in_right,
                                R.animator.slide_in_deep_right);
                        break;
            }
            AuthorizationAnimationManager.setCurrentAnimation(AuthorizationAnimationManager.NO_SPECIFIC);
        }
    }
}
