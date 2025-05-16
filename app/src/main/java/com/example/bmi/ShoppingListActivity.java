package com.example.bmi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayList<Boolean> checkedStates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        TextView title = findViewById(R.id.shoppingTitle);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Button backButton = findViewById(R.id.exitButton);

        title.setText(getString(R.string.shopping_list_title));

        items = getIntent().getStringArrayListExtra("SHOPPING_LIST");
        if (items == null) items = new ArrayList<>();

        checkedStates = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            checkedStates.add(false);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ShoppingListAdapter(items, checkedStates));

        backButton.setOnClickListener(v -> finish());
    }
}
