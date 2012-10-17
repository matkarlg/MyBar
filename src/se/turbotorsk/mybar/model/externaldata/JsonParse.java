/*
New BSD License
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se
Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
�	Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
�	Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
�	Neither the name of the MyBar nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package se.turbotorsk.mybar.model.externaldata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
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

import android.view.View;
import android.widget.Toast;

public class JsonParse {

	public JsonParse() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	public boolean getPreInit() {
		//JSONArray jArray = getWebResponsJSON("preinitdb.php");
		return true;
	}

	public boolean getDb() {

		boolean drinkOK = false, ingredientOK = false; 
		//String responseBody = getWebResponsJSON("getDrinks.php"); //Get JSON.
		
		//Get the drinks
		try {
			String responseBody = getWebResponsJSON("getDrinks.php"); //Get JSON.
			Log.d("respone", "responseBody");
			Log.d("respone", responseBody);
			JSONObject json = new JSONObject(responseBody); // Prepare the JSON to be parsed 
			JSONArray jArray = json.getJSONArray("drinks"); 
			Log.d("getDrinks.php",jArray.toString());
			int jArrSize = jArray.length();
			for (int i = 0; i < jArrSize; ++i) {
				JSONObject jObject;
				jObject = jArray.getJSONObject(i);
				ContentValues values = new Drink(jObject.getInt("_id"),
						jObject.getString("name"), 								//Gets the name from JSON
						jObject.getString("url"),								//Gets the name pic url JSON
						jObject.getString("glass"),								//Gets the glass type from JSON
						jObject.getString("ingredient"),						//Gets the list of ingredients from JSON
						jObject.getString("description"),						//Gets the description from JSON
						jObject.getInt("rating"), 								//Gets the rating from JSON
						0).getContentValues();									//Sets favorite to 0 and gets the content values. 
				//Insets the data into the SQLite
				Uri myBarUriDrink = MyBarApplication.ContentResolver().insert(
						MyBarContentProvider.CONTENTURI_DRINK, values);	
				Log.d(Data.class.getClass().getName(),
						"Inserted Drink. Created row: " + myBarUriDrink.toString());
			}
			drinkOK = true; 
		} catch (Exception e) {
			Log.d(Data.class.getClass().getName(), e.toString());	
			e.printStackTrace();
		}
	
		//Get the Ingretients
		/*
		try {
			String responseBody = getWebResponsJSON("getDrinks.php"); //Get JSON.
			JSONObject json = new JSONObject(responseBody); // Prepare the JSON to be parsed 
			JSONArray jArray = json.getJSONArray("drinks"); 
			Log.d("getDrinks.php",jArray.toString());
			for (int i = 0; i < jArrSize; ++i) {
				JSONObject jObject;
				jObject = jArray.getJSONObject(i);
				ContentValues values = new Ingredient(jObject.getInt("_id"), jObject.getString("name"), jObject.getString("url"), jObject.getString("ingredienttype"), jObject.getInt("alcohollevel"),jObject.getString("description")).getContentValues();		//Gets the rating from JSON;
				//Insets the data into the SQLite
				Uri myBarUriIngredient = MyBarApplication.ContentResolver().insert(MyBarContentProvider.CONTENTURI_INGREDIENT, values);
				Log.d(Data.class.getClass().getName(), "Inserted Ingredient. Created row: " + myBarUriIngredient.toString());
			}
			ingredientOK = true; 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		if(drinkOK & ingredientOK) return true;
		else return false; 
	}
	
	/**
	 * This method gets the JSON-formated datastructure form the web server
	 * @param wepDocumet The name of the file to be recived
	 * @return a JSONObject
	 */

	private String getWebResponsJSON(String wepDocumet) {
		wepDocumet = "test3.php";
		try {
			// Log.i(getClass().getSimpleName(), "send  task - start");
			HttpParams httpParams = new BasicHttpParams();
			int TIMEOUT_MILLISEC = 999999;
			HttpConnectionParams.setConnectionTimeout(httpParams,
					TIMEOUT_MILLISEC);
			HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);

			HttpClient httpclient = new DefaultHttpClient(); // Create http
																// client.
			String url = "http://http://dbaccess.mybar.turbotorsk.se/getDrinks.php"; // Sets
																				// the
																				// URL
																				// to
																				// the
																				// html-formated
																				// document
			HttpPost httppost = new HttpPost(url); // Gets the data.

			// Instantiate a GET HTTP method
			try {
				Log.d(getClass().getSimpleName(), "send  task - start");	
				//
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						2);
				nameValuePairs.add(new BasicNameValuePair("user", "1"));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				String responseBody = httpclient.execute(httppost, responseHandler);
				Log.d("Response body:", responseBody);
				return responseBody; 
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				Log.d("Test10!", e.toString());
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d("Test11!", "error");
				e.printStackTrace();
			}
			// Log.i(getClass().getSimpleName(), "send  task - end");

		} catch (Throwable t) {
			Log.d("Test20!", "error! " + t.toString());
			// Toast.makeText(this, "Request failed: " + t.toString(),
			// Toast.LENGTH_LONG).show();
		}

		return null;
	}
}
