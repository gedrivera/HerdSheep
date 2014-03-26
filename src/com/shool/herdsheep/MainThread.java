package com.shool.herdsheep;


import java.util.ArrayList;
import java.util.Random;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread
{
	//Flag to hold game state
	private boolean       	  running;
	private SurfaceHolder 	  surfaceHolder;
	private GameView          gameView;
	private ArrayList<Packet> currentPackets;
	
	private Random rand = new Random();
	
	
	//Debugging Tag
	private static final String TAG = MainThread.class.getSimpleName();
	
	/**
	 * Setter for the running variable
	 */
	public void setRunning(boolean running)
	{
		this.running = running;
	}
	
	/**
	 * Create a new MainThread Object
	 */
	public MainThread(SurfaceHolder surfHold, GameView gameV)
	{
		super();
		
		this.surfaceHolder = surfHold;
		this.gameView      = gameV;
		
		this.surfaceHolder.lockCanvas();
		Log.d(TAG, "Thread Created");
	}
	
	/**
	 * Main part of a Thread/Runnable
	 */
	@Override
	public void run()
	{
		Canvas canvas;
		
		Log.d(TAG, "Starting game loop.");
		
		//Add the first sheep to start the game upon execution of this method
		this.gameView.addASheep(this.rand);
		
		while(running)
		{
			canvas = null;
			//Try to find the canvas
			
			//Update game state
			
			//Check if lives are good
			if( !this.gameView.checkLives() )
			{
				//Lives are not good
				return; //Exit this method
			}
			//Lives are good
			
			//Maybe a new sheep should be added?
			if( (this.rand.nextInt(10) - this.gameView.getCurrentPrey() > 4) && 
					(this.gameView.getCurrentPrey() < this.gameView.getMaxSheep()) )
			{
				
			}
			
			//Render to screen
			try
			{
				canvas = this.surfaceHolder.lockCanvas();
				
	 			synchronized(surfaceHolder)
				{
	 				//Call a small helper method
	 				canvas = this.sendPacketsToView(canvas);
				}
			}
			finally
			{
				if(canvas != null)
				{
					this.surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}
	
	/**
	 * Private helper method to render the screen upon an update to the screen
	 */
	private Canvas sendPacketsToView(Canvas canvas)
	{
		//Draw each packet
		for(int i = 0; i < this.currentPackets.size(); i++)
		{
			canvas = this.gameView.editCanvas(canvas, this.currentPackets.get(i).getX(),
					this.currentPackets.get(i).getY());
		}
		
		return canvas;
	}

	/**
	 * Called when the Actors have been moved and the screen needs to be redrawn.
	 * Takes the packets and 
	 * @param packetArray
	 */
	public void receivePackets(ArrayList<Packet> packetArray) 
	{
		this.currentPackets = packetArray;
	}
}
