package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityInscriptionBinding;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityMainBinding;

public class InscriptionActivity extends AppCompatActivity {
    private ActivityInscriptionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        binding = ActivityInscriptionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.signupBTNinscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(InscriptionActivity.this,AcceuilActivity.class);
//                i.putExtra("Nom", binding.loginName.getText().toString());
//                i.putExtra("MDP", binding.loginPS.getText().toString());
                startActivity(i);
            }
        });

        binding.signupBTNRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
