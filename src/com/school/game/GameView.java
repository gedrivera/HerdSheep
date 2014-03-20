package com.school.game;

import com.shool.herdsheep.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * This is the Correct way to do the GameScreen.
 * Instead of extending an Activity this extends a View 
 * allowing for only one Activity to be needed. The
 * Activity that is needed controls the Main menu.
 *
 * MODIFIED CODE FROM: 
 * obviam.net/index.php/a-very-basic-the-game-loop-for-android/
 * 
 * TODO: CURRENTLY NOT MODIFIED BUT IT WORKS AS DEFINED BY TUTORIAL -- 
 * BEGIN SHEEPIFICATION PROCESS
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
	private MainThread thread;
	
	//Debugging tag
	private static final String TAG = GameView.class.getSimpleName();
	
	/**
	 * Creates a new GameView Object
	 */
	public GameView(Context context)
	{
		super(context);
		
		//Add the callback(this) to the SurfaceHolder to handle events
		this.getHolder().addCallback(this);
		
		//Create the game loop thread
		this.thread = new MainThread(getHolder(), this);
		
		
		//Make this focusable
		this.setFocusable(true);
	}
	
	/**
	 * 
	 */
	public void surfaceChanged(SurfaceHolder holder, int format, int height, int width)
	{
		
	}
	
	/**
	 * This method activates the MainThread
	 */
	public void surfaceCreated(SurfaceHolder holder)
	{
		this.thread.setRunning(true);
		this.thread.start();
	}
	
	/**
	 * Make sure the thread shuts down smoothly.
	 */
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		boolean retry = true;
		
		while( retry )
		{
			try
			{
				thread.join();
				retry = false;
			}
			catch(InterruptedException e)
			{
				//try again shutting down the thread
			}
		}
	}
	
	/**
	 * 
	 */
	public boolean onTouchEvent(MotionEvent event)
	{
		if( event.getAction() == MotionEvent.ACTION_DOWN)
		{
			if(event.getY() > this.getHeight() - 50)
			{
				this.thread.setRunning(false);
				
				((Activity)getContext()).finish();
			}
			else
			{
				Log.d(TAG, "Coords: x= " + event.getX() + ", y= " + event.getY());
			}
		}
		return super.onTouchEvent(event);
	}
	
	/**
	 * Draws to the screen
	 */
	protected void onDraw(Canvas canvas)
	{
		canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sheep), 40, 40, null);
	}
}
