package com.school.game;


import android.graphics.Point;


/**
 * This is the class for the sheep
 */
public class Prey implements Actor
{
	//private ImageView image = null;
	private Point  pon   = null;
	private Field  fie   = null;
	
	/**
	 * Constructs a new Prey Object
	 * 
	 * PRE-CONDITION: The Location is within the bounds of the PlayZone.
	 */
	public Prey(Point p, Field f)
	{
		this.pon = p;
		this.fie = f;
		
		//this.image = WhiteSheep image
	}
	
	/**
	 * Gets the current Location for this Prey Object
	 */
	public Point getPoint()
	{
		return this.pon;
	}
	
	/**
	 * Returns the Field in which this Prey Object is located
	 */
	public Field getFeild()
	{
		return this.fie;
	}

	/**
	 * Implementation of the move method from Actor interface.
	 * Max movement per move() method call: 10px in poth x and y
	 */
	public Point move() 
	{
		int xChange = (int) (Math.random() * 10);
		int yChange = (int) (Math.random() * 10);
		Point pon   = new Point();
		
		pon.x = this.pon.x + xChange;
		pon.y = this.pon.y + yChange;
		
		//If the Point is valid, then update the Point
		if( this.fie.isValid(pon) )
		{
			this.pon = pon;
			
			return this.pon;
		}
		else
		{
			return this.pon;
		}
	}
	
	public Point respondToInput(Point input)
	{
		return null;
	}

	/**
	 * Removes this Prey Object from the Field
	 */
	public void despawn()
	{
		this.fie.removeActor(this.pon);
	}
	
	
}
