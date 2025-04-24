package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

/*** Klasa BMIActivity odpowiadająca za obliczanie wskaźnika BMI.
 * Użytkownik podaje swoją wagę oraz wzrost na podstawie których obliczanej jest BMI.
 */


public class BMIActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        BMICalculator bmiCalculator = new BMICalculator();

        EditText weightInput = findViewById(R.id.weightInput);
        EditText heightInput = findViewById(R.id.heightInput);
        Button calculateButton = findViewById(R.id.calculateButton);
        TextView resultText = findViewById(R.id.resultText);
        TextView interpretationText = findViewById(R.id.interpretationText);
        Button backButton = findViewById(R.id.BackButton);

        // Obsługa kliknięcia przycisku cofającego do strony głównej
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(BMIActivity.this, StartActivity.class);
            startActivity(intent); //metoda do uruchamiania innej aktywności
        });

        // Obsługa przycisku do wyliczania BMI
        calculateButton.setOnClickListener(view -> {

                String weightStr = weightInput.getText().toString();
                String heightStr = heightInput.getText().toString();

                if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
                    double weight = Double.parseDouble(weightStr);
                    double height = Double.parseDouble(heightStr);

                    if (height > 0) {
                        double heightInCm = height / 100;
                        double bmi = bmiCalculator.calculateBmi(weight, heightInCm);

                        resultText.setText(
                                getString(R.string.bmi_result_with_value, String.format(Locale.US, "%.2f", bmi))
                        );

                        interpretationText.setText(
                                getString(R.string.interpretation_with_value, bmiCalculator.CalculateTextBMI(bmi))
                        );

                    } else {
                        resultText.setText(getString(R.string.error_invalid_height));
                    }
                } else {
                    resultText.setText(getString(R.string.error_fill_fields));
                }
        });
    }
}