package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText weightInput = findViewById(R.id.weightInput);
        EditText heightInput = findViewById(R.id.heightInput);
        Button calculateButton = findViewById(R.id.calculateButton);
        TextView resultText = findViewById(R.id.resultText);
        TextView interpretationText = findViewById(R.id.interpretationText);

        calculateButton.setOnClickListener(view -> {

                String weightStr = weightInput.getText().toString();
                String heightStr = heightInput.getText().toString();

                if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
                    double weight = Double.parseDouble(weightStr);
                    double height = Double.parseDouble(heightStr);

                    if (height > 0) {
                        double heightInCm = height / 100;
                        double bmi = weight / (heightInCm * heightInCm);
                        resultText.setText("Twoje BMI: " + String.format("%.2f", bmi));
                        interpretationText.setText("Interpretacja wyniku: " + CalculateTextBMI(bmi));

                    } else {
                        resultText.setText("Podaj poprawny wzrost!");
                    }
                } else {
                    resultText.setText("Wypełnij oba pola!");
                }
        });
    }

    private String CalculateTextBMI(double bmi){

        if (bmi>=30) {
            return "OTYŁOŚĆ";
        } else if (bmi<30 && bmi>=25) {
            return "NADWAGA";
        } else if (bmi<25 && bmi>18){
            return "OPTYMALNA MASA CIAŁA";
        } else {
            return "NIEDOWAGA";
        }
    }

}