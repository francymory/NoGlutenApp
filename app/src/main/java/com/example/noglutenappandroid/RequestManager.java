package com.example.noglutenappandroid;

import android.content.Context;

import com.example.noglutenappandroid.RecipeInformation.RecipeResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;  //classe per accedere al contesto dell'applicazione

    //classe per comunicare con il REST
    Retrofit retrofit= new Retrofit.Builder().baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    //creo interfaccia per fare richieste
    private interface CallRecipes{
        @GET("recipes/random")
        Call<RecipeResponse> callRecipeResponse(
                @Query("apiKey") String apiKey,
                @Query("number") String number
        );


    }



}
