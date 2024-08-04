package dev.amitb.groupedlistmodule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ListItem> items; // List of ListItem (headers and items)
    private SortCriteria sortCriteria;
    private String headerText;

    public SortedListAdapter(List<Item> itemList) {
        this.sortCriteria = null;
        this.items = new ArrayList<>();
        groupItems(itemList);
    }

    public SortedListAdapter(List<Item> itemList, SortCriteria sortCriteria) {
        this.sortCriteria = sortCriteria;
        this.items = new ArrayList<>();
        groupItems(itemList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ListItem.TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_view, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListItem listItem = items.get(position);
        if (listItem.getType() == ListItem.TYPE_HEADER) {
            ((HeaderViewHolder) holder).bind((String) listItem.getData());
        } else {
            ((ItemViewHolder) holder).bind((Item) listItem.getData());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    // Method to set sort criteria
    public void setSortCriteria(SortCriteria sortCriteria) {
        this.sortCriteria = sortCriteria;
        groupItems(new ArrayList<>(extractItems())); // Rebuild items list
        notifyDataSetChanged(); // Notify adapter of changes
    }

    public void updateHeaderText(String headerText) {
        this.headerText = headerText;
        notifyDataSetChanged(); // Notify adapter that header text has changed
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

    private void groupItems(List<Item> itemList) {
        List<ListItem> newItems = new ArrayList<>(); // New list to avoid modifying existing list

        if (SortCriteria.BY_CATEGORY.equals(sortCriteria)) {
            Collections.sort(itemList, new Comparator<Item>() {
                @Override
                public int compare(Item item1, Item item2) {
                    return item1.getCategory().compareTo(item2.getCategory());
                }
            });
        } else if (SortCriteria.BY_NAME.equals(sortCriteria)) {
            Collections.sort(itemList, new Comparator<Item>() {
                @Override
                public int compare(Item item1, Item item2) {
                    return item1.getName().compareTo(item2.getName());
                }
            });
        }

        String currentGroup = null;
        for (Item item : itemList) {
            String group = (SortCriteria.BY_CATEGORY.equals(sortCriteria)) ? item.getCategory() : item.getName().substring(0, 1); // First letter
            if (!group.equals(currentGroup)) {
                newItems.add(new ListItem(group, ListItem.TYPE_HEADER)); // Add header
                currentGroup = group;
            }
            newItems.add(new ListItem(item, ListItem.TYPE_ITEM)); // Add item
        }

        items.clear(); // Clear existing items
        items.addAll(newItems); // Add new sorted and grouped items
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView headerText;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            headerText = itemView.findViewById(R.id.headerText);
        }

        public void bind(String text) {
            headerText.setText(text);
        }
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
        }

        public void bind(Item item) {
            itemName.setText(item.getName());
        }
    }
}