package com.example.bmi;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class BmiChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_chart);

        Button backButton = findViewById(R.id.BackButton);
        backButton.setOnClickListener(v -> finish());

        LineChart chart = findViewById(R.id.bmiChart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 24.5f));
        entries.add(new Entry(2, 24.2f));
        entries.add(new Entry(3, 23.9f));
        entries.add(new Entry(4, 23.7f));
        entries.add(new Entry(5, 23.5f));

        LineDataSet dataSet = new LineDataSet(entries, "BMI w czasie");
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = chart.getAxisLeft();
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        chart.getDescription().setText("Zmiany BMI");
        chart.animateY(1000);
        chart.invalidate(); // odświeżenie wykresu
    }
}
