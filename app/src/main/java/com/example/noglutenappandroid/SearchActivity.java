package com.example.noglutenappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noglutenappandroid.RecipeInformation.Recipe;
import com.example.noglutenappandroid.RecipeInformation.RecipeAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    public static ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
    private ListView listView;

    protected void onCreate(Bundle savedInstanceState){
        super .onCreate(savedInstanceState);
        setContentView(R.layout.search_fragment);

        setupData();
        setupList();
        setupOnclickListener();
    }

    private void setupData(){

    }

    private void setupList(){
        listView = (ListView) findViewById(R.id.recipesListView);

        RecipeAdapter adapter= new RecipeAdapter(getApplicationContext(), 0, recipeList);
        listView.setAdapter(adapter);
    }

    private void setupOnclickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Recipe selectRecipe = (Recipe) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent(getApplicationContext(), DetailActivity.class);
            }
        });
    }

}
