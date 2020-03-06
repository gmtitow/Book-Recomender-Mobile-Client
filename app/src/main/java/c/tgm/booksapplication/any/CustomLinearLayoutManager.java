package c.tgm.booksapplication.any;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CustomLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;
    
    public CustomLinearLayoutManager(Context context) {
        super(context);
    }
    
    public CustomLinearLayoutManager(Context context, boolean scrollable) {
        super(context);
        isScrollEnabled = scrollable;
    }
    
    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }
    
    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
    
    @Override
    public void onMeasure(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, int widthSpec, int heightSpec) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
    }
}
