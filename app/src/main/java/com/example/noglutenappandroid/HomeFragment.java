package com.example.noglutenappandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.noglutenappandroid.Adapters.RecipeAdapter;
import com.example.noglutenappandroid.Listeners.DetailClickListener;
import com.example.noglutenappandroid.Listeners.FavoritesClickListener;
import com.example.noglutenappandroid.Listeners.RecipeResponseListener;
import com.example.noglutenappandroid.RecipeInformation.Recipe;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    RecipeAdapter adapter;
    RequestManager manager;
    RecyclerView recyclerViewHome;
    List<String> tags = new ArrayList<>();
    ProgressDialog dialog;
    SearchView searchView;
    Spinner spMeal;
    Spinner spCusine;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.home_fragment,null);
        recyclerViewHome=v.findViewById(R.id.recyclerview_home);

        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Loading...");

        spCusine = v.findViewById(R.id.sp_cusine);
        spMeal = v.findViewById((R.id.sp_meal));

        ArrayAdapter arrayAdapterCusine = ArrayAdapter.createFromResource(getContext(), R.array.cusine_tags, R.layout.sp_cusine_text);
        arrayAdapterCusine.setDropDownViewResource(R.layout.sp_cusine_innertext);
        spCusine.setAdapter(arrayAdapterCusine);
        spCusine.setOnItemSelectedListener(spinnerSelectedListener);


        ArrayAdapter arrayAdapterMeal = ArrayAdapter.createFromResource(getContext(), R.array.meal_tags, R.layout.sp_meal_text);
        arrayAdapterMeal.setDropDownViewResource(R.layout.sp_meal_innertext);
        spMeal.setAdapter(arrayAdapterMeal);
        spMeal.setOnItemSelectedListener(spinnerSelectedListener);

        manager=new RequestManager(getContext());

        searchView= v.findViewById(R.id.recipesListSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.GetRecipes(listener, tags);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });
        return v;
    }

    private final RecipeResponseListener listener=new RecipeResponseListener() {

        @Override
        public void FetchRecipe(List<Recipe> responses, String APImessage) {
            dialog.dismiss();
            recyclerViewHome.setHasFixedSize(true);
            recyclerViewHome.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
            adapter = new RecipeAdapter(getContext(), responses,favoritesClickListener, detailClickListener);
            recyclerViewHome.setAdapter(adapter);
            recyclerViewHome.setVisibility(View.VISIBLE);

        }


        @Override
        public void ErrorRecipe(String APImessage) {
            recyclerViewHome.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(), APImessage, Toast.LENGTH_SHORT).show();
        }
    };

    private final FavoritesClickListener favoritesClickListener =new FavoritesClickListener() {
        @Override
        public void onClick(String text) {
            startActivity(new Intent(getContext(), FavoritesActivity.class).putExtra("id", text));

        }
    };

    private final DetailClickListener detailClickListener =new DetailClickListener() {

        @Override
        public void onClick(String text) {
            startActivity(new Intent(getContext(), DetailsActivity.class).putExtra("id", text));

        }
    };

    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString());
            manager.GetRecipes(listener, tags);
            dialog.show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


}
