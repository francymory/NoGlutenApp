package com.example.noglutenappandroid.Listeners;



import com.example.noglutenappandroid.RecipeInformation.Recipe;

import java.util.List;

//interfaccia che riceve i messaggi dell'API REST e se c'è il body con la lista (ARRAYLIST) di ricette
public interface RecipeResponseListener {
    void FetchRecipe(List<Recipe> response, String APImessage);
    void ErrorRecipe(String APImessage);
}
