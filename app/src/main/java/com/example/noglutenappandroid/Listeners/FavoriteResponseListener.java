package com.example.noglutenappandroid.Listeners;

import com.example.noglutenappandroid.RecipeInformation.FavoriteDetailsResponse;
import com.example.noglutenappandroid.RecipeInformation.InstructionResponse;

import java.util.List;

public interface FavoriteResponseListener {
    void didFetch(FavoriteDetailsResponse response, String message);
    void didError(String message);
}
