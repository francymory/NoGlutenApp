package com.example.noglutenappandroid;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.noglutenappandroid.Listeners.RecipeResponseListener;
import com.example.noglutenappandroid.RecipeInformation.RecipeResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

    //creo interfaccia per fare richiesta di ricette al rest
    private interface CallRecipes{
        @GET("recipes/random")
        //metodo che ha come parametri la key,il numero di ricette, e i tags che le ricette hanno/
        //torna un oggetto Call<T> che può essere eseguito (fatta la chiamata) con .enqueue
        Call<RecipeResponse> callRecipeResponse(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags") List<String> tags
        );
    }

    //metodo che fa la richiesta e setta il listener con la risposta dell'API
    //il parametro tags è la lista dei tag delle ricette (values-> strings)
    public void GetRecipes(RecipeResponseListener listener, List<String> tags){
        //creo un oggetto CallRecipes
        CallRecipes callRecipes= retrofit.create(CallRecipes.class);
        //creo oggetto Call<RecipeResponse> per fare la richiesta all'api
        Call<RecipeResponse> request = callRecipes.callRecipeResponse("1be787c3bec54b6cad33efe22405344e", "90", tags);
        //eseguo richiesta e accetta come parametro un oggetto Callback che notifica la risposta/errore
        request.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(@NonNull Call<RecipeResponse> call, @NonNull Response<RecipeResponse> response) {
                if(!response.isSuccessful()){
                    listener.ErrorRecipe(response.message());
                    return;
                }


                assert response.body() != null;
                listener.FetchRecipe(response.body().getRecipes, response.message());

            }

            @Override
            public void onFailure(@NonNull Call<RecipeResponse> call, Throwable t) {
                listener.ErrorRecipe(t.getMessage());  //se la richiesta fallisce proprio voglio il messaggio di errore
            }
        });
    }



}
