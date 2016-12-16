package com.example.nicole.nicoleferreirasilverio_pset6;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class GroceryListActivity extends AppCompatActivity {

    // initialise an ArrayList called groceryIngredients
    ArrayList<String> groceryIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        // create a new ArrayList called neededIngredients
        ArrayList<String> neededIngredients;

        // check if the page is requested with or without extra data
        if (getIntent().getExtras() != null) {

            // fill neededIngredients with the ingredients that were sent via RecipeDetailActivity
            neededIngredients = getIntent().getExtras().getStringArrayList("neededIngredients");

            // fill the ListView with the ingredients from neededIngredient
            this.setData(neededIngredients);
        }
    }

    public void setData(ArrayList<String> groceries){

        // fill groceryIngredients with the list given to setData
        this.groceryIngredients = groceries;

        // create a new adapter with the correct attributes
        ListAdapter adapter = new DetailAdapter(this, groceries);

        // call the adapter on the ListView to fill it with data
        final ListView groceryList = (ListView) findViewById(R.id.groceries);
        groceryList.setAdapter(adapter);

        // create an onItemClick listener for the ListView
        groceryList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){


                TextView chosen = (TextView) view.findViewById(android.R.id.text1);

                // get the current color of the text in the TextView chosen
                int intColor = chosen.getCurrentTextColor();

                // change the color integer to a hexadecimal string
                String hexColor = String.format("#%06X", (0xFFFFFF & intColor));

                // if the color of the text is black, change to grey and otherwise
                if (Objects.equals(hexColor, "#000000")){
                    chosen.setTextColor(Color.parseColor("#D3D3D3"));

                }
                else{
                    chosen.setTextColor(Color.parseColor("#000000"));
                }


            }
        });

        // create an onItemLongClickListener for the ListView
        groceryList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){

                // if clicked on a TextView in the ListView, remove the data in that TextView from
                // groceryIngredients
                String ingredientChosen = parent.getItemAtPosition(position).toString();
                groceryIngredients.remove(ingredientChosen);

                // fill the ListView with the changed data
                setData(groceryIngredients);
                return true;
            }
        });
    }

}
