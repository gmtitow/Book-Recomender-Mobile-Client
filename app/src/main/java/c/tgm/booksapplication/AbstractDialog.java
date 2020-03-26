package c.tgm.booksapplication;

import android.content.Context;
import android.support.v4.app.DialogFragment;

public abstract class AbstractDialog<Communicator> extends DialogFragment {
    protected Communicator mCommunicator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            if (getTargetFragment() == null)
                mCommunicator = (Communicator) context;
            else
                mCommunicator = (Communicator) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement Communicator");
        }
    }
}
