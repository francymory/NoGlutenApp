package com.example.noglutenappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class AddRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipes);

        MaterialButton buttonSave= findViewById(R.id.button_save);
        EditText titleInput=findViewById(R.id.titleinput);
        EditText descriptionInput=findViewById(R.id.descriptioninput);

        Realm.init(getApplicationContext());
        Realm realm= Realm.getDefaultInstance();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=titleInput.getText().toString();
                String description=descriptionInput.getText().toString();
                long timeCreation=System.currentTimeMillis();

                realm.beginTransaction();
                YourRecipeNote note=realm.createObject(YourRecipeNote.class);
                note.setTitle(title);
                note.setDescription(description);
                note.setTimeCreation(timeCreation);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}