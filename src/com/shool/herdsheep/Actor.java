package com.shool.herdsheep;

import android.graphics.Point;

/**
 * INterface that is to be extended by all
 * pieces that is animated or controlled
 *
 */
public interface Actor
{
	Point respondToInput(Point input); //Makes the actor respond to input
	
	Point move(); 		   //Movement by code
	
	Point getPoint();      //Get method for Actor Point
	
	void despawn();        //Remove an Actor
}
