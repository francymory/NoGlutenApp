package com.example.noglutenappandroid.Listeners;



import com.example.noglutenappandroid.RecipeInformation.Recipe;
import com.example.noglutenappandroid.RecipeInformation.RecipeResponse;

import java.util.List;

//interfaccia che riceve i messaggi dell'API REST e se c'è il body con la lista (ARRAYLIST) di ricette
public interface RecipeResponseListener {
    void FetchRecipe(List<Recipe> responses, String APImessage);
    void ErrorRecipe(String APImessage);
}
