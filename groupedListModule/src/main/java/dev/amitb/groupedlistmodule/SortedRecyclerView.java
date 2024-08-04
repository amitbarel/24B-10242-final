package dev.amitb.groupedlistmodule;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
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
                int sortCriteriaOrdinal = a.getInt(R.styleable.SortedRecyclerView_sortCriteria, 0);
                sortCriteria = SortCriteria.values()[sortCriteriaOrdinal];
            } catch (Exception e) {
                sortCriteria = SortCriteria.BY_NAME; // Default value
            } finally {
                a.recycle();
            }
        }
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof SortedListAdapter) {
            SortedListAdapter sortedListAdapter = (SortedListAdapter) adapter;
            sortedListAdapter.setSortCriteria(sortCriteria);
            sortedListAdapter.notifyDataSetChanged();
        }
    }

    public void setSortCriteria(SortCriteria criteria) {
        this.sortCriteria = criteria;
        if (getAdapter() instanceof SortedListAdapter) {
            SortedListAdapter sortedListAdapter = (SortedListAdapter) getAdapter();
            sortedListAdapter.setSortCriteria(criteria);
            sortedListAdapter.notifyDataSetChanged();
        }
    }

    public SortCriteria getSortCriteria() {
        return sortCriteria;
    }
}