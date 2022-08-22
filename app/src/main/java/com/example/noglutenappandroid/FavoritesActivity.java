package com.example.noglutenappandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noglutenappandroid.Listeners.FavoriteResponseListener;
import com.example.noglutenappandroid.RecipeInformation.FavoriteDetailsResponse;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class FavoritesActivity extends AppCompatActivity {

    int id=0;
    RequestManager manager;
    TextView favoriteTitleInput;
    ImageView favoriteImageInput;
    TextView favoriteDescriptionInput;
    MaterialButton buttonAdd;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        id = Integer.valueOf(getIntent().getStringExtra("id"));

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        Toolbar myToolbarFavorite = (Toolbar) findViewById(R.id.my_toolbarFavorite);
        setSupportActionBar(myToolbarFavorite);
        getSupportActionBar().setTitle("Add this recipe to favorites");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonAdd=findViewById(R.id.button_add);
        favoriteTitleInput=findViewById(R.id.titleinputfavorite);
        favoriteImageInput=findViewById(R.id.image_addfavorite);
        favoriteDescriptionInput=findViewById(R.id.descriptioninputfavorite);
        favoriteDescriptionInput.setMovementMethod(new ScrollingMovementMethod());

        manager=new RequestManager(this);
        manager.GetFavoriteDetails(favoriteListener,id);
        dialog.show();

    }


    private  final FavoriteResponseListener favoriteListener=new FavoriteResponseListener() {
        @Override
        public void didFetch(FavoriteDetailsResponse response, String message) {
            dialog.dismiss();
            favoriteTitleInput.setText(response.title);
            favoriteDescriptionInput.setText(response.instructions);
            Picasso.get().load(response.image).into(favoriteImageInput);

            Realm.init(getApplicationContext());
            Realm realm= Realm.getDefaultInstance();

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title=response.title.toString();
                    String description=response.instructions.toString();
                    long timeCreation=System.currentTimeMillis();

                    realm.beginTransaction();
                    YourRecipeNote note=realm.createObject(YourRecipeNote.class);
                    note.setTitle(title);
                    note.setDescription(description);
                    note.setTimeCreation(timeCreation);
                    note.setType("favorite");
                    realm.commitTransaction();
                    Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

        }

        @Override
        public void didError(String message) {

        }
    };
}