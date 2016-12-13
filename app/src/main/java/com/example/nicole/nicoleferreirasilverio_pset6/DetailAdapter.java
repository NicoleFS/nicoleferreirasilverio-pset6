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
 * This is an adapter that puts strings in a listview.
 *
 * Created by Nicole on 13-12-2016.
 */

public class DetailAdapter extends ArrayAdapter<String>{


        public DetailAdapter(Context context, ArrayList<String> ingredients) {
            super(context, android.R.layout.simple_list_item_1, ingredients);
        }

        @NonNull
        @Override
        public View getView(int position, View view, ViewGroup parent){
            LayoutInflater theInflater = LayoutInflater.from(getContext());
            View theView = theInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            // get the ingredient
            String ingredient = getItem(position).toString();

            // put it in the textview of the listview
            TextView listTitle = (TextView) theView.findViewById(android.R.id.text1);
            listTitle.setText(ingredient);

            return theView;
        }

}
