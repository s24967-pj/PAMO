package com.example.bmi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Apliakcja obliczająca BMI na podstawie dwóch danych wejściowych - masy ciała oraz wzrostu.
 * ----------------------------
 * Działanie:
 *  Użytkownik wprowadza wakę oraz wzrost.
 *  BMI jest obliczane z wzoru:
 *      1) Wzrost podany w centymetrach jestsprowadzany do metrów -> wzrost/100
 *      2) BMI = waga / wzrost^2
 * ----------------------------
 * Możliwe błędy:
 *  Brak danych - komunikat „Wypełnij oba pola!”
 *  Niepoprawny wzrost - komunikat „Podaj poprawny wzrost!”
 * */

public class MainActivity extends AppCompatActivity {

    /**
     * Metoda wywoływana przy uruchomieniu aplikacji.
     * Odpowiada za inicjalizację i obsługuje kliknięcie przycisku.
     */
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

    /**
     * Ocenia wynik masy ciała na podstawie wyklakulowanej wartości BMI.
     * przyjmowane parametry: bmi - wartość BMI do interpretacji wyniku.
     * zwraca: interpretacje wyniku jako String.
     */
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