package com.shool.herdsheep;

import com.shool.herdsheep.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
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
	
	//Bitmap constants
	public final Bitmap SHEEP = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sheep));
	
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
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int height, int width)
	{
		
	}
	
	/**
	 * This method activates the MainThread
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		Log.d(TAG, "Surface Created");
		
		this.thread.setRunning(true);
		this.thread.start();
	}
	
	/**
	 * Make sure the thread shuts down smoothly.
	 */
	@Override
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
	 * Handles TouchEvents on the GameView.
	 */
	@Override
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
				//Log coordinates
				Log.d(TAG, "Coords: x= " + event.getX() + ", y= " + event.getY());
								
			}
		}
		return super.onTouchEvent(event);
	}
	
	/**
	 * Draws to the screen
	 */
	@Override
	protected void onDraw(Canvas canvas)
	{
	}
}
