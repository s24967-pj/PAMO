package com.example.bmi;

import org.junit.Test;
import static org.junit.Assert.*;

public class BMICalculatorTest {

    // Testowanie interpretacji BMI (String)
    @Test
    public void testBmiInterpretationObese() {
        // Test dla BMI = 30 -> Otyłość
        BMICalculator bmiCalculator = new BMICalculator();
        double bmi = 30.0;
        String interpretation = bmiCalculator.CalculateTextBMI(bmi);
        assertEquals("OTYŁOŚĆ", interpretation);
    }

    @Test
    public void testBmiInterpretationOverweight() {
        // Test dla BMI = 27 -> Nadwaga
        BMICalculator bmiCalculator = new BMICalculator();
        double bmi = 27.0;
        String interpretation = bmiCalculator.CalculateTextBMI(bmi);
        assertEquals("NADWAGA", interpretation);
    }

    @Test
    public void testBmiInterpretationOptimal() {
        // Test dla BMI = 22 -> Optymalna waga
        BMICalculator bmiCalculator = new BMICalculator();
        double bmi = 22.0;
        String interpretation = bmiCalculator.CalculateTextBMI(bmi);
        assertEquals("OPTYMALNA MASA CIAŁA", interpretation);
    }

    @Test
    public void testBmiInterpretationUnderweight() {
        // Test dla BMI = 17 -> Niedowaga
        BMICalculator bmiCalculator = new BMICalculator();
        double bmi = 17.0;
        String interpretation = bmiCalculator.CalculateTextBMI(bmi);
        assertEquals("NIEDOWAGA", interpretation);
    }

    // Testowanie obliczeń BMI
    @Test
    public void testBmiCalculation() {
        // Przykład: waga 70 kg, wzrost 1.75 m
        double weight = 70.0;
        double height = 1.75;

        // BMI = waga / (wzrost * wzrost)
        double expectedBmi = weight / (height * height);
        double actualBmi = new BMICalculator().calculateBmi(weight, height);

        // Porównanie wyniku BMI jako String
        assertEquals(expectedBmi, actualBmi, 0.01);
    }
}
