package c.tgm.booksapplication.pagination;

import java.util.ArrayList;
import java.util.List;

public abstract class APaginationModel<MainObject> {

    private ArrayList<MainObject> mObjects = new ArrayList<>();

    private int curPage = 1;

    private int pageSize = 20;

    private boolean loading = false;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int increasePage() {
        return ++this.curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public ArrayList<MainObject> getObjects() {
        return mObjects;
    }

    public void setObjects(List<MainObject> objects) {
        this.mObjects.clear();
        this.mObjects.addAll(objects);
    }

    public void addObjects(List<MainObject> objects) {
        this.mObjects.addAll(objects);
    }


    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }
}
