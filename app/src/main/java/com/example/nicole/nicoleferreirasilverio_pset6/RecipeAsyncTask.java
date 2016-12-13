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
        Toast.makeText(context, "Getting data from server", Toast.LENGTH_LONG).show();
    }

    // doInBackGround()
    protected String doInBackground(String... params){
        return HttpRequestHelper.downloadFromServer(params);
    }

    // onPostExecute()
    protected void onPostExecute(String result){
        super.onPostExecute(result);

        // checken of result uberhaupt iets heeft binnengekregen
        if (result.length() == 0){
            Toast.makeText(context, "No data found", Toast.LENGTH_LONG).show();
        }
        else{

            // initialiseer een ArrayList met receptendata
            ArrayList<RecipeData> recipeDataArrayList = new ArrayList<>();
            try {
                JSONObject resultObj = new JSONObject(result);

                // haal de JSONArray met recepten uit het JSONObject met alle resultaten
                JSONArray recipes = resultObj.getJSONArray("recipes");

                // voor alle elementen in de JSONArray met recepten
                for (int i = 0; i < recipes.length(); i++){

                    // isoleer een recept, op plek i in de JSONArray met recepter
                    JSONObject recipe = recipes.getJSONObject(i);

                    // haal de titel van het recept op
                    String recipeTitle = recipe.getString("title");

                    // haal de URL van het plaatje van het recept op
                    String photoURL = recipe.getString("image_url");

                    // haal de ID van het recept op
                    String recipeID = recipe.getString("recipe_id");

                    // haal de URL van de bron van het recept op
                    String instructionsURL = recipe.getString("source_url");

                    // maak een nieuwe RecipeData aan met de opgehaalde attributen
                    RecipeData recipeData = new RecipeData(recipeTitle, photoURL, recipeID, instructionsURL);

                    // voeg de recipeData toe aan de lijst met recepten inclusief alle data
                    recipeDataArrayList.add(recipeData);
                }
            } catch (JSONException e){
                e.printStackTrace();
            }
            this.activity.setData(recipeDataArrayList);
        }
    }




}
