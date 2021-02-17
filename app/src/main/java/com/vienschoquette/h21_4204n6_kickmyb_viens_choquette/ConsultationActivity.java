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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getIntent().getStringExtra("Position"));

        binding = ActivityConsultationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.consultationProgressBar.setProgress(75);

        binding.consultationBTNProgressChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.consultationProgressBar.setProgress(
                        Integer.parseInt(binding.consultationTextProgressChange.getText().toString()));
            }
        });

        binding.consultationBTNRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}
