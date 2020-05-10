package c.tgm.booksapplication.repositories.promotion;

import java.util.ArrayList;
import java.util.List;

import c.tgm.booksapplication.models.data.Book;
import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.models.data.Promotion;
import c.tgm.booksapplication.models.data.ReadBook;
import c.tgm.booksapplication.models.data.ReadBookWithList;
import c.tgm.booksapplication.repositories.CommonRepo;

public interface PromotionRepo extends CommonRepo {

    default void onGetBookForPromotion(List<Book> books) {};

    default void onGetPromotions(List<Promotion> promotions) {};

    default void onDeletePromotion(String message) {};

    default void onAddPromotion(Promotion promotion) {};
}
