package com.shool.herdsheep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

import com.shool.herdsheep.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
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
	public final Bitmap WOLF  = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.wolf));
	
	
	/**
	 * ---------- TEST ----------
	 * This shall also act like the Field.
	 */
	
	//Field variables (No pun intended)
	
	//Device information
	private int screen_width;
	private int screen_height;
	private int border_line;
	
	//Caps
	private final byte SHEEP_CAP = 10;
	private final byte WOLF_CAP = 3;
	
	//Current values
	private byte currentSheep 		= 0;
	private byte currentWolves      = 0;
	private byte livesLost			= 0;
	private byte score				= 0;
		
	//Actor map
	private HashMap<Point, Actor> actorMap;
	
	//Other
	private boolean inProgress = false;
	
	
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
		
		//Create Field information
		this.actorMap = new HashMap<Point, Actor>();
	}
	
	/**
	 * Called when the surface has changed
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int height, int width)
	{
		
	}
	
	/**
	 * Called when the 
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
			if( inProgress )
			{
				//Process the touch event
				
			}
			else
			{
				//Start the game
				this.thread.setRunning(true);
				this.thread.start();
			}
		}
		return super.onTouchEvent(event);
	}
	
	/**
	 * Edits the Canvas like draw() does, except returns the
	 * Canvas after it is edited. 
	 * This is just the start of what this method is going to end up as.
	 */
	protected Canvas editCanvas( Canvas can, float x, float y)
	{
		can.drawColor(Color.BLACK); //Clear the canvas
		can.drawBitmap(this.SHEEP, x, y, null);
		return can; //Return the canvas
	}
	
	/**
	 * Draws to the screen
	 */
	@Override
	protected void onDraw(Canvas canvas)
	{
		//Use editCanvas(Canvas can) instead
	}
	
	/* --------------------------------------------------
	 * 					FIELD METHODS	
	 * --------------------------------------------------
	 */
	
	/**
	 * Returns the actorMap
	 * @return
	 */
	public HashMap<Point, Actor> getActorMap()
	{
		return this.actorMap;
	}
	
	/**
	 * Iterates through the actors calling each of their move()
	 * methods
	 */
	public void iterateActors()
	{
		Iterator<Entry<Point, Actor>> iter = this.actorMap.entrySet().iterator();
		
		ArrayList<Packet> packetArray = new ArrayList<Packet>();
		
		HashMap<Point, Actor> updatedHash = new HashMap<Point, Actor>();
		
		while( iter.hasNext() )
		{
			//Check if the Actor can move (Currently they both move, but prepare for the future)
			if( this.actorMap.get(iter) instanceof Predator )
			{
				Point toPoint = this.actorMap.get(iter).move();
				
				packetArray.add(new Packet(R.drawable.wolf, toPoint.x ,toPoint.y));
				
				updatedHash.put(toPoint, this.actorMap.get(iter));

			}
			else if( this.actorMap.get(iter) instanceof Prey )
			{
				Point toPoint = this.actorMap.get(iter).move();
				
				packetArray.add(new Packet(R.drawable.sheep, toPoint.x, toPoint.y));
				
				updatedHash.put(toPoint, this.actorMap.get(iter));
			}
			else
			{
				//Do nothing because these Actors do not yet exist
				//packetArray.add(new Packet(int, float, float);
				//updatedHash.put(this.actorMap.get(iter).getPoint(), this.actorMap.get(iter));
			}
			
			iter.next(); //Advance Iterator
		}
		
		//Send the Packets to the Thread to be drawn on the screen
		this.thread.receivePackets(packetArray);
	}
	
	/**
	 * Adds a sheep to the game
	 */
	public void addASheep(Random rand)
	{
		//First, can a sheep be added?
		if( this.currentSheep >= this.SHEEP_CAP )
		{
			return; //No, a sheep cannot be added.
		}
		
		//Generates a x value between 0 and screen_height - image_size (Assume 20 for now)
		int x = rand.nextInt(this.screen_width - 20);
		
		//Generates a y value between 0 and screen_height - image_size (Assume 20 for now)
		int y = rand.nextInt(this.screen_height - 20);
		
		//Check y point to see if it falls above the border_line + 5px buffer
		if( y < this.border_line + 5)
		{
			y += (this.border_line + 5); //Add the value of border_line + 5px buffer
		}
		
		//Set up the new Point
		Point insertPoint = new Point();
		insertPoint.x     = x;
		insertPoint.y     = y;
		
		if( this.isValid(insertPoint))
		{
			this.actorMap.put(insertPoint, new Prey(insertPoint));
		}
	}
	
	/**
	 * Checks if the given point is valid in the grid.
	 * To be valid a point must:
	 * 1) Have an x value between 0 and (screen_width - image_size)
	 * 2) Have a  y value between (border_line + 5) and (screen_height - image_size)
	 */
	public boolean isValid(Point pon)
	{
		if( (pon.x > 0) && (pon.x <= (this.screen_width - 20)) )
		{
			if( (pon.y > (this.border_line + 5)) && (pon.y <= (this.screen_height - 20)) )
			{
				//Check if the Location is occupied
				if( this.actorMap.containsKey(pon) )
				{
					return false;
				}
				
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Called by MainThread to check if the game should end
	 */
	public boolean checkLives()
	{
		if( this.livesLost >= 3)
		{
			this.thread.setRunning(false); //Stop the thread
			
			return false; //Don't continue
			
			//TODO: Later on have a menu to start a game. For now, just stop the thread.
		}
		
		return true;
	}

	/**
	 * Return the current number of living Prey.
	 * @return
	 */
	public int getCurrentPrey() 
	{
		return this.currentSheep;
	}
	
	/**
	 * Return the current number of living Predators
	 * @return
	 */
	public int getCurrentPredators()
	{
		return this.currentWolves;
	}

	public int getMaxSheep() 
	{
		return this.SHEEP_CAP;
	}
}
