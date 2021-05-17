package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import android.app.ProgressDialog;
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

import com.google.android.material.navigation.NavigationView;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityAjoutBinding;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.RetrofitCookie;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.Service;

import org.kickmyb.transfer.AddTaskRequest;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjoutActivity extends AppCompatActivity {
    private ActivityAjoutBinding binding;
    ActionBarDrawerToggle actionbartoggle;

    ProgressDialog progressD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.aj_title);

        binding = ActivityAjoutBinding.inflate(getLayoutInflater());
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

        binding.AjoutBTNAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //envoie d'information pour ajouter la tache a la list view
                AddTaskRequest addtask = new AddTaskRequest();

                Date d = new Date(binding.ajoutDate.getYear() - 1900, binding.ajoutDate.getMonth(), binding.ajoutDate.getDayOfMonth());
                addtask.deadLine = d;
                addtask.name = binding.ajoutNom.getText().toString();
                final Service service = RetrofitCookie.get();

                service.ListAdd(addtask).enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if (response.isSuccessful()) {
                            progressD = ProgressDialog.show(AjoutActivity.this, getText(R.string.con_con_title),
                                    getText(R.string.info_updating), true);
                            new  AjoutActivity.DialogTask<>().execute();

                        } else {
                            Toast.makeText(getApplicationContext(), R.string.toast_Err_name_taken, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.i("Server", t.getMessage());
                        Toast.makeText(getApplicationContext(), R.string.toast_Err_general, Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
        binding.AjoutBTNRetour.setOnClickListener(new View.OnClickListener() {
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

                }
                else if (item.getItemId() == R.id.nav_deconnection)
                {
                    Intent i = new Intent(AjoutActivity.this, MainActivity.class);
                    startActivity(i);
                    //enleve le cookie de la session
                    final Service service = RetrofitCookie.get();
                    service.SignOUT().enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(getApplicationContext(), R.string.logoff_message, Toast.LENGTH_LONG).show();
                            progressD = ProgressDialog.show(AjoutActivity.this, getText(R.string.con_con_title),
                                    getText(R.string.info_uploading), true);
                            new AjoutActivity.DialogTask<>().execute();
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
    @Override
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
