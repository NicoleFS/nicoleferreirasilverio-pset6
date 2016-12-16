package com.example.nicole.nicoleferreirasilverio_pset6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Nicole on 9-12-2016.
 * In this activity the user can enter one or more ingredients
 * they want to cook with and let the app search for recipes with
 * one or more of those ingredients. The collected data will be
 * shown in a ListView and the user can select a recipe by clicking
 * on it, which will redirect them to a page with more details
 * about the chosen recipe. There is also a button to visit the
 * user's grocery list or the login page.
 */

public class MainActivity extends AppCompatActivity {

    // initialise an ArrayList to save the recipedata in
    ArrayList<RecipeData> foundRecipeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // method to search for recipes including ingredient(s) the user entered
    public void searchRecipes(View view){

        // execute the AsyncTask with the right context and user input, given in the EditText
        EditText searchRecipe = (EditText) findViewById(R.id.editText);
        String userInput = searchRecipe.getText().toString();
        RecipeAsyncTask asyncTask = new RecipeAsyncTask(this);
        asyncTask.execute(userInput);
    }

    // method to fill the ListView with data
    public void setData(ArrayList<RecipeData> recipedata){

        // fill foundRecipeList with the found recipes including their data
        this.foundRecipeList = recipedata;

        // create a new adapter with the right attributes
        ListAdapter adapter = new RecipeAdapter(this, recipedata);

        // call the adapter on the ListView to fill it with data
        ListView recipeList = (ListView) findViewById(R.id.listView);
        recipeList.setAdapter(adapter);

        // create an onItemClickListener for the elements in the ListView
        recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                // isolate the recipe the user clicked on
                RecipeData chosenRecipe = (RecipeData) parent.getItemAtPosition(position);

                // get the ID from the chosen recipe
                String recipeId = chosenRecipe.getRecipeID();

                // get the title from the chosen recipe
                String recipeTitle = chosenRecipe.getTitle();

                // get the source URL from the chosen recipe
                String recipeSource = chosenRecipe.getSourceURL();

                // get the image URL from the chosen recipe
                String imageURL = chosenRecipe.getImageURL();

                // create an intent to go to the RecipeDetailActivity, where details about the
                // recipe will be shown
                Intent goToRecipeDetail = new Intent(view.getContext(), RecipeDetailActivity.class);

                // show the user what recipe they chose
                Toast.makeText(MainActivity.this, "You chose " + recipeTitle, Toast.LENGTH_SHORT).show();

                // send the ID, title, source URL and image URL of the chosen recipe to the next activity
                goToRecipeDetail.putExtra("recipeID", recipeId);
                goToRecipeDetail.putExtra("imageURL", imageURL);
                goToRecipeDetail.putExtra("recipeTitle", recipeTitle);
                goToRecipeDetail.putExtra("recipeSource", recipeSource);

                // go to RecipeDetailActivity
                startActivity(goToRecipeDetail);
            }
        });
    }

    // method to be redirected to the grocery list
    public void seeGroceryList(View view){

        // create an intent to go to the GroceryListActivity
        Intent goToGroceryList = new Intent(this, GroceryListActivity.class);

        // go to GroceryListActivity
        startActivity(goToGroceryList);
    }

    // method to be redirected to the login page
    public void seeLoginPage(View view){

        Intent goToLogin = new Intent(this, LoginActivity.class);

        startActivity(goToLogin);
    }


}
