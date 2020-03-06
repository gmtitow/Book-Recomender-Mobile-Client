package c.tgm.booksapplication.books.list.adapter;

import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.models.data.Genre;

public class GenreAdapter extends ArrayAdapter<Genre> {
    
    public GenreAdapter(Context context, List<Genre> genres) {
        super(context, R.layout.genre_spinner_item, R.id.textGenreName, genres);
    }
    
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    
    public View getCustomView(int position, View convertView,
                              ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_2, null);
        }
        
        TextView label = convertView.findViewById(R.id.textGenreName);
        label.setText(getItem(position).getGenreName());
        
        return label;
    }
    
    
}
