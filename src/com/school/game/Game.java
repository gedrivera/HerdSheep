package com.school.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
* This is the Java that controls the Game Screen.
* This class controls the images on the screen and
* has a Field Object.
*/
public class Game extends Activity
{
	/*Should be the Activity that is called when the application opens*/
	
	//private Field instance; //Instance of the Field (The game) 
	
	//Debugging tag
	private static final String TAG = Game.class.getSimpleName();
	
	/**
	 * Called when this Activity is about to be created
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState); //Call the super method
		
		//Request to turn the Title off
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//Make the Activity full screen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//Set the GameView as the view
		setContentView(new GameView(this));
		Log.d(TAG, "View added");
	}
	
	/**
	 * This method controls what this Activity does when it
	 * is about to be destroyed
	 */
	protected void onDestroy()
	{
		Log.d(TAG, "Destroying.");
		super.onDestroy();
	}
	
	/**
	 * This method controls what this Activity does when it
	 * is about to be stopped.
	 */
	protected void onStop()
	{
		Log.d(TAG, "Stopping.");
		super.onStop();
	}
}
