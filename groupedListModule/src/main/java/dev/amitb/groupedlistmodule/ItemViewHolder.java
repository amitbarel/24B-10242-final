package dev.amitb.groupedlistmodule;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView itemName;

    public ItemViewHolder(View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.itemName);
    }
    public void bind(Item item) {
        itemName.setText(item.getName());
    }
}
