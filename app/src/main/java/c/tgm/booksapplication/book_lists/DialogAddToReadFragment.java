package c.tgm.booksapplication.book_lists;

import android.app.Dialog;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.AbstractDialog;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.models.data.BookList;

public class DialogAddToReadFragment extends AbstractDialog<DialogAddToReadFragment.Communicator> {

    public static String LISTS_KEY = "lists";

    private Long currentListId = -1L;

    public static DialogAddToReadFragment getInstance(List<BookList> lists) {
        DialogAddToReadFragment fragment = new DialogAddToReadFragment();

        Bundle bundle = new Bundle();
        ArrayList<BookList> listsInArrayList = new ArrayList<>(lists);
        bundle.putSerializable(LISTS_KEY,listsInArrayList);

        fragment.setArguments(bundle);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_book_in_list, null);

        Spinner mSpinnerList = v.findViewById(R.id.spinnerList);

        ArrayList<BookList> lists = (ArrayList<BookList>) getArguments().getSerializable(LISTS_KEY);

        currentListId = lists.get(0).getListId();

        ArrayAdapter<BookList> mListsAdapter = new ArrayAdapter(getContext(),
                R.layout.spinner_item_bigger, lists);


        mSpinnerList.setAdapter(mListsAdapter);
        mSpinnerList.setSelection(0);
        mSpinnerList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                currentListId = ((BookList)adapterView.getSelectedItem()).getListId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });

        Spinner mSpinnerGrades = v.findViewById(R.id.spinnerGrades);
        mSpinnerGrades.setAdapter(ArrayAdapter.createFromResource(getContext(),R.array.grades,
                R.layout.spinner_item_bigger));

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
                getCommunicator().add(currentListId,Integer.valueOf( mSpinnerGrades.getSelectedItem().toString()));
                dismiss();
            }
        });

        return new AlertDialog.Builder(getActivity()).setView(v)
                .create();
    }

    public interface Communicator {
        void add(Long listId, int rating);
    }
}
