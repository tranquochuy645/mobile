package com.example.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ItemModel> itemsList;

    // Constructor for the adapter
    public ItemsAdapter(Context context, List<ItemModel> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    // Create a new ViewHolder for an item in the RecyclerView
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView;

        switch (viewType) {
            case ItemModel.TYPE_TYPE1:
                // Inflate the layout for Type1 items
                itemView = inflater.inflate(R.layout.item_type1, parent, false);
                // Return a new Type1ViewHolder for this item view
                return new Type1ViewHolder(itemView);
            case ItemModel.TYPE_TYPE2:
                // Inflate the layout for Type2 items
                itemView = inflater.inflate(R.layout.item_type2, parent, false);
                // Return a new Type2ViewHolder for this item view
                return new Type2ViewHolder(itemView);
            // Add more cases for other button types if needed
            default:
                return null;
        }
    }

    // Bind data to the ViewHolder's views
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int adapterPosition = holder.getAdapterPosition();

        if (adapterPosition != RecyclerView.NO_POSITION) {
            // Get the item at the current position
            ItemModel item = itemsList.get(adapterPosition);

            // Bind data based on the item's type
            switch (item.getType()) {
                case ItemModel.TYPE_TYPE1:
                    // Call bindData method in Type1ViewHolder to populate Type1 item views
                    ((Type1ViewHolder) holder).bindData(item, this);
                    break;
                case ItemModel.TYPE_TYPE2:
                    // Call bindData method in Type2ViewHolder to populate Type2 item views
                    ((Type2ViewHolder) holder).bindData(item, this);
                    break;
                default:
                    break;
            }
        }
    }

    // Return the total number of items in the RecyclerView
    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    // Return the view type (button type) of an item at a given position
    @Override
    public int getItemViewType(int position) {
        return itemsList.get(position).getType();
    }

    // Add a new item to the list and notify the adapter of the change
    public void addItem(ItemModel item) {
        itemsList.add(item);
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    // Remove an item from the list and notify the adapter of the change
    public void removeItem(ItemModel itemToRemove) {
        if (itemToRemove != null) {
            int position = itemsList.indexOf(itemToRemove);
            if (position >= 0) {
                itemsList.remove(position);
                notifyItemRemoved(position);
            }
        }
    }
}
