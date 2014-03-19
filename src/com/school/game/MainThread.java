package com.school.game;


import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread
{
	//Flag to hold game state
	private boolean       running;
	private SurfaceHolder surfaceHolder;
	private GameView      gameView;
	
	//Debugging Tag
	private static final String TAG = MainThread.class.getSimpleName();
	
	/**
	 * Create a new MainThread Object
	 */
	public MainThread(SurfaceHolder surfHold, GameView gameV)
	{
		super();
		
		this.surfaceHolder = surfHold;
		this.gameView      = gameV;
	}
	/**
	 * Setter for the running variable
	 */
	public void setRunning(boolean running)
	{
		this.running = running;
	}
	
	/**
	 * Main part of a Thread/Runnable
	 */
	public void run()
	{
		long tickCount = 0L;
		
		Log.d(TAG, "Starting game loop");
		
		while(running)
		{
			tickCount++;
			
			//Update game state
			//Render state to the screen
		}
		
		Log.d(TAG, "Game loop executed " + tickCount + " times");
	}

}
