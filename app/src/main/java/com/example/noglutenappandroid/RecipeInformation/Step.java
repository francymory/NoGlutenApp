package com.example.noglutenappandroid.RecipeInformation;

import java.util.ArrayList;
import java.util.List;

public class Step {
    public int number;
    public String step;
    public ArrayList<Ingredient> ingredients;
    public ArrayList<Equipment> equipment;
    public Length length;

    public int getNumber() {
        return number;
    }

    public String getStep() {
        return step;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public Length getLength() {
        return length;
    }

}
