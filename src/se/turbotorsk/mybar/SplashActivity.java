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

import se.turbotorsk.mybar.controller.Controller;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.Toast;

/**
 * This activity handles the splash-logo that starts with the application.
 */
public class SplashActivity extends Activity {
    // Set the length for the splash shown after sync.
    private static final int SPLASH_LENGTH = 1200;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        // Set the content view to the respective XML file.
        setContentView(R.layout.splash);
        // Create a new handler for the new runnable.
        new Handler().postDelayed(new Runnable() {

            public void run() {
                // Delete the Drink and Ingredient tables in SQLite.
                Controller.deleteTables();
                // If the sync is unsuccessful...
                if (!(Controller.dataSync())) {
                    // Display a Toast that the sync failed.
                    Toast.makeText(SplashActivity.this, "Sync Failed: Connection error!", Toast.LENGTH_LONG).show();
                }
                // Create a new intent to send into the MainActivity.
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                // Start the MainActivity.
                SplashActivity.this.startActivity(intent);
                // Finish this activity.
                SplashActivity.this.finish();
            }
        }, SPLASH_LENGTH);
    }
}
