package com.example.noglutenappandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noglutenappandroid.Listeners.RecipeResponseListener;
import com.example.noglutenappandroid.RecipeInformation.Recipe;
import com.example.noglutenappandroid.RecipeInformation.RecipeResponse;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    List<Recipe>  list;
    RecipeAdapter adapter;
    RequestManager manager;
    private RecyclerView recyclerViewHome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.home_fragment,null);

        recyclerViewHome=v.findViewById(R.id.recyclerview_home);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));

        manager=new RequestManager(getContext());
        manager.GetRecipes(recipeResponseListener);


        return v;
    }

    private final RecipeResponseListener recipeResponseListener=new RecipeResponseListener() {

        @Override
        public void FetchRecipe(List<Recipe> responses, String APImessage) {
            recyclerViewHome.setHasFixedSize(true);
            recyclerViewHome.setLayoutManager(new GridLayoutManager(getContext(),1));
            adapter=new RecipeAdapter(getContext(),responses);
            recyclerViewHome.setAdapter(adapter);
        }


        @Override
        public void ErrorRecipe(String APImessage) {

        }
    };


}
