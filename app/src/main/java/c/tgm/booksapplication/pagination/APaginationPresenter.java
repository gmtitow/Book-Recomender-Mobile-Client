package c.tgm.booksapplication.pagination;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import c.tgm.booksapplication.NavigatorPresenter;
import c.tgm.booksapplication.repositories.Repository;

public abstract class APaginationPresenter<View extends IPaginationView<MainObject> , MainObject, PaginationModel extends APaginationModel<MainObject>>
        extends NavigatorPresenter<View> {
    protected PaginationModel mModel;
    protected Repository mRepository;

    public APaginationPresenter() {
        initializeModel();
        initializeRepository();
//        getNewObjects(false);
    }

    protected abstract void initializeModel();
    protected abstract void initializeRepository();

    protected abstract void getNewObjects(boolean add);

    public void updateList(boolean rewrite) {
        if(mModel.isLoading())
            mRepository.cancelRequest();

        if (rewrite){
            mModel.setCurPage(1);
        }

        mModel.setLoading(true);
        getNewObjects(!rewrite);
    }

    public List<MainObject> getObjects(){
        return mModel.getObjects();
    }

    public void getNextPage() {
        mModel.increasePage();
        updateList(false);
    }

    public int getPageSize() {
        return mModel.getPageSize();
    }

    protected PaginationModel getModel() {
        return mModel;
    }
}
