package com.schoo.herdsheep;

import android.location.Location;

/**
 * Interface that is to be extended by all
 * pieces that is animated or controlled
 *
 */
public interface Actor
{
	Location respondToInput(); //Makes the actor respond to input
	
	Location move(); 	     	   //Movement by code
}
