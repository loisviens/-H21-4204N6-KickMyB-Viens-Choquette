package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityAcceuilBinding;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityInscriptionBinding;

import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AcceuilActivity extends AppCompatActivity {
    private ActivityAcceuilBinding binding;
    TachesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        binding = ActivityAcceuilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        this.initRecycler();
        this.remplirRecycler();

        binding.acceuilpBTNRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void remplirRecycler()
    {
        for (int i = 0; i < 200; i++)
        {
            Taches t = new Taches();
            t.nom = "Greg";
            t.avencementFait = 3;
            t.avencementTotaux = 10;
            t.dateCreation = Calendar.getInstance().getTime();
            t.dateLimite =  Calendar.getInstance().getTime();
            adapter.list.add(t);
        }
        adapter.notifyDataSetChanged();

    }

    private void initRecycler()
    {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TachesAdapter();
        recyclerView.setAdapter(adapter);
    }
}
