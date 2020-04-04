package c.tgm.booksapplication.models.data;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import c.tgm.booksapplication.models.data.BookList;
import c.tgm.booksapplication.models.data.Genre;
import c.tgm.booksapplication.models.data.ReadBookWithList;

import c.tgm.booksapplication.models.data.BookListDao;
import c.tgm.booksapplication.models.data.GenreDao;
import c.tgm.booksapplication.models.data.ReadBookWithListDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig bookListDaoConfig;
    private final DaoConfig genreDaoConfig;
    private final DaoConfig readBookWithListDaoConfig;

    private final BookListDao bookListDao;
    private final GenreDao genreDao;
    private final ReadBookWithListDao readBookWithListDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        bookListDaoConfig = daoConfigMap.get(BookListDao.class).clone();
        bookListDaoConfig.initIdentityScope(type);

        genreDaoConfig = daoConfigMap.get(GenreDao.class).clone();
        genreDaoConfig.initIdentityScope(type);

        readBookWithListDaoConfig = daoConfigMap.get(ReadBookWithListDao.class).clone();
        readBookWithListDaoConfig.initIdentityScope(type);

        bookListDao = new BookListDao(bookListDaoConfig, this);
        genreDao = new GenreDao(genreDaoConfig, this);
        readBookWithListDao = new ReadBookWithListDao(readBookWithListDaoConfig, this);

        registerDao(BookList.class, bookListDao);
        registerDao(Genre.class, genreDao);
        registerDao(ReadBookWithList.class, readBookWithListDao);
    }
    
    public void clear() {
        bookListDaoConfig.clearIdentityScope();
        genreDaoConfig.clearIdentityScope();
        readBookWithListDaoConfig.clearIdentityScope();
    }

    public BookListDao getBookListDao() {
        return bookListDao;
    }

    public GenreDao getGenreDao() {
        return genreDao;
    }

    public ReadBookWithListDao getReadBookWithListDao() {
        return readBookWithListDao;
    }

}
