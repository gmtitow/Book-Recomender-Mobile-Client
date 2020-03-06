package c.tgm.booksapplication.any;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CustomGridLayoutManager extends GridLayoutManager {
    private boolean isScrollEnabled = true;
    
    public CustomGridLayoutManager(Context context, int columnCount) {
        super(context,columnCount);
    }
    
    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }
    
    public void setScrollEnabled(boolean flag, boolean scrollable) {
        this.isScrollEnabled = flag;
        isScrollEnabled = scrollable;
    }
    
    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
    
    @Override
    public void onMeasure(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, int widthSpec, int heightSpec) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
    }
}
