package com.example.noglutenappandroid.Adapters;


import android.content.Context;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.noglutenappandroid.Listeners.FavoritesClickListener;
import com.example.noglutenappandroid.R;
import com.example.noglutenappandroid.RecipeInformation.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{

    Context context;
    List<Recipe> list;
    FavoritesClickListener favoritesListener;


    public RecipeAdapter(Context context, List<Recipe> list,FavoritesClickListener favoritesListener) {
        this.context = context;
        this.list = list;
        this.favoritesListener=favoritesListener;

    }

    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.list_recipes, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {


        if(list.get(position).isGlutenFree()) {

            holder.title.setText(list.get(position).getTitle());
            holder.time.setText((list.get(position).getReadyInMinutes()) + " Min");
            holder.servings.setText((list.get(position).getServings()) + " Servings");
            Picasso.get().load(list.get(position).getImage()).into(holder.imageView_food);
            if (list.get(position).isVegetarian()) {
                holder.textView_tags_frees.setText(holder.textView_tags_frees.getText() + " Vegetarian");
            }
            if (list.get(position).isVegan()) {
                holder.textView_tags_frees.setText(holder.textView_tags_frees.getText() + " Vegan");
            }
            if (list.get(position).isGlutenFree()) {
                holder.textView_tags_frees.setText(holder.textView_tags_frees.getText() + " GlutenFree");
            }
            if (list.get(position).isDairyFree()) {
                holder.textView_tags_frees.setText(holder.textView_tags_frees.getText() + " DairyFree");
            }

            holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    favoritesListener.onClick(String.valueOf(list.get(holder.getAbsoluteAdapterPosition()).getId()));
                }
            });

        }

    }

    @Override
    public int getItemCount() {

        return list.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {


        ImageView imageView_food;
        CardView home_container;
        TextView title, servings, time, textView_tags_frees;
        ImageButton favoriteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_tags_frees = itemView.findViewById(R.id.textView_tags_frees);
            imageView_food = itemView.findViewById(R.id.imageView_food);
            home_container = itemView.findViewById(R.id.home_list_container);
            title = itemView.findViewById(R.id.textView_titleRecipe);
            servings = itemView.findViewById(R.id.textView_serving);
            time = itemView.findViewById(R.id.textView_time);
            favoriteButton=itemView.findViewById(R.id.favoriteButton);
        }
    }
}

