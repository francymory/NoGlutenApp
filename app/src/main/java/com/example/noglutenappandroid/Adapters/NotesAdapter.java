package com.example.noglutenappandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noglutenappandroid.R;
import com.example.noglutenappandroid.YourRecipeNote;

import org.w3c.dom.Text;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{

    Context context;
    RealmResults<YourRecipeNote> notesList;

    public NotesAdapter(Context context, RealmResults<YourRecipeNote> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.list_notes,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        YourRecipeNote note=notesList.get(position);
        holder.titleOutput.setText(note.getTitle());
        holder.descriptionOutput.setText(note.getDescription());
        holder.timeOutput.setText(DateFormat.getDateInstance().format(note.getTimeCreation()));

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

    public class NotesViewHolder extends RecyclerView.ViewHolder{

        TextView titleOutput;
        TextView descriptionOutput;
        TextView timeOutput;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            titleOutput=itemView.findViewById(R.id.titleoutput);
            descriptionOutput=itemView.findViewById(R.id.descriptionoutput);
            timeOutput=itemView.findViewById(R.id.timeoutput);
        }
    }
}
