package c.tgm.booksapplication.interfaces;

public interface INavigator {
    default void goById(int id) {};

    default void goById(Object object) {};
}
