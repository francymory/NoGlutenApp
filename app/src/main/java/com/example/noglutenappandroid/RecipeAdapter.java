package com.example.noglutenappandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noglutenappandroid.RecipeInformation.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{
    Context context;
    List<Recipe> list;


    public RecipeAdapter(Context context, List<Recipe> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_recipes, null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).title);
        holder.title.setSelected(true);
        holder.time.setText(String.valueOf(list.get(position).readyInMinutes)+" Min");
        holder.servings.setText(String.valueOf(list.get(position).servings)+" Servings");
        Picasso.get().load(list.get(position).image).into(holder.imageView_food);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_food;
        CardView home_container;
        TextView title, servings, time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_food=itemView.findViewById(R.id.imageView_food);
            home_container=itemView.findViewById(R.id.home_list_container);
            title=itemView.findViewById(R.id.textView_titleRecipe);
            servings= itemView.findViewById(R.id.textView_serving);
            time=itemView.findViewById(R.id.textView_time);
        }
    }
}
