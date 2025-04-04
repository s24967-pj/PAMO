package com.example.bmi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;


/***
 * Fragment służący do wyświetlania przepisu dostosowanego do zapotzrebowania kalorycznego. Dane są przekazywane za pomocą argumentów Bundle
 */
public class RecipeFragment extends Fragment {

    private TextView recipeText;

/**
 * Tworzy widok fragmentu i wyświetla tekst przepisu przekazany w argumentach.
 */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        recipeText = view.findViewById(R.id.recipeText);

        String recipe = getArguments().getString("RECIPE_KEY", "Brak przepisu.");
        recipeText.setText(recipe);

        return view;
    }

}
