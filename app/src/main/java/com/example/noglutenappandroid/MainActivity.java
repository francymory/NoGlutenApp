package com.example.noglutenappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;


import com.example.noglutenappandroid.Adapters.PagerAdapter;
import com.google.android.material.tabs.TabItem;
import com.example.noglutenappandroid.RecipeInformation.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,  R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }


    private void initSearchWidgets(){
        SearchView searchView = (SearchView) findViewById(R.id.recipesListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override

            public boolean onQueryTextSubmit(String query) {
                return false;

            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0 || tab.getPosition()==1||tab.getPosition()==2){
                    pagerAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public boolean onQueryTextChange(String query) {
                ArrayList<Recipe> filteredRecipes = new ArrayList<Recipe>();

                for(Recipe recipe: filteredRecipes){
                    if(recipe.getTitle().toLowerCase().contains(query.toLowerCase())){
                        filteredRecipes.add(recipe);
                    }

                }
                return false;
            }
        });
    }


}