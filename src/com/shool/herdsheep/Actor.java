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
	
	/*
	 * Packet is a class that I made that is like a Point that will have methods
	 * that allow for calculations of locations on the screen based on the location
	 * of the packet (top-left corner) and the max-point (bottom-right corner) of
	 * the image.
	 * This is just a concept for now.
	 * Unless it is proven to be useful it is useless.
	 */
	Packet getPacket(); //Returns the packet for the 
	
	Packet definePacket();
}
