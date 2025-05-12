package com.example.bmi;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayList<Boolean> checkedStates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        TextView title = findViewById(R.id.shoppingTitle);
        ListView listView = findViewById(R.id.listView);
        Button backButton = findViewById(R.id.exitButton);

        title.setText(getString(R.string.shopping_list_title));


        items = getIntent().getStringArrayListExtra("SHOPPING_LIST");
        if (items == null) items = new ArrayList<>();

        checkedStates = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            checkedStates.add(false);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, items);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            boolean checked = !checkedStates.get(position);
            checkedStates.set(position, checked);

            TextView itemView = (TextView) view;
            if (checked) {
                itemView.setPaintFlags(itemView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                itemView.setAlpha(0.5f);
            } else {
                itemView.setPaintFlags(itemView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                itemView.setAlpha(1.0f);
            }

            listView.setItemChecked(position, checked);
        });

        backButton.setOnClickListener(v -> finish());
    }
}