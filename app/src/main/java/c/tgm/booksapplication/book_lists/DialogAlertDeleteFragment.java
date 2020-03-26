package c.tgm.booksapplication.book_lists;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import c.tgm.booksapplication.AbstractDialog;
import c.tgm.booksapplication.R;

public class DialogAlertDeleteFragment extends AbstractDialog<DialogAlertDeleteFragment.WarningCommunicator> {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_warn_deleting_title)
                .setPositiveButton(R.string.text_delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mCommunicator.delete();
                        dismiss();
                    }
                })
                .setNegativeButton(R.string.text_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });
        return builder.create();
    }

    public interface WarningCommunicator {
        void delete();
    }
}


