package com.example.bmi;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private final ArrayList<String> items;
    private final ArrayList<Boolean> checkedStates;

    public ShoppingListAdapter(ArrayList<String> items, ArrayList<Boolean> checkedStates) {
        this.items = items;
        this.checkedStates = checkedStates;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shopping, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = items.get(position);
        boolean checked = checkedStates.get(position);

        holder.itemName.setText(item);
        holder.itemCheckBox.setOnCheckedChangeListener(null);
        holder.itemCheckBox.setChecked(checked);

        if (checked) {
            holder.itemName.setPaintFlags(holder.itemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.itemName.setAlpha(0.5f);
        } else {
            holder.itemName.setPaintFlags(holder.itemName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.itemName.setAlpha(1.0f);
        }

        holder.itemCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkedStates.set(position, isChecked);
            if (isChecked) {
                holder.itemName.setPaintFlags(holder.itemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.itemName.setAlpha(0.5f);
            } else {
                holder.itemName.setPaintFlags(holder.itemName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                holder.itemName.setAlpha(1.0f);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox itemCheckBox;
        TextView itemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCheckBox = itemView.findViewById(R.id.itemCheckBox);
            itemName = itemView.findViewById(R.id.itemName);
        }
    }
}
