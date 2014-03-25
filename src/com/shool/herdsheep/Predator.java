package com.school.game;

import java.util.ArrayList;

import android.graphics.Point;
import android.location.Location;

public class Predator implements Actor
{
	private Point pon = null;
	private Field fie = null;
	
	/**
	 * Creates a new Predator Object
	 * PRE-CONDITION: Location is within the bounds of the PlayZone
	 */
	public Predator(Point p, Field f)
	{
		this.pon = p;
		this.fie = f;
	}
	
	public Point getPoint()
	{
		return this.pon;
	}

	/**
	 * Called by the Game to move the Predator.
	 * Max change per call: 10px in x and y
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
	
	/**
	 * Finds any nearby sheep and possibly eats one of them.
	 * Called by the Field after this Predator Object has
	 * been moved.
	 */
	private void eatASheep(ArrayList<Prey> eats)
	{
		for(int i = 0; i < eats.size(); i++)
		{
			if( (eats.get(i).getPoint().x <= 10) && (eats.get(i).getPoint().y <= 10) )
			{
				int eatIndex = (int) (Math.random() * eats.size() + 1);
				
				if( eatIndex > eats.size() )
				{
					return; //Predator will not eat a sheep
				}
				else
				{
					eats.get(i).despawn();
				}
			}
		}
	}

	/**
	 * Predators do nothing when tapped.
	 */
	public Point respondToInput(Point input)
	{
		return this.pon;
	}
	
	/**
	 * Despawns this Predator
	 */
	public void despawn()
	{
		this.fie.removeActor(this.pon);
	}
	
	


}
