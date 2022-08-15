package com.example.noglutenappandroid.RecipeInformation;

import java.util.ArrayList;
import java.util.List;

public class ExtendedIngredient {
    public int id;
    public String aisle;
    public String image;
    public String consistency;
    public String name;
    public String nameClean;
    public String original;
    public String originalName;
    public double amount;
    public String unit;
    public ArrayList<String> meta;
    public Measures measures;


    public int getId() {
        return id;
    }

    public String getAisle() {
        return aisle;
    }

    public String getImage() {
        return image;
    }

    public String getConsistency() {
        return consistency;
    }

    public String getName() {
        return name;
    }

    public String getNameClean() {
        return nameClean;
    }

    public String getOriginal() {
        return original;
    }

    public String getOriginalName() {
        return originalName;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public List<String> getMeta() {
        return meta;
    }

    public Measures getMeasures() {
        return measures;
    }
}

