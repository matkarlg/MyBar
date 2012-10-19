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

package se.turbotorsk.mybar.model.externaldata;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import se.turbotorsk.mybar.controller.MyBarApplication;
import se.turbotorsk.mybar.model.Data;
import se.turbotorsk.mybar.model.Drink;
import se.turbotorsk.mybar.model.Ingredient;
import se.turbotorsk.mybar.model.database.MyBarContentProvider;
import android.content.ContentValues;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;

/**
 * This class is responsible for getting data from the external MySQL Database. 
 * This includes the HTTP-get and parsing the JSON-formated data.
 * The class also puts the parsed data into the local SQLlite-database.
 * 
 * The class requiers network and internet accees. 
 * @author Dag Fridén (<a
 *         href="mailto:dag@daysoft.se">email</a>)
 */
public class JsonParse {
	//Sets the timeout for the web server. 	
	private static final int TIMEOUT_MILLISEC = 999; 
	
	//Gives the Class access to the network. 
	static {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	public boolean getPreInit() {
		return true;
	}
	
	/**
	 * This method populates the drink and ingredient database with the parsed JSON-
	 * data. 
	 * @return boolena: true if all no errors was encountered. 
	 */
	public static boolean getDb() {
		boolean drinkOK = false, ingredientOK = false;
		String responseBody = getWebData("getIngredients.php");
		//If the getWebData returns error the HTTP-get has failed and 
		//the paring shall not be done. 
		if(responseBody.equals("error")) return false; 
		Log.d("getDB",responseBody);
		try{
			// Prepare the JSON to be parsed.
			JSONObject json = new JSONObject(responseBody);  
			JSONArray jArray = json.getJSONArray("ingredients"); 
			Log.d("getDrinks.php",jArray.toString());
			for (int i = 0; i < jArray.length(); ++i) {
				JSONObject jObject;
				jObject = jArray.getJSONObject(i);
				//Parses the JSON and creates the ContentValues object.
				ContentValues values = new Ingredient(jObject.getInt("_id"), 
						jObject.getString("name"), 					
						jObject.getString("url"), 			
						jObject.getString("ingredienttype"),
						jObject.getInt("alcohollevel"),		
						jObject.getString("description")		
						).getContentValues();						
				//Insets the data into the SQLite.
				Uri myBarUriIngredient = MyBarApplication.contentResolver().insert(MyBarContentProvider.CONTENTURI_INGREDIENT, values);
				Log.d(Data.class.getClass().getName(), "Inserted Ingredient. Created row: " + myBarUriIngredient.toString());
			}
			ingredientOK = true; 
			
        } catch (JSONException e) {
        	Log.e("JSONError ingredients", e.toString());
        	ingredientOK = false; 
        }
		
		
		responseBody = getWebData("getDrinks.php");
		Log.d("getDB",responseBody);
		try {
			// Prepare the JSON to be parsed.
			JSONObject json = new JSONObject(responseBody); 	
			//Creates the JSON array from the drinks array.
			JSONArray jArray = json.getJSONArray("drinks"); 	
			//Print the json to the Log.d.
			Log.d("getDrinks.php",jArray.toString());
			for (int i = 0; i < jArray.length(); ++i) {
				JSONObject jObject;								
				jObject = jArray.getJSONObject(i);
				//
				ContentValues values = new Drink(jObject.getInt("_id"),
					jObject.getString("name"), 	
					jObject.getString("url"),		
					jObject.getString("glass"),				
					jObject.getString("ingredient"),	
					jObject.getString("description"),	
					jObject.getInt("rating"), 	
					//Sets favorite to 0 and gets the content values. 
					0).getContentValues();									
			//Insets the data into the SQLite
			Uri myBarUriDrink = MyBarApplication.contentResolver().insert(
					MyBarContentProvider.CONTENTURI_DRINK, values);	
			Log.d(Data.class.getClass().getName(),
					"Inserted Drink. Created row: " + myBarUriDrink.toString());
		}
			//Sets the return variable.
			drinkOK = true; 	
        } catch (JSONException e) {
        	//Prints to Log.d. 
        	Log.e("JSONError drinks", e.toString());	
        	drinkOK = false; 
        }
		
		return ingredientOK & drinkOK;
	}
	
	/**
	 * This method is gets a given web document from http://dbaccess.mybar.turbotorsk.se/ + file.
	 * @param wepDocumet the file to be fetched.
	 * @return A string representation of the responseBody.
	 */
	public static String getWebData(String wepDocumet)
	{
		
	    try {
	        Log.d(JsonParse.class.getSimpleName(), "Start the web-get documnet");
	        HttpParams httpParams = new BasicHttpParams();
	        HttpConnectionParams.setConnectionTimeout(httpParams,TIMEOUT_MILLISEC);
	        HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
	        HttpParams p = new BasicHttpParams();
	        p.setParameter("user", "1");

	        // Instantiate an HttpClient.
	        HttpClient httpclient = new DefaultHttpClient(p);
	        String url = "http://dbaccess.mybar.turbotorsk.se/" + wepDocumet;
	        HttpPost httppost = new HttpPost(url);
	        // Instantiate a GET HTTP method.
    		try{
    			ResponseHandler<String> responseHandler = new BasicResponseHandler();
    			return httpclient.execute(httppost,responseHandler);
    		
    		} catch (ClientProtocolException e) {    			
    			Log.e("Error", e.toString());
    			return "error";
    		}
	    } catch (Exception e) {
			Log.e("Error", e.toString());
			return "error";
    	}	
	}
}
