package com.schoo.herdsheep;

import java.util.HashMap;
import java.util.Iterator;

import android.location.Location;

/**
 * The play field. ONLY the constructor should be public.
 * Every other method should be private so it can only be
 * called by this class.
 */
public class Field 
{
	//Device information
	private int screen_width;
	private int screen_height;
	private int border_line;	
	
	//Actor caps
	private static final byte SHEEP_CAP = 10;
	private static final byte WOLF_CAP = 3;
	
	//Current values
	private int currentSheep 		= 0;
	private int currentWolves   = 0;
	private int livesLost		  	= 0;
	private int score			    	= 0;
	
	//Others
	
	//Unique Locations, Actors
	private HashMap<Location, Actor> actorMap;
	
	/**
	 * Creates a new field Object
	 */
	public Field(int width, int height)
	{
		//Set the device information
		this.screen_width  = width;
		this.screen_height = height;
		this.findBorderLine();		 //Find the border between the zones
		
		this.actorMap = new HashMap<Location, Actor>();
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
		Runnable runner = new Runnable();
	}
	
	/**
	 * Iterate or loop through the HashMap<Location, Actor>
	 * to call the move method on the sheep.
	 */
	private void iterateActors()
	{
		//TODO: Iterator or some looping device
	}

}
