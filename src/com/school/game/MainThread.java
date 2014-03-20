package com.school.game;


import android.annotation.SuppressLint;
import android.graphics.Canvas;
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
		Canvas canvas;
		
		Log.d(TAG, "Starting game loop");
		
		while(running)
		{
			canvas = null;
			//Try to find the canvas
			try
			{
				canvas = this.surfaceHolder.lockCanvas();
				
				synchronized(surfaceHolder)
				{
					//Update game state
					//Draws canvas on the panel
					this.gameView.onDraw(canvas);
				}
			}
			finally
			{
				//Catch exception
				if( canvas != null)
				{
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}

}
