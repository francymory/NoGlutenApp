package com.example.noglutenappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noglutenappandroid.Adapters.NotesAdapter;
import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class YourRecipesFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.your_recipes_fragment,null);

        RecyclerView recyclerViewYourRecipes=v.findViewById(R.id.recyclerview_yourRecipes);
        MaterialButton addButton=v.findViewById(R.id.button_add);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),AddRecipesActivity.class));
            }
        });

        Realm.init(getContext().getApplicationContext());
        Realm realm=Realm.getDefaultInstance();
        RealmQuery<YourRecipeNote> query = realm.where(YourRecipeNote.class);
        query.equalTo("type", "note");
        RealmResults notesList = query.findAll().sort("timeCreation", Sort.DESCENDING);

        recyclerViewYourRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        NotesAdapter notesAdapter=new NotesAdapter(getContext().getApplicationContext(), notesList );
        recyclerViewYourRecipes.setAdapter(notesAdapter);

        notesList.addChangeListener(new RealmChangeListener<RealmResults>() {
            @Override
            public void onChange(RealmResults realmResults) {
                notesAdapter.notifyDataSetChanged();
            }
        });
        return v;
    }
}
