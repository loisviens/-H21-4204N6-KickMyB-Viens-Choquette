package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityAcceuilBinding;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityConsultationBinding;

import java.util.Calendar;

public class ConsultationActivity extends AppCompatActivity {
    private ActivityConsultationBinding binding;
    PieChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);
        setTitle(getIntent().getStringExtra("Position"));

        binding = ActivityConsultationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        chart = findViewById(R.id.consultationPieChart);

        chart.setMaxAngle(100);
 //chart.setProgress(4);

        binding.consultationBTNRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}
