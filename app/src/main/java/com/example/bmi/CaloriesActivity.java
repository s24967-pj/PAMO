package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

/*** Służy do obliczania zapotrzebowania kalorycznego na podstawie wprowadzonych przez użytkownika danych:
 * waga, wzrost, płeć, aktywność
 * Na podstawie wyliczonego zapotzrebowania można wyświetlić przykładowe przepisy dla danej kalorykii.
 */

public class CaloriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);

        EditText weightInput = findViewById(R.id.weightInput);
        EditText heightInput = findViewById(R.id.heightInput);
        EditText ageInput = findViewById(R.id.ageInput);
        Spinner genderInput = findViewById(R.id.genderSpinner);
        Spinner activityLevelInput = findViewById(R.id.activitySpinner);
        Button calculateButton = findViewById(R.id.calculateButton);
        TextView resultText = findViewById(R.id.resultText);
        Button showRecipeButton = findViewById(R.id.showRecipeButton);
        Button backButton = findViewById(R.id.BackButton);

        // Obsługa przycisku odpowiadającego za powrót do ekranu startowego
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(CaloriesActivity.this, StartActivity.class);
            startActivity(intent); //metoda do uruchamiania innej aktywności
        });

        // Adaptery do spinnerów
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderInput.setAdapter(genderAdapter);

        ArrayAdapter<CharSequence> activityAdapter = ArrayAdapter.createFromResource(this, R.array.activity_array, android.R.layout.simple_spinner_item);
        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityLevelInput.setAdapter(activityAdapter);

        // Obliczanie zapotrzebowania kalorycznego
        calculateButton.setOnClickListener(v -> {
            String weightStr = weightInput.getText().toString();
            String heightStr = heightInput.getText().toString();
            String ageStr = ageInput.getText().toString();
            String genderStr = genderInput.getSelectedItem().toString().toLowerCase();
            String activityLevelStr = activityLevelInput.getSelectedItem().toString().toLowerCase();

            if (!weightStr.isEmpty() && !heightStr.isEmpty() && !ageStr.isEmpty()) {
                double weight = Double.parseDouble(weightStr);
                double height = Double.parseDouble(heightStr);
                int age = Integer.parseInt(ageStr);

                double bmr;

                if (genderStr.equals("mężczyzna")) {
                    bmr = 88.36 + (13.4 * weight) + (4.8 * height) - (5.7 * age);
                } else {
                    bmr = 447.6 + (9.2 * weight) + (3.1 * height) - (4.3 * age);
                }

                double tdee = bmr * getActivityMultiplier(activityLevelStr);
                resultText.setText("Twoje zapotrzebowanie kaloryczne: " + String.format("%.2f", tdee) + " kcal/dzień");

                // Obsługa przycisku „Pokaż przepis”
                showRecipeButton.setOnClickListener(v1 -> {
                    String recipe;
                    if (tdee <= 1600) {
                        recipe = "1. Warzywna sałatka z tuńczykiem\n" +
                                "Tuńczyk w sosie własnym, ogórek, pomidor, sałata, odrobina oliwy z oliwek.\n" +
                                "\n" +
                                "2. Jogurt naturalny z owocami\n" +
                                "Jogurt 0% tłuszczu, truskawki, garść borówek, łyżeczka miodu.";

                    } else if (tdee >= 1601 && tdee <=1900){
                        recipe = "1. Kanapka z awokado i jajkiem\n" +
                                "Pełnoziarnista bułka, jajko na twardo, plaster awokado, rukola.\n" +
                                "\n" +
                                "2. Koktajl białkowy\n" +
                                "Mleko, banan, garść płatków owsianych, miarka odżywki białkowej.";
                    } else {
                        recipe = "1. Makaron z kurczakiem i pesto\n" +
                                "Makaron pełnoziarnisty, grillowany kurczak, łyżka pesto, parmezan.\n" +
                                "\n" +
                                "2. Omlet z serem i warzywami + tost\n" +
                                "3 jajka, ser żółty, papryka, cebula + tost z masłem orzechowym.";
                    }

                    // Przekazanie przepisu do fragmentu i jego wyświetlenie
                    RecipeFragment recipeFragment = new RecipeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("RECIPE_KEY", recipe);
                    recipeFragment.setArguments(bundle);

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainer, recipeFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                });

            } else {
                resultText.setText("Wypełnij wszystkie pola!");
            }
        });
    }

    /**
     * Oblicza wynik aktywności fizycznej na podstawie wybranego poziomu.
     */
    private double getActivityMultiplier(String activityLevel) {
        switch (activityLevel) {
            case "brak": return 1.2;
            case "niska": return 1.375;
            case "średnia": return 1.55;
            case "wysoka": return 1.725;
            case "bardzo wysoka": return 1.9;
            default: return 1.2;
        }
    }
}
