package com.example.noglutenappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout mtabLayout;
    TabItem homeItem, favoritesItem, yourRecipesItem;
    Toolbar mtoolBar ;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtoolBar=findViewById(R.id.toolBar);
        setSupportActionBar(mtoolBar);



    }




}