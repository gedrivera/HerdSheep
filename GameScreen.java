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
	}
}
