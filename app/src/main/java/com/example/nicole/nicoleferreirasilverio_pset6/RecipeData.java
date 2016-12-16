package com.example.nicole.nicoleferreirasilverio_pset6;

/**
 * Created by Nicole on 12-12-2016.
 * This class is a custom data class for the recipes. Each recipe
 * has a title, imageURL, recipeID and sourceURL, which will be
 * displayed in different parts of the application.
 */

public class RecipeData {

    public String title;
    public String imageURL;
    public String recipeID;
    public String sourceURL;

    public RecipeData(String title, String imageURL, String recipeID, String sourceURL){
        this.title = title;
        this.imageURL = imageURL;
        this.recipeID = recipeID;
        this.sourceURL = sourceURL;

    }


    public void setTitle(String title){
        this.title = title;
    }

    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    public void setRecipeID(String recipeID){
        this.recipeID = recipeID;
    }

    public void setSourceURL(String sourceURL){
        this.sourceURL = sourceURL;
    }

    public String getTitle(){
        return title;
    }

    public String getImageURL(){
        return imageURL;
    }

    public String getRecipeID(){
        return recipeID;
    }

    public String getSourceURL(){
        return sourceURL;
    }
}
