package model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Player implements Runnable{

	public Map map;
	
	private int xpos, ypos, i, j, xMapPos, yMapPos;
	private String charName, exploColor, gender, bombColor ;
	public Image currentImg;
	public final int MAXSPEED = 4;
	public ImageIcon moveRight, moveLeft, moveUp, moveDown, steadyR, steadyL, steadyU, steadyD, explosion;
	public boolean isRight = false, isUp = false, isLeft = false, isDown = false, DEAD = false, vulnerable = true;
	public boolean LEFT_OF_BOMB = false, RIGHT_OF_BOMB = false, UP_OF_BOMB = false, DOWN_OF_BOMB = false, collideToBomb = false;  // position of player to the bomb // TEST!!!
	public int playerSpeed = 15, dx = 1, numOfBombs = 1, life = 1;
	
	public Player( int xpos, int ypos, String charName, Map map, String exploColor, String gender , String bombColor ) {
		//System.out.println("Inside Player Class");
		setXpos( xpos );
		setYpos( ypos );
		this.charName = charName;
		this.gender = gender;
		this.exploColor = exploColor;
		this.bombColor = bombColor;
		this.map = map;
		loadImages();
	}
	
	public void loadImages(){
		explosion = new ImageIcon( "Images/Others/explosion" + exploColor + ".png" );
		
		moveRight = new ImageIcon( "Images/Characters/" + gender + "/" + charName + "/moveRight.gif" );
		moveLeft = new ImageIcon( "Images/Characters/" + gender + "/" + charName + "/moveLeft.gif" );
		moveUp = new ImageIcon( "Images/Characters/" + gender + "/" + charName + "/moveUp.gif" );
		moveDown = new ImageIcon( "Images/Characters/" + gender + "/" + charName + "/moveDown.gif" );
		
		steadyR = new ImageIcon( "Images/Characters/" + gender + "/" + charName + "/steadyRight.png" );
		steadyL = new ImageIcon( "Images/Characters/" + gender + "/" + charName + "/steadyLeft.png" );
		steadyU = new ImageIcon( "Images/Characters/" + gender + "/" + charName + "/steadyUp.png" );
		steadyD = new ImageIcon( "Images/Characters/" + gender + "/" + charName + "/steadyDown.png" );
		
		currentImg = steadyD.getImage();
	}
	
	public  boolean hasCollide(){ //damo sayop dd
		//bombCollision();
		//if( bombRect.intersects(getBounds())){
		//	return true;	
		//}
		if( xpos < 255 || xpos > 820 || ypos < 16 || ypos > 497  )
			return true;
		for( int l = 0; l < map.getBarricade().size(); l++ ){
			if( getBounds().intersects( map.getBarricade().get( l ) ) ) {
				return true;
			}
		}
		return false;
	}

	
	public void moveRight(){
		currentImg = moveRight.getImage();
		xpos+=dx;
		if( hasCollide() || collideToBomb  )	//add 	
			xpos-=dx;			
	}
	
	public void moveLeft(){
		currentImg = moveLeft.getImage();
		xpos-=dx;
	
		if( hasCollide()  || collideToBomb ) // || game.bombCollision(this, bomb) )
			xpos+=dx;
	}
	
	public void moveUp(){
		currentImg = moveUp.getImage();
		ypos-=dx;
		
		if( hasCollide() )// || game.bombCollision(this, bomb) )
			ypos+=dx;
	}
	
	public void moveDown(){
		currentImg = moveDown.getImage();
		ypos+=dx;
	
		if( hasCollide() ) //|| game.bombCollision(this, bomb) )
			ypos-=dx;
	}
	
	public void setXpos( int xpos ){
		this.xpos = xpos;
	}
	
	public void setYpos( int ypos ){
		this.ypos = ypos;
	}
	
	public int getXpos(){
		return xpos;
	}
	
	public String getBombColor(){
		return bombColor;
	}
	
	public int getYpos(){
		return ypos;
	}
	
	public void setXMapPos( int xMapPos ){
		this.xMapPos = xMapPos;
	}
	
	public void setYMapPos( int yMapPos ){
		this.yMapPos = yMapPos;
	}
	
	public int getXMapPos(){
		return xMapPos;
	}
	
	public int getYMapPos(){
		return yMapPos;
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
	
	public void setImage( ImageIcon icon ){
		this.currentImg = icon.getImage();
	}
	
	public Image getImage(){
		return currentImg;
	}
	
	public void setCollideToBomb( boolean collideToBomb ){
		this.collideToBomb = collideToBomb;
	}
	public Rectangle getBounds(){
		return new Rectangle( xpos+1, ypos+20 , 25, 30 );//currentImg.getWidth( null )  , currentImg.getHeight(null) - 20 ); 
	}

	public void invulnerable(){
		new Thread( this ).start();
	}
	@Override
	public void run() {
		for( int l = 1; l <= 30; l++ ){
			vulnerable = false;
			currentImg.flush();
			Game.delay( 50 );
		}
		vulnerable = true;
	}	
	
	

}
