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

public class DetailsActivity extends AppCompatActivity {

    int id=0;
    RequestManager manager;
    TextView detailTitleInput;
    ImageView detailImageInput;
    TextView detailDescriptionInput;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        id = Integer.valueOf(getIntent().getStringExtra("id"));

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        Toolbar myToolbarFavorite = (Toolbar) findViewById(R.id.my_toolbarFavorite);
        setSupportActionBar(myToolbarFavorite);
        getSupportActionBar().setTitle("Recipe Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailTitleInput=findViewById(R.id.titleinputfavorite);
        detailImageInput=findViewById(R.id.image_addfavorite);
        detailDescriptionInput=findViewById(R.id.descriptioninputfavorite);
        detailDescriptionInput.setMovementMethod(new ScrollingMovementMethod());

        manager=new RequestManager(this);
        manager.GetFavoriteDetails(favoriteListener,id);
        dialog.show();
    }

    private  final FavoriteResponseListener favoriteListener=new FavoriteResponseListener() {
        @Override
        public void didFetch(FavoriteDetailsResponse response, String message) {
            dialog.dismiss();
            detailTitleInput.setText(response.title);
            detailDescriptionInput.setText(response.instructions);
            Picasso.get().load(response.image).into(detailImageInput);

        }

        @Override
        public void didError(String message) {

        }
    };
}