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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class CaloriesActivity extends AppCompatActivity {
    private ArrayList<String> shoppingList = new ArrayList<>();


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
        Button shoppingListButton = findViewById(R.id.shoppingListButton);

        // Powrót do ekranu startowego
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(CaloriesActivity.this, StartActivity.class);
            startActivity(intent);
        });

        // Przejście do listy zakupów
        shoppingListButton.setOnClickListener(v -> {
            Intent intent = new Intent(CaloriesActivity.this, ShoppingListActivity.class);
            intent.putStringArrayListExtra("SHOPPING_LIST", shoppingList);
            startActivity(intent);
        });

        // Adaptery spinnerów
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderInput.setAdapter(genderAdapter);

        ArrayAdapter<CharSequence> activityAdapter = ArrayAdapter.createFromResource(this, R.array.activity_array, android.R.layout.simple_spinner_item);
        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityLevelInput.setAdapter(activityAdapter);

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
                resultText.setText(String.format(Locale.getDefault(), "Twoje zapotrzebowanie kaloryczne: %.2f kcal/dzień", tdee));

                String recipe;
                if (tdee <= 1600) {
                    recipe = "1. Warzywna sałatka z tuńczykiem\nTuńczyk w sosie własnym, ogórek, pomidor, sałata, oliwa z oliwek.\n\n2. Jogurt naturalny z owocami\nJogurt 0% tłuszczu, truskawki, borówki, miód.";
                    shoppingList = new ArrayList<>(Arrays.asList("Tuńczyk", "Ogórek", "Pomidor", "Sałata", "Oliwa z oliwek", "Jogurt 0%", "Truskawki", "Borówki", "Miód"));
                } else if (tdee <= 1900) {
                    recipe = "1. Kanapka z awokado i jajkiem\nBułka pełnoziarnista, jajko, awokado, rukola.\n\n2. Koktajl białkowy\nMleko, banan, płatki owsiane, odżywka białkowa.";
                    shoppingList = new ArrayList<>(Arrays.asList("Bułka pełnoziarnista", "Jajko", "Awokado", "Rukola", "Mleko", "Banan", "Płatki owsiane", "Odżywka białkowa"));
                } else {
                    recipe = "1. Makaron z kurczakiem i pesto\nMakaron pełnoziarnisty, kurczak, pesto, parmezan.\n\n2. Omlet z serem i warzywami + tost\nJajka, ser żółty, papryka, cebula, tosty, masło orzechowe.";
                    shoppingList = new ArrayList<>(Arrays.asList("Makaron pełnoziarnisty", "Kurczak", "Pesto", "Parmezan", "Jajka", "Ser żółty", "Papryka", "Cebula", "Tosty", "Masło orzechowe"));
                }

                RecipeFragment recipeFragment = new RecipeFragment();
                Bundle bundle = new Bundle();
                bundle.putString("RECIPE_KEY", recipe);
                recipeFragment.setArguments(bundle);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, recipeFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            } else {
                resultText.setText(getString(R.string.fill_all_fields));
            }
        });
    }

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
