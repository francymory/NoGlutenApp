package com.example.noglutenappandroid.Adapters;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noglutenappandroid.FavoritesActivity;
import com.example.noglutenappandroid.R;
import com.example.noglutenappandroid.YourRecipeNote;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>{

    Context context;
    RealmResults<YourRecipeNote> notesList;

    public FavoritesAdapter(Context context, RealmResults<YourRecipeNote> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoritesViewHolder(LayoutInflater.from(context).inflate(R.layout.list_favorites,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {

        YourRecipeNote note=notesList.get(position);
        holder.titleOutputFav.setText(note.getTitle());
        holder.descriptionOutputFav.setText(note.getDescription());
        holder.timeOutputFav.setText(DateFormat.getDateInstance().format(note.getTimeCreation()));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu menuDelete=new PopupMenu(context,view);
                menuDelete.getMenu().add("DELETE");
                menuDelete.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getTitle().equals("DELETE")){
                            Realm realm= Realm.getDefaultInstance();
                            realm.beginTransaction();
                            note.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });

                menuDelete.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
         return notesList.size();
    }


    public class FavoritesViewHolder extends RecyclerView.ViewHolder{
        TextView titleOutputFav;
        TextView descriptionOutputFav;
        TextView timeOutputFav;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            titleOutputFav=itemView.findViewById(R.id.titleoutputFavorite);
            descriptionOutputFav=itemView.findViewById(R.id.descriptionoutputFavorite);
            descriptionOutputFav.setMovementMethod(new ScrollingMovementMethod());
            timeOutputFav=itemView.findViewById(R.id.timeoutputFavorite);
        }

    }

}
