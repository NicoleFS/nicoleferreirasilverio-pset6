package com.example.nicole.nicoleferreirasilverio_pset6;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 *
 * Created by Nicole on 12-12-2016
 * In this activity the user can see more detailed information
 * about the chosen recipe. There is a possibility to visit
 * the recipe's instructions on the original publishers website,
 * shown in this activity, as well as the possibility to save
 * the needed ingredients in a grocery list
 *
 */

public class RecipeDetailActivity extends AppCompatActivity {

    // initialise the ArrayLists foundIngredientList and chosenIngredients
    // and the string sourceRecipe
    ArrayList<String> foundIngredientList;
    ArrayList<String> chosenIngredients = new ArrayList<>();
    String sourceRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        // get the ID of the recipe from the intent
        String idRecipe = getIntent().getExtras().getString("recipeID");

        // get the title of the recipe from the intent
        String titleRecipe = getIntent().getExtras().getString("recipeTitle");

        // get the source URL of the recipe from the intent
        sourceRecipe = getIntent().getExtras().getString("recipeSource");

        // get the image URL of the recipe from the intent
        String image = getIntent().getExtras().getString("imageURL");

        // put the title of the recipe in the correct TextView
        TextView titleText = (TextView) findViewById(R.id.recipe_title);
        titleText.setText(titleRecipe);

        // put the source URL in the correct TextView
        TextView sourceText = (TextView) findViewById(R.id.recipe_source);
        sourceText.setText(sourceRecipe);

        // show the image in a webview
        WebView web = (WebView) findViewById(R.id.recipe_image);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        web.loadUrl(image);

        // execute the details AsyncTask
        DetailsAsyncTask AsyncTask = new DetailsAsyncTask(this);
        AsyncTask.execute(idRecipe);
    }

    // method to fill the ListView with data
    public void setData(ArrayList<String> ingredientdata){

        // fill the list foundIngredientList with the recipe's ingredients
        this.foundIngredientList = ingredientdata;

        // create a new adapter with the correct attributes
        ListAdapter adapter = new DetailAdapter(this, ingredientdata);

        // call the adapter on the ListView to fill it with data
        final ListView ingredientsList = (ListView) findViewById(R.id.ingredients);
        ingredientsList.setAdapter(adapter);

        // create an onItemClickListener for the elements in the ListView
        ingredientsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                // get the ingredient clicked on
                String chosenIngredient = parent.getItemAtPosition(position).toString();

                // check if the ingredient is already in the list with needed ingredients
                if (chosenIngredients.contains(chosenIngredient)){
                    Toast.makeText(RecipeDetailActivity.this,
                            "This ingredient is already in your grocery list",
                            Toast.LENGTH_SHORT).show();
                } else {

                    // if not, add the ingredient to the list
                    chosenIngredients.add(chosenIngredient);
                }


            }
        });
    }

    // method to go to the grocery list, with the list of needed ingredients send to the activity
    public void fillAndGoToGroceries(View view){

        // create a new intent to go to the grocery list
        Intent goToFilledGroceryList = new Intent(this, GroceryListActivity.class);

        // send the list with ingredients to the grocery list activity
        goToFilledGroceryList.putExtra("neededIngredients", chosenIngredients);

        // go to the next activity
        startActivity(goToFilledGroceryList);
    }

    // method to visit the instructions on original publisher's website
    public void goToUrl (View view) {
        Uri uriUrl = Uri.parse(sourceRecipe);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);

    }
}
