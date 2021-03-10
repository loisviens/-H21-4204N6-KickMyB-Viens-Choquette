package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.RetrofitUtil;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kickmyb.transfer.SigninRequest;
import org.kickmyb.transfer.SigninResponse;

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
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.vienschoquette.h21_4204n6_kickmyb_viens_choquette", appContext.getPackageName());
    }
    @Test
    public void testSimpleUtilisateur() throws IOException {
        Service service = RetrofitUtil.get();
        Call<String> call = service.test();
        Response<String> response = call.execute();
        String resultat = response.body();
        Log.i("RETROFIT", resultat);
    }
    @Test
    public void testsignin() throws IOException {
        Service service = RetrofitUtil.get();
        SigninRequest S = new SigninRequest();
        S.password = "123";
        S.username = "456";
        Call<SigninResponse> call = service.SignIN(S);
        Response<SigninResponse> response = call.execute();
        String resultat = response.body().username;
        Log.i("RETROFIT", resultat);
    }
}