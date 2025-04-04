package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Aktywność startowa aplikacji – umożliwia wybór  co chce użytkownik obliczyć: BMI lub zapotrzebowanie kaloryczne
 */

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button BmiButton = findViewById(R.id.BmiButton);
        Button CaloriesButton = findViewById(R.id.CaloriesButton);

        BmiButton.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, BMIActivity.class);
            startActivity(intent);
        });
        CaloriesButton.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, CaloriesActivity.class);
            startActivity(intent);
        });
    }
}