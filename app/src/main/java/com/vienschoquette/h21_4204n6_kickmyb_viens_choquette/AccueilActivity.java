package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityAcceuilBinding;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.RetrofitCookie;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.Service;

import java.util.Calendar;

public class AccueilActivity extends AppCompatActivity {
    private ActivityAcceuilBinding binding;
    TachesAdapter adapter;
    ActionBarDrawerToggle actionbartoggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityAcceuilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setTitle("Acceuil");

        this.initRecycler();
        this.remplirRecycler();

        //binding.toolbar.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(binding.toolbar);

        TextView txt =binding.navView.getHeaderView(0). findViewById(R.id.textView);
        txt.setText(getIntent().getExtras().getString("Nom"));

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


        binding.acceuilpBTNRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.acceuilpBTNAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccueilActivity.this, AjoutActivity.class);
//                i.putExtra("Nom", binding.loginName.getText().toString());
//                i.putExtra("MDP", binding.loginPS.getText().toString());
                startActivity(i);
            }
        });

        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_accueil) {

                }
                else if (item.getItemId() == R.id.nav_ajout)
                {
                    Intent i = new Intent(AccueilActivity.this, AjoutActivity.class);
                    startActivity(i);
                }
                else if (item.getItemId() == R.id.nav_deconnection)
                {
                    finish();
                }
                 binding.drawerLayout.closeDrawers();
                return false;
            }
        });


    }
   /*@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
   @Override
    public void Deconnection() {
        final Service service = RetrofitCookie.get();
        service.SignOUT().enqueue(Callback<String>);
        getPreference().removeLoginPreferences();

       CookieManager cm = CookieManager.getInstance();
       cm.removeAllCookies();
    }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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
    protected void onPostResume() {
        super.onPostResume();
        adapter.notifyDataSetChanged();
    }

    private void remplirRecycler()
    {
        for (int i = 0; i < 10; i++)
        {
            Taches t = new Taches();
            t.nom = "Greg";
            t.avencementFait = 3;
            t.avencementTotaux = 10;
            t.dateCreation = Calendar.getInstance().getTime();
            t.dateLimite = Calendar.getInstance().getTime();
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
