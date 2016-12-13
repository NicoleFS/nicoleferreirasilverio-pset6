package com.example.nicole.nicoleferreirasilverio_pset6;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Nicole on 13-12-2016.
 */

public class DetailsAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    RecipeDetailActivity activity;

    // initialiseer een ArrayList genaamd recipeIngredients
    ArrayList<String> recipeIngredients = new ArrayList<String>();

    // constructor
    public DetailsAsyncTask(RecipeDetailActivity activity){
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    // onPreExecute()
    protected void onPreExecute(){
        Toast.makeText(context, "Getting data from server", Toast.LENGTH_LONG).show();
    }

    // doInBackGround()
    protected String doInBackground(String... params){
        return HttpRequestIngredients.downloadFromServer(params);
    }

    // onPostExecute()
    protected void onPostExecute(String result){
        super.onPostExecute(result);

        // checken of result uberhaupt iets heeft binnengekregen
        if (result.length() == 0){
            Toast.makeText(context, "No data found", Toast.LENGTH_LONG).show();
        }
        else{

            try {
                JSONObject resultObj = new JSONObject(result);

                // haal de JSONArray met ingredienten uit het JSONObject
                JSONArray ingredients = resultObj.getJSONObject("recipe").getJSONArray("ingredients");

                // haal elk ingredient uit de JSONArray, maak er een string van en voeg het toe aan
                // de ArrayList recipeIngredients
                for (int i = 0; i < ingredients.length(); i++){
                    String ingredient = ingredients.getString(i);

                    recipeIngredients.add(ingredient);
                }

            } catch (JSONException e){
                e.printStackTrace();
            }
            // vul de ListView in RecipeDetailActivity met de ingredienten
            this.activity.setData(recipeIngredients);
        }
    }
}
