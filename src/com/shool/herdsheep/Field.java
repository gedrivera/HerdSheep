package com.shool.herdsheep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import android.graphics.Point;
import android.view.MotionEvent;

/**
 * The play field.
 */
public class Field 
{
	//Device information
	private int screen_width;
	private int screen_height;
	private int border_line;
	private Game  screen = null;
	
	//Actor caps
	private static final byte SHEEP_CAP = 10;
	private static final byte WOLF_CAP = 3;
	
	//Current values
	private byte currentSheep 		= 0;
	private byte currentWolves      = 0;
	private byte livesLost			= 0;
	private byte score				= 0;
	
	//Others
	
	//Unique Locations, Actors
	private HashMap<Point, Actor> actorMap;
	
	/**
	 * Creates a new field Object
	 */
	public Field(int width, int height, Game screen)
	{
		//Set the device information
		this.screen_width  = width;
		this.screen_height = height;
		this.findBorderLine();		 //Find the border between the zones
		this.screen        = screen;
		
		this.actorMap = new HashMap<Point, Actor>();
	}
	
	/**
	 * Finds the Location of the border_line
	 */
	private void findBorderLine()
	{
		int oneFifth = (int) (Math.floor(this.screen_height / 5));
		
		this.border_line = oneFifth;
	}
	
	private void mainLoop()
	{
		this.iterateActors();
	}
	
	/**
	 * Iterate or loop through the HashMap<Point, Actor>
	 * to call the move method on the sheep.
	 */
	private void iterateActors()
	{
		Iterator<Entry<Point, Actor>> iter = this.actorMap.entrySet().iterator();
		HashMap<Point, Actor> temp = new HashMap<Point, Actor>();
		
		while( iter.hasNext() )
		{
			Actor act = this.actorMap.get(iter);
			Point pon = this.actorMap.get(iter).move();
			
			temp.put(pon, act); //Add to the temporary HashMap
			
			iter.next(); //Move to the next actor
		}
		
		this.actorMap = temp; //Update actorMap
	}
	
	/**
	 * Checks if a Point is valid
	 */
	public boolean isValid(Point pon)
	{
		if( (pon.x < 0) || (pon.x >= this.screen_width) || 
				(pon.y < this.border_line) || (pon.y > this.screen_height) )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * Calls the move() method on affected Actors.
	 */
	public void fireInputEvent(MotionEvent event) 
	{
		Iterator<Entry<Point, Actor>> iter = this.actorMap.entrySet().iterator();
		Point touchPoint = new Point();
		Point iterPoint;
		ArrayList<Actor> effected = new ArrayList<Actor>();
		
		touchPoint.x = (int) event.getX();
		touchPoint.y = (int) event.getY();
		
		//Find points within 20 pixels
		while( iter.hasNext() )
		{
			iterPoint = this.actorMap.get(iter).getPoint();
			
			//Basic - Square zone
			if( (Math.abs(touchPoint.x - iterPoint.x) <= 20) && (Math.abs(touchPoint.y - iterPoint.y) <= 20))
			{
				effected.add(this.actorMap.get(iter));
			}
			
			iter.next(); //Advance Iterator
		}
		
		for(int i = 0; i < effected.size(); i++)
		{
			Point keyToRemove = effected.get(i).getPoint();
			Point movedTo     = effected.get(i).respondToInput(touchPoint);
			
			this.actorMap.remove(keyToRemove);
			this.actorMap.put(movedTo, effected.get(i));
		}
	}
	
	/**
	 * Called when the game is ended either by the lose of all lives or
	 * the Player has quit the current game.
	 */
	private void quitGame()
	{
		//Possible top 5 to 10 high scores?
	}
	
	
	/**
	 * Pauses the game
	 */
	public void pause()
	{
		
	}
	
	/**
	 * Resumes the game
	 */
	public void resume()
	{
		
	}
	
	/**
	 * Removes an Actor at the given Point from the HashMap
	 */
	public void removeActor(Point pon)
	{
		//Check toMake sure that there is such a stored Location
		if( this.actorMap.containsKey(pon) )
		{
			this.actorMap.remove(pon);
		}
	}

}
