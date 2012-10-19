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

package se.turbotorsk.mybar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * This activity handles the AboutBox that is a part of the Options-menu. It
 * shows the current version number and also the name of the developing group
 * who made the application.
 */
public class AboutBox {
	private static String versionName(Context context) {
		return "0.5";
	}

	public static void show(Activity callingActivity) {
		// Use a Spannable to allow for links highlighting.
		SpannableString aboutText = new SpannableString("Version "
				+ versionName(callingActivity) + "\n\n"
				+ callingActivity.getString(R.string.about));
		// Generate views to pass to AlertDialog.Builder and to set the text.
		View about;
		TextView tvAbout;
		try {
			// Inflate the custom view.
			LayoutInflater inflater = callingActivity.getLayoutInflater();
			about = inflater.inflate(R.layout.aboutbox,
					(ViewGroup) callingActivity.findViewById(R.id.aboutView));
			tvAbout = (TextView) about.findViewById(R.id.aboutText);
		} catch (InflateException e) {
			// Inflater can throw exception, unlikely but default to TextView if
			// it occurs.
			tvAbout = new TextView(callingActivity);
			about = tvAbout;
		}

		// Set the about text.
		tvAbout.setText(aboutText);
		// Now Linkify the text.
		Linkify.addLinks(tvAbout, Linkify.ALL);
		// Build and show the dialog.
		new AlertDialog.Builder(callingActivity)
				.setTitle(
						"About " + callingActivity.getString(R.string.app_name))
				.setCancelable(true).setIcon(R.drawable.ic_drinkicon)
				.setPositiveButton("OK", null).setView(about).show();
		// Builder method returns allow for method chaining.
	}
}