package se.turbotorsk.mybar.controller;

import android.content.ContentResolver;
import se.turbotorsk.mybar.model.Data;
import se.turbotorsk.mybar.model.Drink;

public class Controller {
	private ContentResolver contentResolver = null;
	public final static Controller controller = new Controller();
	private Data data = null;
	ContentResolver cr;
	
	public Controller()
	{
		//contentResolver = CoreLib.ContentResolver();
		//data = new Data(contentResolver);	
	}

	
	public String[] getDrinkNamesAsArray()
	{
		String[] array = null;
		int i = 0;
		for(Drink drink : data.getAllDrinks(this.contentResolver)){
			array[i++] = drink.getName();	
		}
		return array;
	}
	
	public Drink getIngredientById(int id){
		return null;
	}
}