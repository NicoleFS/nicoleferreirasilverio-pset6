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
 * Created by Nicole on 13-12-2016
 * This class will obtain data through the JSONObject obtained by the
 * HttpRequestIngredients. This data will be shown in a ListView in the
 * RecipeDetailActivity.
 *
 */

public class DetailsAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    RecipeDetailActivity activity;

    // initialise an ArrayList called recipeIngredients
    ArrayList<String> recipeIngredients = new ArrayList<>();

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

        // check if there was any data found
        if (result.length() == 0){
            Toast.makeText(context, "No data found", Toast.LENGTH_LONG).show();
        }
        else{

            try {
                JSONObject resultObj = new JSONObject(result);

                // get the JSONArray with ingredients from the JSONObject recipe
                JSONArray ingredients = resultObj.getJSONObject("recipe").getJSONArray("ingredients");

                // make a string from every ingredient from the JSONArray and add it to recipeIngredients
                for (int i = 0; i < ingredients.length(); i++){
                    String ingredient = ingredients.getString(i);
                    recipeIngredients.add(ingredient);
                }

            } catch (JSONException e){
                e.printStackTrace();
            }

            // fill the ListView in RecipeDetailActivity with the ingredients
            this.activity.setData(recipeIngredients);
        }
    }
}
