package c.tgm.booksapplication;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

import c.tgm.booksapplication.authentication.authorization.AuthorizationFragment;
import c.tgm.booksapplication.authentication.authorization.change_password.code.ChangePasswordCodeFragment;
import c.tgm.booksapplication.authentication.authorization.change_password.login.ChangePasswordLoginFragment;
import c.tgm.booksapplication.authentication.authorization.change_password.password.ChangePasswordPasswordFragment;
import c.tgm.booksapplication.authentication.registration.confirm.RegistrationConfirmCodeFragment;
import c.tgm.booksapplication.authentication.registration.login.RegistrationLoginFragment;
import c.tgm.booksapplication.authentication.registration.password.RegistrationPasswordFragment;
import c.tgm.booksapplication.authors.list.AuthorListFragment;
import c.tgm.booksapplication.book_lists.BookListsListFragment;
import c.tgm.booksapplication.book_lists.read_books.ReadBooksFragment;
import c.tgm.booksapplication.books.item.BookItemFragment;
import c.tgm.booksapplication.books.list.BookListFragment;
import c.tgm.booksapplication.books.recommends.BookRecommendsFragment;
import c.tgm.booksapplication.filters.FilterFragment;
import c.tgm.booksapplication.filters.FilterViewOptions;
import c.tgm.booksapplication.filters.IFIlterHandler;
import c.tgm.booksapplication.filters.author.AuthorFilterFragment;
import c.tgm.booksapplication.filters.author.IAuthorChanger;
import c.tgm.booksapplication.filters.booklist.BookListFilterFragment;
import c.tgm.booksapplication.filters.booklist.IBookListChanger;
import c.tgm.booksapplication.filters.genre.GenreFilterFragment;
import c.tgm.booksapplication.filters.genre.IGenreChanger;
import c.tgm.booksapplication.models.data.BookInfo;
import c.tgm.booksapplication.models.data.Review;
import c.tgm.booksapplication.models.request.promotion.BookDescription;
import c.tgm.booksapplication.promotion.add.PromotionAddFragment;
import c.tgm.booksapplication.promotion.add.selectbooks.IBookDescriptionRemember;
import c.tgm.booksapplication.promotion.add.selectbooks.PromotionSelectBooksFragment;
import c.tgm.booksapplication.promotion.add.showselected.ShowSelectedBooksFragment;
import c.tgm.booksapplication.promotion.list.PromotionListFragment;
import c.tgm.booksapplication.review.ReviewAddFragment;
import c.tgm.booksapplication.review.list.ReviewListFragment;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {
    public static final class MainScreens extends SupportAppScreen {
    
        public static final int MAP_SCREEN = 0;
        public static final int MAIN_SCREEN = 1;
        public static final int PROFILE_SCREEN = 2;
    
        private final int number;
    
        public MainScreens(int number) {
            this.number = number;
            this.screenKey = getClass().getSimpleName() + "_" + number;
        }
    
        @Override
        public Fragment getFragment() {
            switch (number) {
                default:
                    throw new RuntimeException("Unknown screen key!!");
            }
        }
    }
    
    public static final class AuthenticationScreens extends SupportAppScreen {
        
        public static final String AUTHORIZATION_SCREEN = "authorization_screen";
        public static final String REGISTRATION_LOGIN_SCREEN = "registration_login_screen";
        public static final String REGISTRATION_PASSWORD_SCREEN = "registration_password_screen";
        public static final String REGISTRATION_CONFIRM_CODE_SCREEN = "registration_confirm_code_screen";
        public static final String RESET_PASSWORD_LOGIN_SCREEN = "reset_password_login_screen";
        public static final String RESET_PASSWORD_CODE_SCREEN = "reset_password_code_screen";
        public static final String RESET_PASSWORD_PASSWORD_SCREEN = "reset_password_password_screen";
        
        private Object data = null;
        private Object data2 = null;
        
        public AuthenticationScreens(String screenkey) {
            this.screenKey = screenkey;
        }
        
        public AuthenticationScreens(String screenkey, Object data) {
            this.screenKey = screenkey;
            this.data = data;
        }
    
        public AuthenticationScreens(String screenkey, Object data, Object data2) {
            this.screenKey = screenkey;
            this.data = data;
            this.data2 = data2;
        }
        
        @Override
        public Fragment getFragment() {
            switch(getScreenKey()) {
                case AUTHORIZATION_SCREEN:
                    return AuthorizationFragment.getInstance();
                case REGISTRATION_LOGIN_SCREEN:
                    if(data!=null)
                        return RegistrationLoginFragment.getInstance((String)data);
                    else
                        return RegistrationLoginFragment.getInstance();
                case REGISTRATION_PASSWORD_SCREEN:
                        return RegistrationPasswordFragment.getInstance((String)data,(String)data2);
                case REGISTRATION_CONFIRM_CODE_SCREEN:
                    return RegistrationConfirmCodeFragment.getInstance((String)data);
                case RESET_PASSWORD_LOGIN_SCREEN:
                    return ChangePasswordLoginFragment.getInstance((String)data);
                case RESET_PASSWORD_CODE_SCREEN:
                    return ChangePasswordCodeFragment.getInstance((String)data);
                case RESET_PASSWORD_PASSWORD_SCREEN:
                    return ChangePasswordPasswordFragment.getInstance( ((String[])data)[0],((String[])data)[1]);
               
                default:
                    throw new RuntimeException("Unknown screen key!!");
            }
        }
    }
    
    public static final class BookScreens extends SupportAppScreen {
        
        public static final String FIND_BOOK_SCREEN = "find_book_screen";
        public static final String GET_RECOMMENDS_SCREEN = "get_recommends_screen";
        public static final String BOOK_INFO_SCREEN = "book_info_screen";
        
        private Object data = null;
        private Object data2 = null;
        
        public BookScreens(String screenkey) {
            this.screenKey = screenkey;
        }
        
        public BookScreens(String screenkey, Object data) {
            this.screenKey = screenkey;
            this.data = data;
        }
        
        public BookScreens(String screenkey, Object data, Object data2) {
            this.screenKey = screenkey;
            this.data = data;
            this.data2 = data2;
        }
        
        @Override
        public Fragment getFragment() {
            switch(getScreenKey()) {
                case FIND_BOOK_SCREEN:{
                    if (data != null) {
                        return BookListFragment.getInstance((int)data);
                    } else {
                        return BookListFragment.getInstance();
                    }
                }
                case GET_RECOMMENDS_SCREEN:{
                    if (data != null) {
                        return BookRecommendsFragment.getInstance((int)data);
                    } else {
                        return BookRecommendsFragment.getInstance();
                    }
                }
                case BOOK_INFO_SCREEN:{
                    if (data != null) {
                        return BookItemFragment.getInstance((BookInfo) data);
                    }
                }
                default:
                    throw new RuntimeException("Unknown screen key!!");
            }
        }
    }
    
    public static final class AuthorScreens extends SupportAppScreen {
        
        public static final String FIND_AUTHOR_SCREEN = "find_author_screen";
        
        private Object data = null;
        private Object data2 = null;
        
        public AuthorScreens(String screenkey) {
            this.screenKey = screenkey;
        }
        
        public AuthorScreens(String screenkey, Object data) {
            this.screenKey = screenkey;
            this.data = data;
        }
        
        public AuthorScreens(String screenkey, Object data, Object data2) {
            this.screenKey = screenkey;
            this.data = data;
            this.data2 = data2;
        }
        
        @Override
        public Fragment getFragment() {
            switch(getScreenKey()) {
                case FIND_AUTHOR_SCREEN:{
                    return AuthorListFragment.getInstance();
                }
                default:
                    throw new RuntimeException("Unknown screen key!!");
            }
        }
    }
    
    public static final class ReviewScreens extends SupportAppScreen {
        
        public static final String ADD_REVIEW_SCREEN = "add_review";
        public static final String REVIEW_LIST_SCREEN = "list_review";
        
        private Object data = null;
        private Object data2 = null;
        
        public ReviewScreens(String screenkey) {
            this.screenKey = screenkey;
        }
        
        public ReviewScreens(String screenkey, Object data) {
            this.screenKey = screenkey;
            this.data = data;
        }
        
        public ReviewScreens(String screenkey, Object data, Object data2) {
            this.screenKey = screenkey;
            this.data = data;
            this.data2 = data2;
        }
        
        @Override
        public Fragment getFragment() {
            switch(getScreenKey()) {
                case ADD_REVIEW_SCREEN:{
                    if (data2!=null)
                        return ReviewAddFragment.getInstance((int)data,(Review)data2);
                    else
                        return ReviewAddFragment.getInstance((int)data);
                }
                case REVIEW_LIST_SCREEN:{
                    return ReviewListFragment.getInstance();
                }
                default:
                    throw new RuntimeException("Unknown screen key!!");
            }
        }
    }

    public static final class BookListsScreens extends SupportAppScreen {

        public static final String LISTS_LIST_SCREEN = "lists_list_screen";
        public static final String READ_BOOKS_SCREEN = "read_books_screen";

        private Object data = null;
        private Object data2 = null;

        public BookListsScreens(String screenkey) {
            this.screenKey = screenkey;
        }

        public BookListsScreens(String screenkey, Object data) {
            this.screenKey = screenkey;
            this.data = data;
        }

        public BookListsScreens(String screenkey, Object data, Object data2) {
            this.screenKey = screenkey;
            this.data = data;
            this.data2 = data2;
        }

        @Override
        public Fragment getFragment() {
            switch(getScreenKey()) {
                case LISTS_LIST_SCREEN:
                    return BookListsListFragment.getInstance();
                case READ_BOOKS_SCREEN:
                    return ReadBooksFragment.getInstance((int)data);
                default:
                    throw new RuntimeException("Unknown screen key!!");
            }
        }
    }

    public static final class PromotionScreens extends SupportAppScreen {

        public static final String LIST_SCREEN = "list_screen";
        public static final String ADD_SCREEN = "add_screen";
        public static final String SELECT_BOOK_SCREEN = "select_book_screen";
        public static final String SHOW_SELECTED_BOOKS_SCREEN = "show_selected_books_screen";

        private Object data = null;
        private Object data2 = null;
        private Object data3 = null;

        public PromotionScreens(String screenkey) {
            this.screenKey = screenkey;
        }

        public PromotionScreens(String screenkey, Object data) {
            this.screenKey = screenkey;
            this.data = data;
        }

        public PromotionScreens(String screenkey, Object data, Object data2) {
            this.screenKey = screenkey;
            this.data = data;
            this.data2 = data2;
        }

        public PromotionScreens(String screenkey, Object data, Object data2, Object data3) {
            this.screenKey = screenkey;
            this.data = data;
            this.data2 = data2;
            this.data3 = data3;
        }

        @Override
        public Fragment getFragment() {
            switch(getScreenKey()) {
                case LIST_SCREEN:
                    return PromotionListFragment.getInstance();
                case ADD_SCREEN:
                    return PromotionAddFragment.getInstance();
                case SELECT_BOOK_SCREEN:
                    return PromotionSelectBooksFragment.getInstance((IBookDescriptionRemember) data,
                            (ArrayList<BookDescription>)data2);
                case SHOW_SELECTED_BOOKS_SCREEN:
                    return ShowSelectedBooksFragment.getInstance((IBookDescriptionRemember) data,
                            (IBookDescriptionRemember) data2,
                            (ArrayList<BookDescription>)data3);
                default:
                    throw new RuntimeException("Unknown screen key!!");
            }
        }
    }

    public static final class FilterScreens extends SupportAppScreen {

        public static final String FILTER_SCREEN = "filter_screen";
        public static final String FILTER_AUTHOR_SCREEN = "filter_author_screen";
        public static final String FILTER_GENRE_SCREEN = "filter_genre_screen";
        public static final String FILTER_BOOK_LIST_SCREEN = "filter_book_list_screen";

        private Object data = null;
        private Object data2 = null;

        public FilterScreens(String screenkey) {
            this.screenKey = screenkey;
        }

        public FilterScreens(String screenkey, Object data) {
            this.screenKey = screenkey;
            this.data = data;
        }

        public FilterScreens(String screenkey, Object data, Object data2) {
            this.screenKey = screenkey;
            this.data = data;
            this.data2 = data2;
        }

        @Override
        public Fragment getFragment() {
            switch(getScreenKey()) {
                case FILTER_SCREEN:
                    if (data2!=null)
                        return FilterFragment.getInstance((IFIlterHandler) data,(FilterViewOptions) data2);
                    return FilterFragment.getInstance((IFIlterHandler) data);
                case FILTER_AUTHOR_SCREEN:
                    return AuthorFilterFragment.getInstance((IAuthorChanger) data);
                case FILTER_GENRE_SCREEN:
                    return GenreFilterFragment.getInstance((IGenreChanger) data);
                case FILTER_BOOK_LIST_SCREEN:
                    return BookListFilterFragment.getInstance((IBookListChanger) data);
                default:
                    throw new RuntimeException("Unknown screen key!!");
            }
        }
    }
}
