package se.turbotorsk.mybar;

import java.util.LinkedList;
import java.util.List;

import se.turbotorsk.mybar.model.Drink;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkAdapter extends ArrayAdapter<LinkedList> {

public DrinkAdapter(Context context, int textViewResourceId) {
    super(context, textViewResourceId);
    // TODO Auto-generated constructor stub
}

private LinkedList<Drink> items;

public DrinkAdapter(Context context, int resource, LinkedList items) {

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

    Drink p = items.get(position);

    if (p != null) {

        TextView tt = (TextView) v.findViewById(R.id.drink);
        TextView tt1 = (TextView) v.findViewById(R.id.ingredients);
        TextView tt3 = (TextView) v.findViewById(R.id.rating);
        ImageView iv = (ImageView) v.findViewById(R.id.list_image);

        
        if (tt != null) {
        	
            tt.setText(p.getName());
        }
        if (tt1 != null) {

            tt1.setText(p.getIngredient());
        }
        if (tt3 != null) {

            tt3.setText(Integer.toString(p.getRating()));
        }
    }

    return v;

}


}
