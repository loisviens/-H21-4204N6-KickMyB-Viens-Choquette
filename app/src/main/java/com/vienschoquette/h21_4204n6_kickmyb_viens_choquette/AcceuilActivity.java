package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityAcceuilBinding;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityInscriptionBinding;

public class AcceuilActivity extends AppCompatActivity {
    private ActivityAcceuilBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        binding = ActivityAcceuilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.acceuilpBTNRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
