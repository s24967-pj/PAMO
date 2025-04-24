package com.example.bmi;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class BMIActivityEspressoTest {

    @Rule
    public ActivityScenarioRule<BMIActivity> activityScenarioRule =
            new ActivityScenarioRule<>(BMIActivity.class);

    @Test
    public void testCalculateButtonIsVisible() {
        onView(withId(R.id.calculateButton))
                .check(matches(isDisplayed()));
    }
}
