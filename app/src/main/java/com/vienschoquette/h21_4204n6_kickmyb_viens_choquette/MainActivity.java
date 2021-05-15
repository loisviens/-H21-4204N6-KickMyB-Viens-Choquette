package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityMainBinding;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.RetrofitCookie;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.Service;

import org.kickmyb.transfer.SigninRequest;
import org.kickmyb.transfer.SigninResponse;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    ProgressDialog progressD;

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

                    progressD = ProgressDialog.show(MainActivity.this, getText(R.string.con_con_title),
                            getText(R.string.con_con_message), true);
                    new MainActivity.DialogTask<>().execute();

                    SigninRequest user = new SigninRequest();
                    user.username = binding.loginName.getText().toString();
                    user.password = binding.loginPS.getText().toString();
                    final Service service = RetrofitCookie.get();
                    service.SignIN(user).enqueue(new retrofit2.Callback<SigninResponse>() {
                        @Override
                        public void onResponse(Call<SigninResponse> call, Response<SigninResponse> response) {
                            if (response.isSuccessful()) {
                                //tout est bon, commencre l'activitée et envooie le nom de l'utilisateur
                                i.putExtra("Nom", binding.loginName.getText().toString());
                                startActivity(i);
                            } else {
                                //identifiant incorrect
                                Toast.makeText(getApplicationContext(), R.string.con_error_wrong_id, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SigninResponse> call, Throwable t) {
                            Log.i("Server", t.getMessage());
                            Toast.makeText(getApplicationContext(), R.string.con_error_connection, Toast.LENGTH_LONG).show();
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
    class DialogTask<A,B,C> extends AsyncTask<A,B,C> {

        @Override
        protected void onPostExecute(C c) {
            progressD.dismiss();
            super.onPostExecute(c);
        }

        @Override
        protected C doInBackground(A... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}