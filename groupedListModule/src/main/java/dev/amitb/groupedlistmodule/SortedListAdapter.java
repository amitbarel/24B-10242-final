package dev.amitb.groupedlistmodule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ListItem> items = new ArrayList<>();
    private SortCriteria sortCriteria;

    public SortedListAdapter(List<Item> items) {
        setItems(items);
    }

    public void setSortCriteria(SortCriteria sortCriteria) {
        this.sortCriteria = sortCriteria;
        groupItems(extractItems());
    }

    public void setItems(List<Item> items) {
        this.items = new ArrayList<>();
        for (Item item : items) {
            this.items.add(new ListItem(item, ListItem.TYPE_ITEM));
        }
        groupItems(extractItems());
    }

    private void groupItems(List<Item> itemList) {
        List<ListItem> newItems = new ArrayList<>();

        if (sortCriteria == SortCriteria.BY_CATEGORY) {
            itemList.sort(Comparator.comparing(Item::getCategory));
        } else if (sortCriteria == SortCriteria.BY_NAME) {
            itemList.sort(Comparator.comparing(Item::getName));
        } else if (sortCriteria == SortCriteria.BY_PRICE) {
            itemList.sort(Comparator.comparing(Item::getPrice));
        }

        String currentGroup = null;
        for (Item item : itemList) {
            String group;
            if (sortCriteria == SortCriteria.BY_CATEGORY) {
                group = item.getCategory();
            } else if (sortCriteria == SortCriteria.BY_NAME) {
                group = item.getName().substring(0, 1);
            } else {
                group = String.valueOf(item.getPrice());
            }

            if (!group.equals(currentGroup)) {
                newItems.add(new ListItem(group, ListItem.TYPE_HEADER));
                currentGroup = group;
            }
            newItems.add(new ListItem(item, ListItem.TYPE_ITEM));
        }

        items.clear();
        items.addAll(newItems);
    }


    private List<Item> extractItems() {
        List<Item> itemList = new ArrayList<>();
        for (ListItem listItem : items) {
            if (listItem.getType() == ListItem.TYPE_ITEM) {
                itemList.add((Item) listItem.getData());
            }
        }
        return itemList;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ListItem.TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_view, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItem listItem = items.get(position);
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).headerText.setText((String) listItem.getData());
        } else if (holder instanceof ItemViewHolder) {
            Item item = (Item) listItem.getData();
            ((ItemViewHolder) holder).itemName.setText(item.getName());
            ((ItemViewHolder) holder).itemCategory.setText(item.getCategory());
            ((ItemViewHolder) holder).itemPrice.setText(String.format("$%.2f", item.getPrice()));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView headerText;

        HeaderViewHolder(View itemView) {
            super(itemView);
            headerText = itemView.findViewById(R.id.headerText);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemCategory;
        TextView itemName;
        TextView itemPrice;

        ItemViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemCategory = itemView.findViewById(R.id.itemCategory);
        }
    }
}
