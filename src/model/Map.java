package model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Map {

	private int[][] mapArray = new int[ 13 ][ 15 ];
	private int[][] mapArrayCopy = new int[ 13 ][15 ];
	private int barricadeArray[][] = new int[ 13 ][ 15 ];
	public int breakableArray[][] = new int[ 13 ][ 15 ];
	public int powerUp[][] = new int[ 13 ][ 15 ];
	private int i, j, initX, initY, temp;
	public final int INDESTRUCTABLE = 0, OPEN_SQUARE = 1, BREAKABLES = 2, BREAKABLES1 = -1, BREAKABLES2 = -2, UNTOUCHABLES = 3 , num_of_breakables = 100, POWERUPS1 = 4, POWERUPS2 = 5, POWERUPS3 = 6, POWERUPS4 = 7, POWERUPS5 = 8, isBOMB = 9;
	public ImageIcon  breakable1Icon, breakable2Icon, barricadeIcon, floor;
	public ImageIcon [] breakableIcon = { new ImageIcon( "Images/Map/breakable1.png" ), new ImageIcon( "Images/Map/breakable3.png" ) };
	public ImageIcon [] powerUps ={	new ImageIcon( "Images/Others/powerUp1.png" ), new ImageIcon( "Images/Others/powerUp2.png" ), new ImageIcon( "Images/Others/powerUp3.png" ), new ImageIcon( "Images/Others/powerUp5.png" ), new ImageIcon( "Images/Others/powerUp4.png" ) };
	public ArrayList <Rectangle> barricade = new ArrayList<Rectangle>();
	
	
	Random random = new Random();
	
	public Map( String breakable1, String breakable2, String barricade, String floor ) {	
		initMap();
		this.breakable1Icon = new ImageIcon( "Images/Map/breakable" + breakable1 + ".png" );
		this.breakable2Icon = new ImageIcon( "Images/Map/breakable" + breakable2 + ".png" );
		this.barricadeIcon = new ImageIcon( "Images/Map/brick" + barricade + ".png" );
		this.floor = new ImageIcon( "Images/Map/tile" + floor + ".png" );	
	}
	
	public void initMap(){
		barricade.add(new Rectangle(0,0,0,0) );
		for( i = 0; i < 13 ; i++ ){
			for( j = 0; j < 15; j++ ){
				if( i % 2 == 0 && j % 2 == 1 ){
					itemPosition( i, j );
					barricade.add( new Rectangle( initX, initY, 40, 40 ) );
					mapArray[ i ][ j ] = INDESTRUCTABLE;
				}
				else
					mapArray[ i ][ j ] = OPEN_SQUARE;
			}
		}
		randomBreakables();
		copyArray();
	}
	
	public void copyArray(){										// a copy of the map for future purposes e.g for powerUps
		for( int i = 0; i < mapArray.length; i++ )					
			for( int j = 0; j < mapArray[ i ].length ; j++ )
				mapArrayCopy[ i ][ j ] = mapArray[ i ][ j ];
	}
	
	public void randomBreakables(){
		for( int l = 0; l < num_of_breakables; l++ ){				//randomed breakables should also contain powerUps inside
			do{														//breakables should not be placed in the players's position and its adjacent sides
				i = random.nextInt( 13 );							//INDESTRUCTABLE's position, and should be placed only in an OPEN_SQUARE
				j = random.nextInt( 15 );
			}while( mapArray[ i ][ j ] != OPEN_SQUARE || ( i == 0 && j == 0 ) || ( i == 1 && j == 0 ) || 
			( i == 1 && j == 1 ) || ( i == 12 && j == 14 ) || ( i == 11 && j == 14 ) || ( i == 11 && j == 13 ) ); 
			
			itemPosition( i, j );
			mapArray[ i ][ j ] = BREAKABLES;
			barricade.add( new Rectangle( initX, initY, 40, 40 ) ); 
			barricadeArray[ i ][ j ] = l + 50;						// determining where the barricades( Rectangles ) are being added to the map
			if( l < 50 ){
				int r = random.nextInt( 5 );
				randomPowerUps( r );								// Random Power Up
			}
			breakableType();
		}
	}
	
	public void breakableType(){
		if( temp == 0 ){
			breakableArray[i][j] = BREAKABLES1;
			temp = 1;
		}
		else if(temp == 1 ) {
			breakableArray[i][j] = BREAKABLES2;
			temp = 0;
		}
	}
	
	public void randomPowerUps( int r ){
		if( r == 0 )
			powerUp[i][j] = POWERUPS1;  							 // increase explosion distance
		else if( r == 1 )
			powerUp[i][j] = POWERUPS2;  							 // increase speed
		else if( r == 2 )
			powerUp[i][j] = POWERUPS3;  							 // reverse direction
		else if( r == 3 ) 
			powerUp[i][j] = POWERUPS4;								 // add life
		else 
			powerUp[i][j] = POWERUPS5;								 //add bomb
	}
	
	public void itemPosition( int i, int j ){  						 // X and Y of an item in the mapArray
		
		initX = 250 + ( 40 * ( ( 15 * i + j ) % 15 ) );
		initY = 25 + ( 40 * ( ( 13 * j + i ) % 13 ) );
	}
	
	public int[][] getMapArray(){
		return mapArray;
	}
	
	public int[ ][ ] getMapArrayCopy(){
		return mapArrayCopy;
	}
	
	public int[][] getBarricadeArray(){
		return barricadeArray;
	}
	
	public ArrayList< Rectangle > getBarricade(){
		return barricade;
	}
	
	public int getInitX(){
		return initX;
	}
	
	public int getInitY(){
		return initY;
	}

}
