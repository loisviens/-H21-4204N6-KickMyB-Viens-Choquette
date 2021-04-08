package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.RetrofitCookie;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kickmyb.transfer.SigninRequest;
import org.kickmyb.transfer.SigninResponse;
import org.kickmyb.transfer.SignupRequest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void testSimpleUtilisateur() throws IOException {
        Service service = RetrofitCookie.get();
        Call<String> call = service.test();
        Response<String> response = call.execute();
        String resultat = response.body();
        Log.i("RETROFIT", resultat);
    }
    @Test
    public void testSignIn() throws IOException {
        Service service = RetrofitCookie.get();
        SigninRequest S = new SigninRequest();
        S.password = "123";
        S.username = "456";
        Call<SigninResponse> call = service.SignIN(S);
        Response<SigninResponse> response = call.execute();
        String resultat = response.body().username;
        Log.i("RETROFIT", resultat);
    }
    @Test
    public void testSignUp() throws IOException {
        Service service = RetrofitCookie.get();
        SignupRequest S = new SignupRequest();
        S.password = "123";
        S.username = "456";
        Call<SigninResponse> call = service.SignUP(S);
        Response<SigninResponse> response = call.execute();
        String resultat = response.body().username;
        Log.i("RETROFIT", resultat);
    }
    @Test
    public void testSignOut() throws IOException {
        Service service = RetrofitCookie.get();
        Call<String> call = service.SignOUT();
        Response<String> response = call.execute();
        String resultat = response.body();
        Log.i("RETROFIT", resultat);
    }
}