package com.shool.herdsheep;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity 
{

	/*Should be the Activity that is called when the application opens*/
	
	//Debugging tag
	private static final String TAG = MainActivity.class.getSimpleName();
	
	//Display constants
	public int screen_width;
	public int screen_height;
	
	/**
	 * Called when this Activity is about to be created
	 * 
	 * 
	 * Suppress these warnings because of the API difference work around.
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState); //Call the super method
		
		
		
		//Request to turn the Title off
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
		
		//Get Screen Dimensions and then pass them to field to create a new Field Object
		Display display = getWindowManager().getDefaultDisplay();

		//First check the Build Version 
		//If API >= 13 use the getSize() method 
		//otherwise use getWidth() and getHeight()
		if(android.os.Build.VERSION.SDK_INT >= 13)
		{
			Point size = new Point();
			
			display.getSize(size);
			
			this.screen_width  = size.x;
			this.screen_height = size.y;
						
		}
		else
		{
			this.screen_width  = display.getWidth();
			this.screen_height = display.getHeight();
		}
		
		Log.d(TAG, "View added");
	}
	
	/**
	 * This method controls what this Activity does when it
	 * is about to be destroyed
	 */
	@Override
	protected void onDestroy()
	{
		Log.d(TAG, "Destroying.");
		super.onDestroy();
	}
	
	/**
	 * This method controls what this Activity does when it
	 * is about to be stopped.
	 */
	@Override
	protected void onStop()
	{
		Log.d(TAG, "Stopping.");
		super.onStop();
	}
}

