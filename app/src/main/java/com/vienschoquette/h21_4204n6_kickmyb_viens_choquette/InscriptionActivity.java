package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.databinding.ActivityInscriptionBinding;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.RetrofitCookie;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.Service;

import org.kickmyb.transfer.SigninRequest;
import org.kickmyb.transfer.SigninResponse;
import org.kickmyb.transfer.SignupRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InscriptionActivity extends AppCompatActivity {
    private ActivityInscriptionBinding binding;

    ProgressDialog progressD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Inscription");

        binding = ActivityInscriptionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.signupBTNinscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                progressD = ProgressDialog.show(InscriptionActivity.this, getText(R.string.con_con_title),
                        getText(R.string.con_con_message), true);
                new InscriptionActivity.DialogTask<>().execute();


                Intent i = new Intent(InscriptionActivity.this, AccueilActivity.class);
                SignupRequest user = new SigninRequest();
                user.username = binding.signupName.getText().toString();
                user.password = binding.signupPS.getText().toString();
                final Service service = RetrofitCookie.get();
                service.SignUP(user).enqueue(new Callback<SigninResponse>() {
                    @Override
                    public void onResponse(Call<SigninResponse> call, Response<SigninResponse> response) {
                        if (response.isSuccessful()) {
                            //tout est bon, commencre l'activitée et envooie le nom de l'utilisateur

                            i.putExtra("Nom", binding.signupName.getText().toString());
                            startActivity(i);
                        } else {
                            //identifiant incorrect
                            Toast.makeText(getApplicationContext(), R.string.con_error_invalid_signup , Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<SigninResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), R.string.con_error_connection, Toast.LENGTH_LONG).show();
                    }
                });



            }
        });

        binding.signupBTNRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
