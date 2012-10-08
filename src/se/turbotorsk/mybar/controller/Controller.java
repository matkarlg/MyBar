package se.turbotorsk.mybar.controller;

import android.content.ContentResolver;
import se.turbotorsk.mybar.model.Data;
import se.turbotorsk.mybar.model.Drink;

public class Controller {
	
	public final static Controller controller = new Controller();
	private Data data = null;
	
	public Controller()
	{
		data = new Data(null);
	}

	
	public String[] getDrinkNamesAsArray(ContentResolver contentResolver)
	{
		String[] array = null;
		int i = 0;
		for(Drink drink : data.getAllDrinks(contentResolver)){
			array[i++] = drink.getName();	
		}
		return array;
	}
	
	public Drink getIngredientById(int id){
		return null;
	}
}