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

package se.turbotorsk.mybar.model;

import android.content.ContentValues;

/**
 * Ingredient is a simple class that contains variables describing, among other
 * attributes the name and the type of description about ingredients used in
 * drinks.
 * 
 * @author Mathias Karlgren (<a
 *         href="mailto:mathias.karlgren@gmail.com">email</a>)
 */
public class Ingredient {
    private int id = 0;
    private String name = "";
    private String url = "";
    private String type = "";
    private int alcoholcontent = 0;
    private String description = "";
    private ContentValues values = null;

    public Ingredient(int id, String name, String url, String type, int alcoholcontent,
            String description) {
        this.id = id;
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
     * Gets the ContentValues.
     * 
     * @return values
     */
    public ContentValues getContentValues() {
        return values;
    }

    /**
     * Gets the id.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the URL.
     * 
     * @return URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL.
     * 
     * @param URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the type.
     * 
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the alcoholcontent.
     * 
     * @return alcoholcontent
     */
    public int getAlcoholcontent() {
        return alcoholcontent;
    }

    /**
     * Sets the alcoholcontent.
     * 
     * @param alcoholcontent
     */
    public void setAlcoholcontent(int alcoholcontent) {
        this.alcoholcontent = alcoholcontent;
    }

    /**
     * Gets the description.
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
