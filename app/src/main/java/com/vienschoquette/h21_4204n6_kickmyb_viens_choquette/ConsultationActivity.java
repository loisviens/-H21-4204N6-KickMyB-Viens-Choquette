package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.google.android.material.navigation.NavigationView;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityAcceuilBinding;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityConsultationBinding;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.RetrofitCookie;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.Service;

import org.kickmyb.transfer.TaskDetailResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultationActivity extends AppCompatActivity {
    private ActivityConsultationBinding binding;
    ActionBarDrawerToggle actionbartoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Consultation");

        //setTitle(getIntent().getStringExtra("Position"));

        binding = ActivityConsultationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionbartoggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.drawerOpen, R.string.drawerClose){
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                }
            };
            actionbartoggle.setDrawerIndicatorEnabled(true);

            binding.drawerLayout.addDrawerListener(actionbartoggle);
            actionbartoggle.syncState();

        }

        final Service service = RetrofitCookie.get();
        service.DetailTache(getIntent().getExtras().getLong("ID")).enqueue(new Callback<TaskDetailResponse>() {
            @Override
            public void onResponse(Call<TaskDetailResponse> call, Response<TaskDetailResponse> response) {
                try {
                    binding.consultationDate.setText            ("Échéance:\n"+ response.body().deadLine.toString());
                    //binding. .setText( response.body().events;
                    binding.consultationNom.setText             ("Nom d'activité:\n"+ response.body().name);
                    binding.consultationTempsEcouler.setText    ("Temps écouler:\n"+ response.body().percentageTimeSpent);
                    binding.consultationProgressBar.setProgress(response.body().percentageDone);

                } catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Une ou plusieur information recu sont nulles", Toast.LENGTH_LONG).show();
                    Log.e("kickmyb", "onResponse: " + e );
                }



            }

            @Override
            public void onFailure(Call<TaskDetailResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Mauvais ID ou autre erreure", Toast.LENGTH_LONG).show();
            }
        });


        binding.consultationBTNProgressChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Service service = RetrofitCookie.get();
                service.TachePourcentageChange(getIntent().getExtras().getLong("ID"),Integer.parseInt(binding.consultationTextProgressChange.getText().toString())).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        binding.consultationProgressBar.setProgress(
                                Integer.parseInt(binding.consultationTextProgressChange.getText().toString()));
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Erreure au changement de progre", Toast.LENGTH_LONG).show();
                        Log.e("kickmyb", "onFailure: "+t );
                    }
                });



            }
        });

        binding.consultationBTNRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_accueil) {
                    finish();
                }
                else if (item.getItemId() == R.id.nav_ajout)
                {
                    finish();
                    Intent i = new Intent(ConsultationActivity.this, AjoutActivity.class);
                    startActivity(i);
                }
                else if (item.getItemId() == R.id.nav_deconnection)
                {
                    finish();
                    Intent i = new Intent(ConsultationActivity.this, MainActivity.class);
                    startActivity(i);
                    //enleve le cookie de la session
                    final Service service = RetrofitCookie.get();
                    service.SignOUT().enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "erreure a la reponce de déconnection", Toast.LENGTH_LONG).show();
                        }
                    });
                }
                binding.drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (actionbartoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionbartoggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        actionbartoggle.onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

}
