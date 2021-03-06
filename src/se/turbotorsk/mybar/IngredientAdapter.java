/*
New BSD License
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se

Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice,
  this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.
 * Neither the name of the MyBar nor the names of its contributors may be 
  used to endorse or promote products derived from this software without
  specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY 
OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package se.turbotorsk.mybar;

import java.util.LinkedList;
import se.turbotorsk.mybar.model.Ingredient;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This activity handles the ingredients.
 */
public class IngredientAdapter extends ArrayAdapter<LinkedList> {

    public IngredientAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    private LinkedList<Ingredient> items;

    /**
     * Constructor for IngredientAdapter.
     * 
     * @param context
     * @param resource
     * @param items
     */
    public IngredientAdapter(Context context, int resource, LinkedList items) {
        // Call the extended constructor.
        super(context, resource, items);
        // initialize variables.
        this.items = items;

    }

    /**
     * This method gets the view for all ingredients.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Set the view that was received through the method call.
        View v = convertView;
        // If the view is not null, inflate the layout.
        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.rowlayout, null);

        }
        // Initialize an ingredient object to the list item.
        Ingredient p = items.get(position);
        // If the ingredient object is not null, set the different
        // parts of the list item to their respective XML-part.
        if (p != null) {
            TextView tt = (TextView) v.findViewById(R.id.drink);
            TextView tt2 = (TextView) v.findViewById(R.id.ingredients);
            TextView tt3 = (TextView) v.findViewById(R.id.rating);
            ImageView iv = (ImageView) v.findViewById(R.id.list_image);

            if (tt != null) {
                // Sets the name of the ingredients to the text view.
                tt.setText(p.getName());
            }
            if (tt2 != null) {
                // Sets the type of the ingredients to the text view.
                tt2.setText(p.getType());
            }
            if (tt3 != null) {
                // Sets the ABV of the ingredients to the text view.
                tt3.setText("ABV: " + p.getAlcoholcontent() + "%");
            }
            if (iv != null) {
                // Sets the image of the ingredient to the image view.
                iv.setImageResource(R.drawable.bottle);
            }
        }

        return v;
    }

    /**
     * This method is fetching the name of an ingredient.
     * 
     * @param position
     * @return name of ingredient
     */
    public String getIngredientName(int position) {
        Ingredient ingredient = items.get(position);
        return ingredient.getName();
    }

    /**
     * This method is fetching the description of an ingredient.
     * 
     * @param position
     * @return ingredient description
     */
    public String getDescription(int position) {
        Ingredient ingredient = items.get(position);
        return ingredient.getDescription();
    }

    /**
     * This method gets the name of an ingredient.
     * 
     * @param position
     * @return name of ingredient
     */
    public String getName(int position) {
        Ingredient ingredient = items.get(position);
        return ingredient.getName();
    }

    /**
     * This method gets the id of an ingredient.
     * 
     * @param position
     * @return id of ingredient
     */
    public int getId(int position) {
        Ingredient ingredient = items.get(position);
        return ingredient.getId();
    }

    /**
     * This method gets the position.
     * 
     * @param position
     * @return home
     */
    public String getPosition(int position) {
        return "home";
    }

}
