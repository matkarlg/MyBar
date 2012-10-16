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

/*
 Copyright (c) 2012, MyBar Team All rights reserved.
 mybar@turbotorsk.se
 Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 �	Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 �	Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 �	Neither the name of the <ORGANIZATION> nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package se.turbotorsk.mybar.model;

import android.content.ContentValues;

/**
 * A MyBar object contains information about an ingredient.
 * 
 * @author Mathias Karlgren (matkarlg)
 * 
 */
public class MyBar {

	private int _id = 0;
	private int ingredientID = 0;
	private String location = "";
	private ContentValues values = null;

	/**
	 * This method returns the information about an ingredient.
	 * 
	 * @param _id
	 * @param ingredientID
	 * @param location
	 */
	public MyBar(int _id, int ingredientID, String location) {
		this._id = _id;
		this.ingredientID = ingredientID;
		this.location = location;

		values = new ContentValues();
		values.put("ingredientid", ingredientID);
		values.put("location", location);
	}

	/**
	 * This method gets the values of the content.
	 * 
	 * @return values
	 */
	public ContentValues getContentValues() {
		return values;
	}

	/**
	 * This method gets the id.
	 * 
	 * @return id
	 */
	public int get_id() {
		return _id;
	}

	/**
	 * This method sets the id.
	 * 
	 * @param _id
	 */
	public void set_id(int _id) {
		this._id = _id;
	}

	/**
	 * This method gets the ingredientID.
	 * 
	 * @return ingredientID
	 */
	public int getIngredientID() {
		return ingredientID;
	}

	/**
	 * This method sets the location.
	 * 
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * This method gets the location.
	 * 
	 * @return location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * This method sets the ingredientID.
	 * 
	 * @param ingredientID
	 */
	public void setIngredientid(int ingredientID) {
		this.ingredientID = ingredientID;
	}
}
