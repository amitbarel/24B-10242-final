package dev.amitb.groupedlistmodule;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SortedRecyclerView extends RecyclerView {
    private SortCriteria sortCriteria;

    public SortedRecyclerView(Context context) {
        super(context);
        init(null);
    }

    public SortedRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SortedRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.SortedRecyclerView,
                    0, 0);

            try {
                int sortCriteriaValue = a.getInteger(R.styleable.SortedRecyclerView_sortCriteria, 0);
                sortCriteria = SortCriteria.values()[sortCriteriaValue];
            } finally {
                a.recycle();
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof SortedListAdapter) {
            ((SortedListAdapter) adapter).setSortCriteria(sortCriteria);
            ((SortedListAdapter) adapter).notifyDataSetChanged();
        }
    }

    public void setSortCriteria(SortCriteria sortCriteria) {
        this.sortCriteria = sortCriteria;
        if (getAdapter() instanceof SortedListAdapter) {
            ((SortedListAdapter) getAdapter()).setSortCriteria(sortCriteria);
            ((SortedListAdapter) getAdapter()).notifyDataSetChanged();
        }
    }

    public SortCriteria getSortCriteria() {
        return sortCriteria;
    }
}