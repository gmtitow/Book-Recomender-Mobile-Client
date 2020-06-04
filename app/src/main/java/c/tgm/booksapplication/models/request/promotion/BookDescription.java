package c.tgm.booksapplication.models.request.promotion;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import c.tgm.booksapplication.models.data.Author;
import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.Genre;

public class BookDescription implements Parcelable {

    public static final String TYPE_BOOK = "book";
    public static final String TYPE_DESCRIPTION = "description";

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("book_id")
    @Expose
    private Integer book_id = null;

    @SerializedName("author_id")
    @Expose
    private Integer author_id = null;

    @SerializedName("genre_id")
    @Expose
    private Integer genre_id = null;

    @SerializedName("query")
    @Expose
    private String query = null;

    @SerializedName("factor")
    @Expose
    private Float factor;

    private Book book;

    private Author author;

    private Genre genre;

    public BookDescription(String type, Book book, Float factor) {
        this.type = type;
        setBook(book);
        this.factor = factor;
    }

    public BookDescription(String type, Author author, Genre genre, String query, Float factor) {
        this.type = type;
        setAuthor(author);
        setGenre(genre);
        this.query = query;
        this.factor = factor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public Integer getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Integer genre_id) {
        this.genre_id = genre_id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Float getFactor() {
        return factor;
    }

    public void setFactor(Float factor) {
        this.factor = factor;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        setBook_id(book.getBookId());
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
        if (author!=null)
            setAuthor_id(author.getAuthorId());
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
        if (genre!=null)
            setGenre_id(genre.get_id().intValue());
    }

    protected BookDescription(Parcel in) {
        type = in.readString();
        book_id = in.readByte() == 0x00 ? null : in.readInt();
        author_id = in.readByte() == 0x00 ? null : in.readInt();
        genre_id = in.readByte() == 0x00 ? null : in.readInt();
        query = in.readString();
        factor = in.readByte() == 0x00 ? null : in.readFloat();
        book = (Book) in.readValue(Book.class.getClassLoader());
        author = (Author) in.readValue(Author.class.getClassLoader());
        genre = (Genre) in.readValue(Genre.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        if (book_id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(book_id);
        }
        if (author_id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(author_id);
        }
        if (genre_id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(genre_id);
        }
        dest.writeString(query);
        if (factor == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeFloat(factor);
        }
        dest.writeValue(book);
        dest.writeValue(author);
        dest.writeValue(genre);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BookDescription> CREATOR = new Parcelable.Creator<BookDescription>() {
        @Override
        public BookDescription createFromParcel(Parcel in) {
            return new BookDescription(in);
        }

        @Override
        public BookDescription[] newArray(int size) {
            return new BookDescription[size];
        }
    };
}
