package com.schoo.screenj;

import com.schoo.herdsheep.Field;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

/**
* This is the Java that controls the Game Screen.
* This class controls the images on the screen and
* has a Field Object.
*/
public class GameScreen extends Activity
{
	private Field instance; //Instance of the Field (The game) 
	
	/**
	 * Called when the app asks for a game instance
	 * to be created. Gets information about the hardware
	 * to pass to the Field constructor.
	 */
	public void onCreate()
	{
		Display dis = getWindowManager().getDefaultDisplay();
		Point   pon = new Point();
		
		int width  = pon.x; 
		int height = pon.y;
		
		this.instance = new Field(width, height);
		
		
		//Set up how the screen should be displayed
		this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
	}
	
	/**
	 * Called when this Activity loses focus.
	 * (When a different Activity has focus)
	 */
	 public void onPause()
	 {
	 	//Tell the Feild instance to pause
	 	this.instance.pause();
	 }
	 
	 /**
	  * Called when this Activity loss focus and is not
	  * visible to the user.
	  */
	  public void onStop()
	  {
	  	//Tell the Field instance to pause
	  	this.instance.pause();
	  }
	 
	 /**
	  * Called when this Activity is being resumed from a paused
	  * state.
	  */
	  public void onResume()
	  {
	  	//Nothing for this yet
	  }
	  
	  
	  //--------- Event Handles ---------\\
	  
	  /**
	   * Handles TouchEvents that occur outside the bounds
	   * of any View.
	   * (When the user touches the background)
	   */
	   public boolean onTouchEvent(MotionEvent e)
	   {
	   	//TODO: Send Point of event to the Field
	   	this.instance.fireInputEvent(     );
	   }
	   
	   /**
	    * Called when the back button is pressed
	    * This will tell the game to pause.
	    */
	   public void onBackPressed()
	   {
	   	if(this.instance.isPaused())
	   	{
	   		this.instance.resume();
	   	}
	   	else
	   	{
	   		this.instance.pause();
	   	}
	   }
}
