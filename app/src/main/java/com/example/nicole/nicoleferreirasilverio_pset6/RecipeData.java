package com.example.nicole.nicoleferreirasilverio_pset6;

/**
 * Created by Nicole on 12-12-2016.
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

    // geef het recept een titel
    public void setTitle(String title){
        this.title = title;
    }

    // geef het recept een URL van het plaatje van het gerecht
    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    // geef het recept een ID
    public void setRecipeID(String recipeID){
        this.recipeID = recipeID;
    }

    // geef het recept een URL van de website waar het recept vandaan komt
    public void setSourceURL(String sourceURL){
        this.sourceURL = sourceURL;
    }

    // vraag de titel op van het recept
    public String getTitle(){
        return title;
    }

    // vraag de URL van het plaatje van het gerecht op
    public String getImageURL(){
        return imageURL;
    }

    // vraag de ID van het recept op
    public String getRecipeID(){
        return recipeID;
    }

    // vraag de URL van de website waar het recept vandaan komt op
    public String getSourceURL(){
        return sourceURL;
    }
}
