package com.shool.herdsheep;

/**
 * A class to hold a location that is passed to the MainThread
 * for basic movement.
 *
 */
public class Packet 
{
	private int bit;
	private float  x;
	private float  y;
	
	/**
	 * Create a new Packet 
	 * @param x
	 * @param y
	 */
	public Packet(int bitConstant, float x, float y)
	{
		this.bit = bitConstant;
		this.x 	 = x;
		this.y   = y;
	}
	
	/**
	 * Returns the bitmap
	 */
	public int getBitmap()
	{
		return this.bit;
	}
	
	/**
	 * Get the float value of the X coordinate
	 * @return
	 */
	public float getX()
	{
		return this.x;
	}
	
	/**
	 * Get the float value of the Y coordinate
	 * @return
	 */
	public float getY()
	{
		return this.y;
	}
}
