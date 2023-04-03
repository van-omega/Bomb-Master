package model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bomb implements Runnable{

	private int xpos, ypos;
	private String bombColor ;
	private Image currentImg;
	public ImageIcon bombIcon;
	public boolean explode = false, canBomb = true, dropBomb = false, buto = false, preMature = false;
	public boolean LEFT_OF_BOMB = false, RIGHT_OF_BOMB = false, UP_OF_BOMB = false, DOWN_OF_BOMB = false;
	private int i  , j ;
	public int bombIntensity = 1;
	public Rectangle bombRect = new Rectangle( 0, 0, 40, 40);
	
	public Game game;
	
	public Bomb( int xpos, int ypos, Game game, String bombColor ) {
		setXpos( xpos );
		setYpos( ypos );
		this.game = game;
		this.bombColor = bombColor;
		
		bombIcon = new ImageIcon( "Images/Others/Bomb" + bombColor + ".gif" );
		currentImg = bombIcon.getImage();
	}
	
	public Bomb( int xpos, int ypos, Game game, int bombIntensity, boolean dropBomb, String bombColor ){
		setXpos( xpos );
		setYpos( ypos );
		this.game = game;
		bombIcon = new ImageIcon( "Images/Others/Bomb" + bombColor + ".gif" );
		currentImg = bombIcon.getImage();
		this.bombIntensity = bombIntensity;
		this.dropBomb = dropBomb;
	}
	
	public void setXpos( int xpos ){
		this.xpos = xpos;
	}
	
	public void setYpos( int ypos ){
		this.ypos = ypos;
	}
	
	public int getXpos( ){
		return xpos;
	}
	
	public int getYpos(){
		return ypos;
	}
	
	public void setImage( ImageIcon icon ){
		this.currentImg = icon.getImage();
	}
	
	public Image getImage(){
		return currentImg;
	}
	
	public void setI( int i ){
		this.i = i;
	}
	
	public void setJ( int j ){
		this.j = j;
	}
	
	public int getI(){
		return i;
	}
	
	public int getJ(){
		return j;
	}
	
	public void explode(){
		Thread t = new Thread( this );
	//	System.out.println("Thread "+ t.getName()+" created. CurThread: "+Thread.currentThread().getName());
		t.start();
	}

	@Override
	public void run() {
		for( int i = 0; i < 3; i++ ){
			canBomb = false;
			Game.delay( 1000 );
		}
		currentImg = null;	
		dropBomb = false;
		game.isBombDropped = false;
			
		explode = true;
		Game.delay( 500 );
		game.explodeEffect( getI(), getJ(), game.map.getMapArray(), game.map.getMapArrayCopy(), game.map.getBarricadeArray(), this );	// for power ups this
		buto = true;
		
		//buto = false;
		canBomb = true;
	}

}