package com.example.noglutenappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noglutenappandroid.RecipeInformation.Recipe;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    Recipe selectedRecipe;

    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.recipe_detail);
            getSelectedRecipe();
            setValues();
    }

    private void getSelectedRecipe(){
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        selectedRecipe = SearchActivity.recipeList.get(Integer.valueOf(parsedStringID));
    }

    private void setValues(){

        TextView tv = (TextView) findViewById(R.id.recipeName);
        ImageView iv = (ImageView) findViewById(R.id.recipeImage);


        tv.setText(selectedRecipe.getTitle());
        Picasso.get().load("https://spoonacular.com/recipeImages/"+selectedRecipe.id+"-90x90."+selectedRecipe.imageType).into(iv);

    }
}