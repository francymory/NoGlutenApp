package com.example.noglutenappandroid.RecipeInformation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noglutenappandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends ArrayAdapter<Recipe> {
    public RecipeAdapter(Context context, int resource, List<Recipe> recipeList){
        super(context,resource,recipeList);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Recipe recipe = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_cell, parent, false);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.recipeName);
        ImageView iv = (ImageView) convertView.findViewById(R.id.recipeImage);


        tv.setText(recipe.getTitle());
        Picasso.get().load("https://spoonacular.com/recipeImages/"+recipe.id+"-90x90."+recipe.imageType).into(iv);

        return convertView;
    }
}
