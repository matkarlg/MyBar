package se.turbotorsk.mybar;

import java.util.LinkedList;

import se.turbotorsk.mybar.model.Drink;
import se.turbotorsk.mybar.model.Ingredient;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class IngredientAdapter extends ArrayAdapter<LinkedList> {

public IngredientAdapter(Context context, int textViewResourceId) {
    super(context, textViewResourceId);
    // TODO Auto-generated constructor stub
}

private LinkedList<Ingredient> items;

public IngredientAdapter(Context context, int resource, LinkedList items) {

    super(context, resource, items);

    this.items = items;

}

@Override
public View getView(int position, View convertView, ViewGroup parent) {

    View v = convertView;

    if (v == null) {

        LayoutInflater vi;
        vi = LayoutInflater.from(getContext());
        v = vi.inflate(R.layout.rowlayout, null);

    }

    Ingredient p = items.get(position);

    if (p != null) {

        TextView tt = (TextView) v.findViewById(R.id.drink);
        ImageView iv = (ImageView) v.findViewById(R.id.list_image);

        
        if (tt != null) {
        	
            tt.setText(p.getName());
        }
    }

    return v;
	}

	public String getIngredientName(int position){
		Ingredient ingredient = items.get(position);
		return ingredient.getName();
	}
	
	public String getDescription(int position){
		Ingredient ingredient = items.get(position);
		return ingredient.getDescription();
	}
	

}