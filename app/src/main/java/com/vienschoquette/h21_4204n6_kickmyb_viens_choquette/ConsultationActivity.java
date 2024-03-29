package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
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

    ProgressDialog progressD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.task_title);


        progressD = ProgressDialog.show(ConsultationActivity.this, getText(R.string.con_con_title),
                getText(R.string.info_updating), true);
        new  ConsultationActivity.DialogTask<>().execute();
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
                    binding.consultationDate.setText            (getText(R.string.task_limit) + "\n" + response.body().deadLine.toString());
                    //binding. .setText( response.body().events;
                    binding.consultationNom.setText             (getText(R.string.task_name) + "\n" + response.body().name);
                    binding.consultationTempsEcouler.setText    (getText(R.string.task_time) + "\n" + response.body().percentageTimeSpent);
                    binding.consultationProgressBar.setProgress(response.body().percentageDone);

                } catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), R.string.task_error_null_info, Toast.LENGTH_LONG).show();
                    Log.e("kickmyb", "onResponse: " + e );
                }



            }

            @Override
            public void onFailure(Call<TaskDetailResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.con_error_connection, Toast.LENGTH_LONG).show();
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
                        progressD = ProgressDialog.show(ConsultationActivity.this, getText(R.string.con_con_title),
                                getText(R.string.info_uploading), true);
                        new  ConsultationActivity.DialogTask<>().execute();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), R.string.task_error_progress_change, Toast.LENGTH_LONG).show();
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

                    Intent i = new Intent(ConsultationActivity.this, MainActivity.class);
                    startActivity(i);
                    //enleve le cookie de la session
                    final Service service = RetrofitCookie.get();
                    service.SignOUT().enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                            progressD = ProgressDialog.show(ConsultationActivity.this, getText(R.string.con_con_title),
                                    getText(R.string.info_uploading), true);
                            new ConsultationActivity.DialogTasklogoff<>().execute();

                            Toast.makeText(getApplicationContext(), R.string.logoff_message, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), R.string.toast_Err_logoff_request, Toast.LENGTH_LONG).show();
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

    protected void onPause() {
        super.onPause();
        if ( progressD!=null && progressD.isShowing() )
        {
            progressD.cancel();
        }
    }

    class DialogTask<A,B,C> extends AsyncTask<A,B,C> {

        @Override
        protected void onPostExecute(C c) {
            progressD.dismiss();
            super.onPostExecute(c);
        }

        @Override
        protected C doInBackground(A... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class DialogTasklogoff<A,B,C> extends AsyncTask<A,B,C> {

        @Override
        protected void onPostExecute(C c) {
            progressD.dismiss();
            finish();
            super.onPostExecute(c);
        }

        @Override
        protected C doInBackground(A... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
