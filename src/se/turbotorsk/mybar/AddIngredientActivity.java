/*
New BSD License
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se

Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the following conditions are met:
*	Redistributions of source code must retain the above copyright notice,
 	this list of conditions and the following disclaimer.
*	Redistributions in binary form must reproduce the above copyright notice,
 	this list of conditions and the following disclaimer in the documentation
 	and/or other materials provided with the distribution.
*	Neither the name of the MyBar nor the names of its contributors may be 
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

import se.turbotorsk.mybar.controller.Controller;
import se.turbotorsk.mybar.model.Ingredient;
import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * This activity handles the functions that add ingredients.
 */
public class AddIngredientActivity extends ListActivity {
	
	private TextView text;
	private IngredientAdapter adapter;
	public static int save = -1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setText((TextView) findViewById(R.id.drink));
		adapter = new IngredientAdapter(this, R.layout.rowlayout,
				Controller.getAllIngredients());

		setListAdapter(adapter);

	}

	@Override
	public void onListItemClick(ListView parent, View v, int position, long id) { 

		{
			if(save != position && parent.getChildAt(position) != null)
			{
		    parent.getAdapter().getView(position, v, null).setBackgroundColor(Color.parseColor("#0489B1"));
 		    Controller.addMyBarIngredient(adapter.getId(position));
		    save = position;
			}
			
			else if(parent.getChildAt(position) != null)
			{
			parent.getAdapter().getView(position, v, null).setBackgroundColor(Color.WHITE);
//		    Controller.removeMyBarIngredient();			
			save = -1;
			}	
		}
	}

	//Needed according to sonar, feels a bit wierd. Might remove?
	public TextView getText() {
		return text;
	}

	public void setText(TextView text) {
		this.text = text;
	}
}


