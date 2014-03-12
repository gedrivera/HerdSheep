package com.schoo.herdsheep; //Package within my personal ADT 

import android.location.Location;

/**
 * This is the class for the sheep
 */
public class Prey implements Actor
{
	//private ImageView image = null;
	private Location  loc   = null;
	//Don't worry about colors yet
	
	/**
	 * Constructs a new Prey Object
	 * 
	 * PRE-CONDITION: The Location is within the bounds of the PlayZone.
	 */
	public Prey(Location l)
	{
		this.loc = l;
	}
	
	/**
	 * Gets the current Location for this Prey Object
	 */
	public Location getLocation()
	{
	  //TODO: Sheep Movement
		return this.loc;
	}

	/**
	 * Implementation of the move method from Actor interface
	 */
	public Location move() 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	* This method is called when the screen (Field is touched)
	* For Prey Objects they will move away from the point at 
	* which the touch was located.
	*/
	public void respondToInput()
	{
		//TODO: respondToInput
	}
}
