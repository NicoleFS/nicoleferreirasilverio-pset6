package com.example.nicole.nicoleferreirasilverio_pset6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class RecipeDetailActivity extends AppCompatActivity {

    // initialiseer de ArrayLists foundIngredientList en chosenIngredients
    ArrayList<String> foundIngredientList;
    ArrayList<String> chosenIngredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        // haal de ID van het recept op uit de intent
        String idRecipe = getIntent().getExtras().getString("recipeID");

        // haal de titel van het recept op uit de intent
        String titleRecipe = getIntent().getExtras().getString("recipeTitle");

        // haal de URL van de website waar het recept vandaan komt op uit de intent
        String sourceRecipe = getIntent().getExtras().getString("recipeSource");

        // zet de titel van het recept in de juiste textview
        TextView titleText = (TextView) findViewById(R.id.recipe_title);
        titleText.setText(titleRecipe);

        // zet de URL van de bron website in de juiste textview
        TextView sourceText = (TextView) findViewById(R.id.recipe_source);
        sourceText.setText(sourceRecipe);

        // voer de tweede AsyncTask uit met de juiste parameters
        DetailsAsyncTask AsyncTask = new DetailsAsyncTask(this);
        AsyncTask.execute(idRecipe);
    }

    public void setData(ArrayList<String> ingredientdata){

        // vul de lijst foundIngredientList met de ingredienten horende bij het recept
        this.foundIngredientList = ingredientdata;

        // maak een nieuwe adapter aan met de juiste attributen
        ListAdapter adapter = new DetailAdapter(this, ingredientdata);

        // zet de adapter op de listview om deze te vullen
        final ListView ingredientsList = (ListView) findViewById(R.id.ingredients);
        ingredientsList.setAdapter(adapter);

        // maak een onItemClickListener voor de elementen in de listview
        ingredientsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                // maak een string van het item waar op geklikt is
                String chosenIngredient = parent.getItemAtPosition(position).toString();

                // voeg deze string toe aan de lijst chosenIngredients
                chosenIngredients.add(chosenIngredient);
            }
        });
    }

    public void fillAndGoToGroceries(View view){

        // maak een nieuwe intent aan om naar de volgende activity te gaan, de grocery list
        Intent goToFilledGroceryList = new Intent(this, GroceryListActivity.class);

        // geef de intent de lijst met aangeklikte ingredienten mee
        goToFilledGroceryList.putExtra("neededIngredients", chosenIngredients);

        // ga naar de volgende activity
        startActivity(goToFilledGroceryList);
    }




}
