package c.tgm.booksapplication.models.request.promotion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import c.tgm.booksapplication.models.request.AbstractRequest;

public class PromotionAddRequest extends AbstractRequest {
    @SerializedName("time_start")
    @Expose
    private Integer time_start;

    @SerializedName("time_end")
    @Expose
    private Integer time_end;

    @SerializedName("description")
    @Expose
    private String description = null;

    @SerializedName("book_descriptions")
    @Expose
    private List<BookDescription> book_descriptions;

    public Integer getTime_start() {
        return time_start;
    }

    public void setTime_start(Integer time_start) {
        this.time_start = time_start;
    }

    public Integer getTime_end() {
        return time_end;
    }

    public void setTime_end(Integer time_end) {
        this.time_end = time_end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BookDescription> getBook_descriptions() {
        return book_descriptions;
    }

    public void setBook_descriptions(List<BookDescription> book_descriptions) {
        this.book_descriptions = book_descriptions;
    }

    public PromotionAddRequest(Integer time_start, Integer time_end, String description, List<BookDescription> book_descriptions) {
        this.time_start = time_start;
        this.time_end = time_end;
        this.description = description;
        this.book_descriptions = book_descriptions;
    }

    public PromotionAddRequest(Integer time_start, Integer time_end, List<BookDescription> book_descriptions) {
        this.time_start = time_start;
        this.time_end = time_end;
        this.book_descriptions = book_descriptions;
    }

    public class BookDescription {
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

        public BookDescription(String type, Integer book_id, Float factor) {
            this.type = type;
            this.book_id = book_id;
            this.factor = factor;
        }

        public BookDescription(String type, Integer author_id, Integer genre_id, String query, Float factor) {
            this.type = type;
            this.author_id = author_id;
            this.genre_id = genre_id;
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
    }
}
