package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.loginBTNconnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AcceuilActivity.class);
//                i.putExtra("Nom", binding.loginName.getText().toString());
//                i.putExtra("MDP", binding.loginPS.getText().toString());
                startActivity(i);
            }
        });

        binding.loginBTNinscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,InscriptionActivity.class);
                startActivity(i);
            }
        });

    }
}