package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityMainBinding;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.RetrofitUtil;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.Service;

import org.kickmyb.transfer.SigninRequest;
import org.kickmyb.transfer.SigninResponse;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Connection");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.loginBTNconnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AccueilActivity.class);
//                i.putExtra("Nom", binding.loginName.getText().toString());
//                i.putExtra("MDP", binding.loginPS.getText().toString());

                    SigninRequest user = new SigninRequest();
                    user.username = binding.loginName.getText().toString();
                    user.password = binding.loginPS.getText().toString();
                    final Service service = RetrofitUtil.get();
                    service.SignIN(user).enqueue(new retrofit2.Callback<SigninResponse>() {
                        @Override
                        public void onResponse(Call<SigninResponse> call, Response<SigninResponse> response) {
                            if (response.isSuccessful()) {
                                //tout est bon, commencre l'activitée et envooie le nom de l'utilisateur
                                i.putExtra("Nom", binding.loginName.getText().toString());
                                startActivity(i);
                            } else {
                                //identifiant incorrect
                                Toast.makeText(getApplicationContext(), "Mauvais identifiant ou mot de passe", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SigninResponse> call, Throwable t) {
                            Log.i("Server", t.getMessage());
                            Toast.makeText(getApplicationContext(), "Erreure", Toast.LENGTH_LONG).show();
                        }
                    });


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