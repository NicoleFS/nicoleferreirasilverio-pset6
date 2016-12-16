package com.example.nicole.nicoleferreirasilverio_pset6;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nicole on 12-12-2016.
 */

public class RecipeAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    MainActivity activity;

    // constructor
    public RecipeAsyncTask(MainActivity activity){
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    // onPreExecute()
    protected void onPreExecute(){
        Toast.makeText(context, "Getting data from server", Toast.LENGTH_SHORT).show();
    }

    // doInBackGround()
    protected String doInBackground(String... params){
        return HttpRequestHelper.downloadFromServer(params);
    }

    // onPostExecute()
    protected void onPostExecute(String result){
        super.onPostExecute(result);

        // check if there was any data found
        if (result.length() == 0){
            Toast.makeText(context, "No data found", Toast.LENGTH_LONG).show();
        }
        else{

            // initialise an ArrayList with recipe data
            ArrayList<RecipeData> recipeDataArrayList = new ArrayList<>();
            try {
                JSONObject resultObj = new JSONObject(result);

                // get the JSONArray with all the recipes including data
                JSONArray recipes = resultObj.getJSONArray("recipes");

                // for all the elements in the recipes JSONArray
                for (int i = 0; i < recipes.length(); i++){

                    // isolate a recipe, at position i in the JSONArray recipes
                    JSONObject recipe = recipes.getJSONObject(i);

                    // get the title of the recipe
                    String recipeTitle = recipe.getString("title");

                    // get the image URL of the recipe
                    String photoURL = recipe.getString("image_url");

                    // get the ID of the recipe
                    String recipeID = recipe.getString("recipe_id");

                    // get the source URL of the recipe
                    String instructionsURL = recipe.getString("source_url");

                    // create a new RecipeData element, recipeData, with the obtained attributes
                    RecipeData recipeData = new RecipeData(recipeTitle, photoURL, recipeID, instructionsURL);

                    // add the recipeData to the list of recipes
                    recipeDataArrayList.add(recipeData);
                }
            } catch (JSONException e){
                e.printStackTrace();
            }

            // fill the ListView in MainActivity with the recipes in recipeDataArrayList
            this.activity.setData(recipeDataArrayList);
        }
    }




}
