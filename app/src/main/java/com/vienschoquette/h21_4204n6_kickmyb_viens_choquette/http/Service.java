package com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.http;

import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.transfer.Repo;
import com.vienschoquette.h21_4204n6_kickmyb_viens_choquette.transfer.Utilisateur;

import org.kickmyb.transfer.AddTaskRequest;
import org.kickmyb.transfer.HomeItemResponse;
import org.kickmyb.transfer.SigninRequest;
import org.kickmyb.transfer.SigninResponse;
import org.kickmyb.transfer.SignupRequest;
import org.kickmyb.transfer.TaskDetailResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Service {

    @GET("test")
    Call<String> test();

    @POST("/api/id/signin")
    Call<SigninResponse> SignIN(@Body SigninRequest S);

    @POST("/api/id/signup")
    Call<SigninResponse> SignUP(@Body SignupRequest S);

    @POST("/api/id/signout")
    Call<Void> SignOUT();                              // pas certain du Call<String>. il est pas censer retourner rien...

    @POST("/api/add")
    Call<Void> ListAdd(@Body AddTaskRequest T);       // pas certain du Call<String>. il est pas censer retourner rien...

    @GET("/api/home")
    Call<List<HomeItemResponse>> ListGet();

    @GET("/api/detail/{id}")
    Call<TaskDetailResponse> DetailTache(@Path("id") Long Id );

    @GET("/api/progress/{id}/{valeur}")
    Call<String> TachePourcentageChange(@Path("id") Long Id,@Path("valeur") int Valeur );  //un peut de dificultée a faire...
}
