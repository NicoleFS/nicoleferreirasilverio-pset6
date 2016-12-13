package com.example.nicole.nicoleferreirasilverio_pset6;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nicole on 9-12-2016.
 */

public class RecipeAdapter extends ArrayAdapter<RecipeData>{


        public RecipeAdapter(Context context, ArrayList<RecipeData> foundRecipeData) {
        super(context, android.R.layout.simple_list_item_1, foundRecipeData);
    }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(android.R.layout.simple_list_item_1, parent, false);

        // get the title of the recipe
        String titleRecipe = getItem(position).getTitle();

        // fill the textviews of the listview with the titles of the recipes
        TextView listTitle = (TextView) theView.findViewById(android.R.id.text1);
        listTitle.setText(titleRecipe);

        return theView;
    }

}
