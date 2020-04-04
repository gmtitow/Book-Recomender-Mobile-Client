package c.tgm.booksapplication.book_lists.read_books.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import c.tgm.booksapplication.R;
import c.tgm.booksapplication.any.UserRequest;
import c.tgm.booksapplication.databinding.ReadBookItemBinding;
import c.tgm.booksapplication.models.data.ReadBook;

public class ReadBookHolder extends RecyclerView.ViewHolder {

    private ReadBookItemBinding mBinding;

    public ReadBookHolder(@NonNull ReadBookItemBinding itemView) {
        super(itemView.getRoot());
        this.mBinding = itemView;

    }

    public void bind(final ReadBook book, Context context) {
        mBinding.textAuthorName.setText(book.getAuthorName());
        mBinding.textBookName.setText(book.getName());
        mBinding.textRating.setText(String.valueOf(book.getRating()));

        Picasso.with(context) //передаем контекст приложения
                .load(UserRequest.URL_SERVER + book.getImagePath())
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .centerInside()
                .into(mBinding.imageView);


//        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navigator.goById(book.getBookId());
//            }
//        });
    }
}
