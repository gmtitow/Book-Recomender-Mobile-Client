package c.tgm.booksapplication.book_lists;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import c.tgm.booksapplication.R;

/**
 * Created by Герман on 29.04.2018.
 */

public class DialogAddListFragment extends DialogFragment {

    public static final String EXTRA_CHOICE = "c.tgm.booksapplication.book_lists.add_fragment";
    
    DialogCommunicator mCommunicator;
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    
        try {
            mCommunicator = (DialogCommunicator) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DialogCommunicator");
        }
    }
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_list, null);

        EditText mEditListName = v.findViewById(R.id.list_name);

        Button mButtonCancel =  v.findViewById(R.id.button_cancel);
        Button mButtonCreate =  v.findViewById(R.id.button_create);

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommunicator.createList(mEditListName.getText().toString());
                dismiss();
            }
        });

        return new AlertDialog.Builder(getActivity()).setView(v)
                .create();
    }
    
    public interface DialogCommunicator {
        void createList(String listName);
    }
}
