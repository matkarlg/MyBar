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

package se.turbotorsk.mybar.test;

import se.turbotorsk.mybar.AboutBox;
import se.turbotorsk.mybar.AddIngredientActivity;
import se.turbotorsk.mybar.MainActivity;
import se.turbotorsk.mybar.SettingsActivity;
import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;

public class MyBarRobotiumTest extends ActivityInstrumentationTestCase2<MainActivity> {
	
	private Solo solo;

	public MyBarRobotiumTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void tearDown() throws Exception {
		try {
			solo.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		getActivity().finish();
		super.tearDown();
		System.exit(0);
	}
	
	
	public void testStartApplication(){
		solo.assertCurrentActivity("Nu blev det fel activity", MainActivity.class);
	}
	
	
	/*Doesnt work at the moment. Keeping this to remember what i have to do.
	public void testChangeTabToCollection(){
		solo.assertCurrentActivity("First activity", MainActivity.class);
		solo.clickOnText("COLLECTION");
		solo.assertCurrentActivity("Ska ha bytt activity", CollectionActivity.class);
	}*/
	
//	public void testOpenAddButton(){
//		solo.clickOnMenuItem("Add");
//		solo.assertCurrentActivity("Ska vara add aktiviteten", AddIngredientActivity.class);
//		solo.goBack();
//	}
//	
//	public void testOpenSettingsButton(){
//		solo.clickOnMenuItem("Settings");
//		solo.assertCurrentActivity("Ska vara settings aktiviteten", SettingsActivity.class);
//		solo.goBack();
//	}

	//Messes up the other tests at the moment
	/*
	public void testOpenShareButton(){
		solo.clickOnMenuItem("Share");
		solo.goBack();
		solo.goBack();
		solo.clickOnText("OK");
	}*/
	
//	public void testOpenAboutBox(){
//		solo.clickOnMenuItem("About");
//		solo.assertCurrentActivity("Ska vara about aktiviteten", AboutBox.class);
//		solo.goBack();
//	}
	
//	public void testCheckMemory(){
//		solo.assertMemoryNotLow();
//	}
	
//	public void testChangeToCollection(){
//		solo.clickOnButton("COLLECTION");
//	}
}
