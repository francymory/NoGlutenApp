package com.example.noglutenappandroid;

import io.realm.RealmObject;

public class YourRecipeNote extends RealmObject {
    String title;
    String description;
    long timeCreation;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(long timeCreation) {
        this.timeCreation = timeCreation;
    }
}

