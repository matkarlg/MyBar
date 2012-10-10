package se.turbotorsk.mybar.controller;

import java.util.LinkedList;

import android.content.ContentResolver;
import se.turbotorsk.mybar.model.Data;
import se.turbotorsk.mybar.model.Drink;

public class Controller {
	//public final static Controller controller = new Controller();
	//private CoreLib cl = new CoreLib();
	private static Data data = null;
	private static int myBarID = 1;
	private static DrinkManager dm = null;
	//		ContentResolver contentResolver = CoreLib.ContentResolver();
	public Controller()
	{
		//contentResolver = CoreLib.ContentResolver();
		dm = new DrinkManager();
		
		//data = new Data(contentResolver);	
	}

	public static LinkedList<Drink> getMyBar()
	{
		//dm.getMyBar(data.getMyIngredients(this.myBarID), data.getAllDrinks(this.contentResolver));			Implement this in data....
		return null;
	}
	
	public static String[] getDrinkNamesAsArray()
	{
		String[] array = null;
		int i = 0;
		for(Drink drink : data.getAllDrinks()){
			array[i++] = drink.getName();	
		}
		return array;
	}
	
	
	public static Drink getIngredientById(int id){
		return null;
	}
	//-----------------------------------------------------Methods for getting and setting favorite drinks ----------------------------------
	
	
	public static boolean addFavoriteDrink(int id){
		return false;
	}
	
	public static LinkedList<Drink> getFavoritDrinks()
	{
		return null;
	}
	
	// -----------------------------------------------------Methods for Ingredient categories ------------------------------------
	
	public static boolean addMyBarCat(String Name)
	{
		return false; //data.AddMyBarCat(String name);							Add this method in data. 
	}
	
	public static boolean addIngredientToList(int ingredientID)
	{
		// data.AddIngredientToList(ingredientID, this.myBarID); 				Add this method in data. 
		return false; 
	}
	
	public static boolean changeMyBarID(int myBarID)
	{
		if(true){ //Kolla om ID finns
			Controller.myBarID = myBarID;
			return true;
		}	
		else return false; 
	}
	
	public static String[][] listMyBarIngredientCategories()
	{
		//data.listMyBarIngredientCategories(this.myBarID);						Add this method in data.
		return null;
	}
}