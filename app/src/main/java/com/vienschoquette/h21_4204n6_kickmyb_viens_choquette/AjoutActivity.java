package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityAjoutBinding;

import java.util.Calendar;

public class AjoutActivity extends AppCompatActivity {
    private ActivityAjoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Acceuil");

        binding = ActivityAjoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.AjoutBTNAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //envoie d'information pour ajouter la tache a la list view
                finish();
            }
        });
        binding.AjoutBTNRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
