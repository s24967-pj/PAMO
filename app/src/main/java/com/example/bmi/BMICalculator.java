package com.example.bmi;

public class BMICalculator {

    public double calculateBmi(double weight, double heightInMeters) {
        return weight / (heightInMeters * heightInMeters);
    }

    public String CalculateTextBMI(double bmi) {
        if (bmi >= 30) {
            return "OTYŁOŚĆ";
        } else if (bmi < 30 && bmi >= 25) {
            return "NADWAGA";
        } else if (bmi < 25 && bmi > 18) {
            return "OPTYMALNA MASA CIAŁA";
        } else {
            return "NIEDOWAGA";
        }
    }
}
