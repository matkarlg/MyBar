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
 * A drink object contains information about an ingredient.
 * 
 * @author Mathias Karlgren (matkarlg)
 * 
 */
public class Ingredient {

	private int _id = 0;
	private String name = "";
	private String url = "";
	private String type = "";
	private int alcoholcontent = 0;
	private String description = "";
	private ContentValues values = null;

	/**
	 * This method returns the information about an ingredient.
	 * 
	 * @param _id
	 * @param name
	 * @param url
	 * @param type
	 * @param alcoholcontent
	 * @param description
	 */
	public Ingredient(int _id, String name, String url, String type, int alcoholcontent,
			String description) {
		this._id = _id;
		this.name = name;
		this.url = url;
		this.type = type;
		this.alcoholcontent = alcoholcontent;
		this.description = description;

		values = new ContentValues();
		values.put("name", name);
		values.put("url", url);
		values.put("type", type);
		values.put("alcoholcontent", alcoholcontent);
		values.put("description", description);
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
	 * This method gets the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets the name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method gets the url.
	 * 
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * This method sets the url.
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * This method gets the type.
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * This method sets the type.
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * This method gets the alcoholcontent.
	 * 
	 * @return alcoholcontent
	 */
	public int getAlcoholcontent() {
		return alcoholcontent;
	}

	/**
	 * This method sets the alcoholcontent.
	 * 
	 * @param alcoholcontent
	 */
	public void setAlcoholcontent(int alcoholcontent) {
		this.alcoholcontent = alcoholcontent;
	}

	/**
	 * This method gets the description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method sets the description.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
