package com.example.nicole.nicoleferreirasilverio_pset6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class GroceryListActivity extends AppCompatActivity {

    // initialiseer een ArrayList genaamd groceryIngredients
    ArrayList<String> groceryIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        // maak een nieuwe ArrayList aan genaamd neededIngredients
        ArrayList<String> neededIngredients;

        // vul neededIngredients met de ingredienten die nog nodig zijn en doorgegeven zijn via
        // RecipeDetailActivity
        neededIngredients = getIntent().getExtras().getStringArrayList("neededIngredients");

        // vul de ListView met de benodigde ingredienten
        this.setData(neededIngredients);
    }

    public void setData(ArrayList<String> groceries){

        // vul groceryIngredients met de lijst die wordt meegegeven aan setData
        this.groceryIngredients = groceries;

        // maak een nieuwe adapter aan met de juiste attributen
        ListAdapter adapter = new DetailAdapter(this, groceries);

        // zet de adapter op de ListView om deze te vullen
        final ListView groceryList = (ListView) findViewById(R.id.groceries);
        groceryList.setAdapter(adapter);

//        ingredientsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//                String chosenIngredient = parent.getItemAtPosition(position).toString();
//                chosenIngredients.add(chosenIngredient);
//            }
//        });
    }

}
