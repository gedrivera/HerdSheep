package com.shool.herdsheep;



import android.graphics.Point;


/**
 * This is the class for the sheep
 */
public class Prey implements Actor
{
	public final int IMAGE = R.drawable.sheep;
	
	private Point  pon   = null;
	
	/**
	 * Constructs a new Prey Object
	 * 
	 * PRE-CONDITION: The Location is within the bounds of the PlayZone.
	 */
	public Prey(Point p)
	{
		this.pon = p;
	}
	
	/**
	 * Gets the current Location for this Prey Object
	 */
	@Override
	public Point getPoint()
	{
		return this.pon;
	}
	
	/**
	 * Implementation of the move method from Actor interface.
	 * Max movement per move() method call: 10px in poth x and y
	 */
	@Override
	public Point move() 
	{
		//TODO: Create a new move() method
		return this.pon;
	}
	
	@Override
	public Point respondToInput(Point input)
	{
		return null;
	}

	/**
	 * Removes this Prey Object from the Field
	 */
	@Override
	public void despawn()
	{
		//TODO: New despwan method
	}

	@Override
	public Packet getPacket() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Packet definePacket() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
