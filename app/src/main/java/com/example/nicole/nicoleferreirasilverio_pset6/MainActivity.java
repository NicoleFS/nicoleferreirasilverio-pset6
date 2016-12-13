package com.example.nicole.nicoleferreirasilverio_pset6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // initialiseer een ArrayList met receptendata
    ArrayList<RecipeData> foundRecipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void searchRecipes(View view){
        // voer de AsyncTask uit met de juiste context en de meegegeven user input
        EditText searchRecipe = (EditText) findViewById(R.id.editText);
        String userInput = searchRecipe.getText().toString();
        RecipeAsyncTask asyncTask = new RecipeAsyncTask(this);
        asyncTask.execute(userInput);
    }

    public void setData(ArrayList<RecipeData> recipedata){

        // vul foundRecipeList met de gevonden recepten (+ data)
        this.foundRecipeList = recipedata;

        // maak een nieuwe adapter aan met de juiste attributen
        ListAdapter adapter = new RecipeAdapter(this, recipedata);

        // zet de adapter op de listview om deze te vullen
        ListView recipeList = (ListView) findViewById(R.id.listView);
        recipeList.setAdapter(adapter);

        // maak een onItemClickListener voor de elementen in de listview
        recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                // isoleer het recept waar op geklikt is
                RecipeData chosenRecipe = (RecipeData) parent.getItemAtPosition(position);

                // vraag de ID op van het gekozen recept
                String recipeId = chosenRecipe.getRecipeID();

                // vraag de titel op van het gekozen recept
                String recipeTitle = chosenRecipe.getTitle();

                // vraag de URL van de bron van het recept op
                String recipeSource = chosenRecipe.getSourceURL();

                // vraag de URL van het plaatje van het gerecht op
                String imageURL = chosenRecipe.getImageURL();

                // maak een intent aan om naar de volgende activity te gaan, waar details van het
                // recept worden weergegeven
                Intent goToRecipeDetail = new Intent(view.getContext(), RecipeDetailActivity.class);

                // geef de ID, titel, URL van de bron en URL van het plaatje mee aan de volgende
                // activity en ga naar de volgende activity
                goToRecipeDetail.putExtra("recipeID", recipeId);
                goToRecipeDetail.putExtra("imageURL", imageURL);
                goToRecipeDetail.putExtra("recipeTitle", recipeTitle);
                goToRecipeDetail.putExtra("recipeSource", recipeSource);
                startActivity(goToRecipeDetail);
            }
        });
    }

    public void seeGroceryList(View view){

        // maak een intent aan om naar de volgende activity te gaan, waar het boodschappenlijstje
        // wordt weergegeven
        Intent goToGroceryList = new Intent(this, GroceryListActivity.class);

        // ga naar de volgende activity
        startActivity(goToGroceryList);
    }


}
