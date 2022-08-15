package com.example.noglutenappandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.noglutenappandroid.Adapters.RecipeAdapter;
import com.example.noglutenappandroid.Listeners.RecipeResponseListener;
import com.example.noglutenappandroid.RecipeInformation.Recipe;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    RecipeAdapter adapter;
    RequestManager manager;
    RecyclerView recyclerViewHome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.home_fragment,null);

        recyclerViewHome=v.findViewById(R.id.recyclerview_home);

        manager=new RequestManager(getContext());
        manager.GetRecipes(listener);

        return v;
    }

    private final RecipeResponseListener listener=new RecipeResponseListener() {

        @Override
        public void FetchRecipe(List<Recipe> responses, String APImessage) {
            recyclerViewHome.setHasFixedSize(true);
            recyclerViewHome.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
            adapter = new RecipeAdapter(getContext(), responses);
            recyclerViewHome.setAdapter(adapter);
            recyclerViewHome.setVisibility(View.VISIBLE);

        }


        @Override
        public void ErrorRecipe(String APImessage) {
            recyclerViewHome.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(), APImessage, Toast.LENGTH_SHORT).show();
        }
    };


}
