package com.example.noglutenappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noglutenappandroid.Adapters.FavoritesAdapter;
import com.example.noglutenappandroid.Adapters.NotesAdapter;
import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class FavoritesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.favorites_fragment,null);

        RecyclerView recyclerViewFavorites= v.findViewById(R.id.recyclerview_favorites);

        Realm.init(getContext().getApplicationContext());
        Realm realm=Realm.getDefaultInstance();
        RealmQuery<YourRecipeNote> query = realm.where(YourRecipeNote.class);
        query.equalTo("type", "favorite");
        RealmResults notesList = query.findAll().sort("timeCreation", Sort.DESCENDING);

        recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        FavoritesAdapter favoritesAdapter =new FavoritesAdapter(getContext().getApplicationContext(), notesList );
        recyclerViewFavorites.setAdapter(favoritesAdapter);

        notesList.addChangeListener(new RealmChangeListener<RealmResults>() {
            @Override
            public void onChange(RealmResults realmResults) {
                favoritesAdapter.notifyDataSetChanged();
            }
        });
        return v;
    }
}
