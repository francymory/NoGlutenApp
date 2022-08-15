package com.example.noglutenappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.noglutenappandroid.Adapters.PagerAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout mtabLayout;
    TabItem homeItem, favoritesItem, yourRecipesItem;
    Toolbar mtoolBar ;
    PagerAdapter pagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtoolBar=findViewById(R.id.toolBar);
        setSupportActionBar(mtoolBar);

        homeItem=findViewById(R.id.tabHome);
        favoritesItem=findViewById(R.id.tabHeart);
        yourRecipesItem=findViewById(R.id.tabNote);

        viewPager=findViewById(R.id.fragmentContainer);

        mtabLayout=findViewById(R.id.tabLayout);

        pagerAdapter=new PagerAdapter(getSupportFragmentManager(),3);
        viewPager.setAdapter(pagerAdapter);

        mtabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0 || tab.getPosition()==1||tab.getPosition()==2){
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mtabLayout));



    }




}