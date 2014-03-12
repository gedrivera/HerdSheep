package com.schoo.herdsheep;

import android.location.Location;

/**
 * Interface that is to be extended by all
 * pieces that are animated or controlled
 */
public interface Actor
{
	Location respondToInput(); //Makes the actor respond to input
	
	Location move(); 		   //Movement by code
	
	void despawn();		       //Remove the Actor
}
