package com.example.noglutenappandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    List<String> tags;
    ProgressDialog dialog;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.home_fragment,null);
        recyclerViewHome=v.findViewById(R.id.recyclerview_home);

        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Loading...");


        manager=new RequestManager(getContext());
        tags=new ArrayList<>();

        tags.add("main course");
        tags.add("side dish");
        tags.add("dessert");
        tags.add("appetizer");
        tags.add("salad");
        tags.add("bread");
        tags.add("breakfast");
        tags.add("soup");
        tags.add("beverage");
        tags.add("sauce");
        tags.add("marinade");
        tags.add("fingerfood");
        tags.add("snack");
        tags.add("drink");

        tags.add("African");
        tags.add("American");
        tags.add("British");
        tags.add("Cajun");
        tags.add("Caribbean");
        tags.add("Chinese");
        tags.add("Eastern European");
        tags.add("European");
        tags.add("French");
        tags.add("German");
        tags.add("Greek");
        tags.add("Indian");
        tags.add("Irish");
        tags.add("Italian");
        tags.add("Japanese");
        tags.add("Jewish");
        tags.add("Korean");
        tags.add("Latin");
        tags.add("Mediterranean");
        tags.add("Mexican");
        tags.add("Middle Eastern");
        tags.add("Nordic");
        tags.add("Southern");
        tags.add("Spanish");
        tags.add("Thai");
        tags.add("Vietnamese");

        manager.GetRecipes(listener, tags);
        dialog.show();

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


}
