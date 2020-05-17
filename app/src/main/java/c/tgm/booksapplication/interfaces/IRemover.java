package c.tgm.booksapplication.interfaces;

public interface IRemover {
    default void delete(int id){};
    default void delete(Object object){};
}
