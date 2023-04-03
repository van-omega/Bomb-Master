package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;


public class Game implements ActionListener {

	public Timer gameTimer;
	public Player p1, p2;
	public Bomb bomb1, bomb2;
	public Map map;
	public Thread thread, thread2;
	public ArrayList< Bomb > bombs, bombsCopy, bombs2, bombs2Copy;
	
	public boolean isBombDropped = false;
	
	public Game( Player p1, Player p2, Map map ) {
		this.p1 = p1;
		this.p2 = p2;
		this.map = map;
		initComponents();
	}
	
	public void initComponents(){
		//map = new Map();
		bomb1 = new Bomb( 1001, 601, this, p1.getBombColor() );
		bomb2 = new Bomb( 1001, 601, this, p2.getBombColor() );
		
		bombs = new ArrayList<Bomb>();
		bombs2 = new ArrayList<Bomb>();
		bombsCopy = new ArrayList<Bomb>();
		bombs2Copy = new ArrayList<Bomb>();
		
		gameTimer = new Timer( 10, this );
		gameTimer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if( p1.DEAD && p2.DEAD ){
			
		}
	}
	
	public void playerAction( Player p , Bomb bomb ){						/* Knowing the position of the Player in the mapArray[][]; */ 
		int a, b = 0, i = 0, j = 0;
		for( a = 250 , j = 0; a <= 855; a+= 40, j++ ){
			for( b = 25, i = 0; b <= 540; b+= 40, i++  )
				if( p.getXpos() + p.currentImg.getWidth( null ) / 2 >= a && p.getXpos() + p.currentImg.getWidth( null ) / 2 <= a+40 && p.getYpos() + p.currentImg.getHeight( null ) / 2 +10 >= b && p.getYpos() + p.currentImg.getHeight(null)/2 + 10 <= b + 40 )
					break;
			if( p.getXpos() + p.currentImg.getWidth( null ) / 2 >= a && p.getXpos() + p.currentImg.getWidth( null ) / 2 <= a + 40 && p.getYpos() + p.currentImg.getHeight( null ) / 2 + 10 >= b && p.getYpos() + p.currentImg.getHeight( null ) / 2 + 10 <= b + 40 )
				break;
		}
		p.setI( i );
		p.setJ( j );
		p.setXMapPos( a  );
		p.setYMapPos( b );
		if( map.powerUp[ p.getI() ][ p.getJ() ] == map.POWERUPS1 ){			//lengthens explosion
				map.powerUp[p.getI() ][ p.getJ()] = map.OPEN_SQUARE;
				bomb.bombIntensity++;				
		}
		else if( map.powerUp[ p.getI() ][ p.getJ() ] == map.POWERUPS2 ){	//add Speed
			map.powerUp[ p.getI() ][ p.getJ() ] = map.OPEN_SQUARE;
			if( p.playerSpeed > p.MAXSPEED )
				p.playerSpeed--;						
		}
		else if( map.powerUp[ p.getI() ][ p.getJ() ] == map.POWERUPS3 ){	//reverse direction
			map.powerUp[ p.getI() ][ p.getJ() ] = map.OPEN_SQUARE;		
			p.dx *= -1;
		}
		else if( map.powerUp[ p.getI() ][ p.getJ() ] == map.POWERUPS4  ){	//add bomb
			map.powerUp[ p.getI() ][ p.getJ() ] = map.OPEN_SQUARE;
			p.numOfBombs++;
		}
		else if( map.powerUp[ p.getI() ][ p.getJ() ] == map.POWERUPS5 ){	//add life
			map.powerUp[ p.getI() ][ p.getJ() ] = map.OPEN_SQUARE;
			p.life++;
		}
	}
	
	public void bombOrientation( Player p, Bomb bomb ){

		if( bomb.dropBomb ){
			bomb.bombRect.setBounds( bomb.getXpos()-3, bomb.getYpos() , 40, 40 ); 
			if( p.getBounds().x + p.currentImg.getWidth( null ) < bomb.getXpos()  )				// p1 went left of the bomb
				bomb.LEFT_OF_BOMB = true;
			
			else if( p.getBounds().x > bomb.getXpos() + 40 - 3 )								// p1 went Right of the bomb
				bomb.RIGHT_OF_BOMB = true;
				
			else if( p.getBounds().y + 28 < bomb.getYpos() )									// p1 went above the bomb
				bomb.UP_OF_BOMB = true;
				
			else if( p.getBounds().y > bomb.getYpos() + 40 )									// p1 went under the bomb
				bomb.DOWN_OF_BOMB = true;
		}
	} 
	
	public void bombCollision( Player p, Bomb bomb ){ // for( bombs)
		if( bomb.LEFT_OF_BOMB )
			if( p.getBounds().intersects( bomb.bombRect ) )
				p.setXpos( p.getXpos() - 2 );
		if( bomb.RIGHT_OF_BOMB )
			if( p.getBounds().intersects( bomb.bombRect ) )
				p.setXpos(p.getXpos() + 2 );
		if( bomb.UP_OF_BOMB )
			if( p.getBounds().intersects( bomb.bombRect ) )
				p.setYpos(p.getYpos() - 2 );
		if( bomb.DOWN_OF_BOMB )
			if( p.getBounds().intersects( bomb.bombRect ) )
				p.setYpos( p.getYpos() + 2 ); 
	}
	
	public void checkPlayerHit( Player p , int i, int j ){ 
		if( p.getI() == i  && p.getJ() == j  && p.vulnerable ){
			if( p.life > 0 ){
				p.life--;
				if(p.life == 0 )
					p.DEAD = true;
				p.invulnerable();
			}
		}
	}
	
	public void explodeEffect( int i, int j, int [][] mapArray , int[][] mapArrayCopy, int[][] barricadeArray , Bomb bomb ){ 	// mapArrayCopy may not have been used
		//System.out.println(i + " : " + j);
		checkPlayerHit( p1 , i, j  );
		checkPlayerHit( p2 , i, j  );
		if( j + 1 <= 14 &&	mapArray[ i ][ j + 1 ] != map.INDESTRUCTABLE  ){													// effect of the right intensity of the explosion
			for( int l = 1; l <= bomb.bombIntensity ; l++ ){		// for powerUps
				if( j + l <= 14 && ( mapArray[ i ][ j + l ]  == map.BREAKABLES || mapArray[i][j+l] == map.isBOMB ) ){			// if intensity hits any, it is limited to that point
					//System.out.println("Sulod1" + "\t"+Thread.currentThread().getName());
					mapArray[ i ][ j + l ] = map.OPEN_SQUARE;
					map.barricade.remove( barricadeArray [ i ][ j + l ] );
					map.barricade.add( barricadeArray[ i ][ j + l ], new Rectangle( 0, 0, 0, 0 )  );
					break;
				}
				else if( j + l <= 14 && ( map.powerUp[ i ][ j + l ]  == map.POWERUPS1 || map.powerUp[i][ j + l ] == map.POWERUPS2 || map.powerUp[i][ j + l ] == map.POWERUPS3 || map.powerUp[i][j+l] == map.POWERUPS4 ) ){
					System.out.println("Sulod dinhi" + "\t"+Thread.currentThread().getName());
					map.powerUp[i][j+l] = map.OPEN_SQUARE;
				}
				checkPlayerHit( p1 , i, j +  l );
				checkPlayerHit( p2 , i, j +  l );
			}
		}
		if( j-1 >= 0 &&  mapArray[ i ][ j - 1 ] != map.INDESTRUCTABLE ){												// effect of the left intensity of the explosion
			for( int l = 1; l <= bomb.bombIntensity; l++ ){
				if( j - l >= 0 && ( mapArray[ i ][ j - l ]  == map.BREAKABLES || mapArray[i][j-l] == map.isBOMB) ){
					mapArray[ i ][ j - l ] = map.OPEN_SQUARE;
					map.barricade.remove( barricadeArray [ i ][ j - l ] );
					map.barricade.add( barricadeArray[ i ][ j - l ], new Rectangle( 0, 0, 0, 0 )  );
					break;
				}
				if( j - l >= 0 && ( map.powerUp[ i ][ j - l ]  == map.POWERUPS1 || map.powerUp[i][ j - l ] == map.POWERUPS2 || map.powerUp[i][ j -l ] == map.POWERUPS3 || map.powerUp[i][j-l] == map.POWERUPS4 ) )
					map.powerUp[i][j-l] = map.OPEN_SQUARE;
				checkPlayerHit( p1 , i, j -  l );
				checkPlayerHit( p2 , i, j -  l );
			}
		}
		if( i+1 <= 12 &&  mapArray[ i + 1  ][ j ] != map.INDESTRUCTABLE  ){												// effect of the down intensity of the explosion
			for( int l = 1; l<= bomb.bombIntensity; l++ ){
				if( i + l <= 12 && ( mapArray[ i + l ][ j ]  == map.BREAKABLES || mapArray[i+l][j] == map.isBOMB ) ){
					mapArray[ i + l ][ j ] = map.OPEN_SQUARE;
					map.barricade.remove( barricadeArray[ i + l ][ j ] );
					map.barricade.add( barricadeArray[ i + l ][ j ], new Rectangle( 0, 0, 0, 0 )  );
					break;
				}
				if( i + l <= 12 && ( map.powerUp[ i + l][ j ] == map.POWERUPS1 || map.powerUp[i+l][ j ] == map.POWERUPS2 || map.powerUp[i+l][ j ] == map.POWERUPS3 || map.powerUp[i+l][j] == map.POWERUPS4 ) )
					map.powerUp[i+l][j] = map.OPEN_SQUARE;
				checkPlayerHit( p1 , i + l, j );
				checkPlayerHit( p2 , i + l, j );
			}
		}
		if( i-1 >= 0 &&  mapArray[ i - 1 ][ j ] != map.INDESTRUCTABLE  ){												// effect of the up intensity of the explosion
			for( int l = 1; l <= bomb.bombIntensity; l++ ){
				if( i - l >= 0 && ( mapArray[ i - l ][ j ]  == map.BREAKABLES || mapArray[i-l][j] == map.isBOMB ) ){
					mapArray[ i - l ][ j ] = map.OPEN_SQUARE;
					map.barricade.remove( barricadeArray[ i - l ][ j ] );
					map.barricade.add( barricadeArray[ i - l ][ j ], new Rectangle( 0, 0, 0, 0 )  );
					break;
				}
				if( i - l >= 0 && ( map.powerUp[ i - l][ j ]  == map.POWERUPS1 || map.powerUp[i-l][ j ] == map.POWERUPS2 || map.powerUp[i-l][ j ] == map.POWERUPS3 || map.powerUp[i-l][j] == map.POWERUPS4 ) )
					map.powerUp[i-l][j] = map.OPEN_SQUARE;
				checkPlayerHit( p1 , i - l, j );
				checkPlayerHit( p2 , i - l, j );
			}
		}
		mapArray[i][j] = map.OPEN_SQUARE;	
//		/bomb.buto = true;
		
		
	}
	
	public void drawExplosion( Graphics g, int i, int j, int [][] mapArray, Bomb bomb, Player p ){
		//intensity 3 bomb; for the PowerUps
		g.drawImage( p.explosion.getImage(), bomb.getXpos() - 3, bomb.getYpos(), null );				// epicenter
		if( j + 1 <= 14 && mapArray[ i ][ j + 1 ] != map.INDESTRUCTABLE  ){
			for( int l = 1; l <= bomb.bombIntensity; l++ ){
				if( j + l <= 14 && (mapArray[ i ][ j + l ] == map.BREAKABLES || mapArray[ i ][ j + l ] == map.isBOMB) ){
					g.drawImage( p.explosion.getImage(), bomb.getXpos() - 3 + 40 * l, bomb.getYpos(), null );	// right intensity
					break;
				}
				if( j + l <= 14 && mapArray[ i ][ j + l ] != map.BREAKABLES )
					g.drawImage( p.explosion.getImage(), bomb.getXpos() - 3 + 40 * l, bomb.getYpos(), null );	// right intensity
			}
		}
		if( j - 1 >= 0 &&  mapArray[ i ][ j - 1 ] != map.INDESTRUCTABLE  ){
			for( int l = 1; l <= bomb.bombIntensity; l++ ){
				if( j - l >= 0 && ( mapArray[ i ][ j - l ] == map.BREAKABLES || mapArray[i][j -l] == map.isBOMB) ){
					g.drawImage( p.explosion.getImage(), bomb.getXpos() - 3 - 40 * l, bomb.getYpos(), null );	// left intensity
					break;
				}
				if( j - l >= 0 && mapArray[ i ][ j - l ] != map.BREAKABLES )
					g.drawImage( p.explosion.getImage(), bomb.getXpos() - 3 - 40 * l, bomb.getYpos(), null );	// left intensity
				
			}
		}
		if( i + 1 <= 12 && mapArray[ i + 1  ][ j ] != map.INDESTRUCTABLE ){
			for( int l = 1; l <= bomb.bombIntensity; l++ ){
				if( i + l <= 12 && ( mapArray[ i + l ][ j ] == map.BREAKABLES || mapArray[i+l][j] == map.isBOMB ) ){
					g.drawImage( p.explosion.getImage(), bomb.getXpos() - 3, bomb.getYpos() + 40 * l, null );	// down intensity
					break;
				}
				if( i + l <= 12 && mapArray[ i + l ][ j ] != map.BREAKABLES )
					g.drawImage( p.explosion.getImage(), bomb.getXpos() - 3, bomb.getYpos() + 40 * l, null );	// down intensity
			
			}
		}
		if( i - 1 >= 0 && mapArray[ i - 1 ][ j ] != map.INDESTRUCTABLE  ){
			for( int l = 1; l <= bomb.bombIntensity; l++ ){
				if( i - l >= 0 && ( mapArray[ i - l ][ j ] == map.BREAKABLES || mapArray[i-l][j] == map.isBOMB) ){
					g.drawImage( p.explosion.getImage(), bomb.getXpos() - 3, bomb.getYpos() - 40 * l, null );	// up intensity
					break;
				}
				if( i -  l >= 0 && mapArray[ i - l ][ j ] != map.BREAKABLES )
					g.drawImage( p.explosion.getImage(), bomb.getXpos() - 3, bomb.getYpos() - 40 * l, null );	// up intensity
			
			}
		}
	}
	
	public void drawMap( Graphics g, int [][] mapArray ){
		for( int i = 0; i < 15; i++ ){
			for( int j = 0; j < 17; j++ ){
				int initX = 210 + ( 40 * ( ( 17 * i + j ) % 17 ) );
				int initY = -15 + ( 40 * ( ( 15 * j + i ) % 15 ) );
				g.drawImage( map.barricadeIcon.getImage(), initX  , initY  , null );
			}
		}
		
		for( int i = 0; i < 13; i++ ){
			for( int j = 0; j < 15; j++ ){
				map.itemPosition(i, j);
				g.drawImage( map.floor.getImage(), map.getInitX(), map.getInitY(), null );
				if( map.powerUp[i][j] == map.POWERUPS1 )		
					g.drawImage( map.powerUps[0].getImage(), map.getInitX(), map.getInitY(), null );
				else if( map.powerUp[ i ][ j ] == map.POWERUPS2 )	
					g.drawImage( map.powerUps[1].getImage(), map.getInitX(), map.getInitY(), null );
				else if( map.powerUp[i][j] == map.POWERUPS3 )	
					g.drawImage( map.powerUps[2].getImage(), map.getInitX(), map.getInitY(), null );
				else if( map.powerUp[i][j] == map.POWERUPS4 )
					g.drawImage( map.powerUps[3].getImage(), map.getInitX(), map.getInitY(), null );
				else if( map.powerUp[i][j] == map.POWERUPS5 )
					g.drawImage( map.powerUps[4].getImage(), map.getInitX(), map.getInitY(), null );
				
				if( mapArray[ i ][ j ] == map.INDESTRUCTABLE ){	 										      // Solid
					g.drawImage( map.barricadeIcon.getImage(), map.getInitX(), map.getInitY(), null );				  //draw Solid Brick
				}
				else if( mapArray[ i ][ j ] == map.BREAKABLES ){	
					g.drawImage( map.floor.getImage(), map.getInitX(), map.getInitY(), null );				  // draw Tile
					if( map.breakableArray[i][j] == map.BREAKABLES1 )										  // Breakables type 1
						g.drawImage( map.breakable1Icon.getImage(), map.getInitX(), map.getInitY(), null );
					else if( map.breakableArray[i][j] == map.BREAKABLES2 )
						g.drawImage( map.breakable2Icon.getImage(), map.getInitX(), map.getInitY(), null ); // Breakables type 2
				}
			}
		}
	}
	public static void delay(long time)	{
		long beforeTime, timeDiff, sleep;
		beforeTime = System.currentTimeMillis();
		timeDiff = System.currentTimeMillis() - beforeTime;
		sleep = time - timeDiff;
		if (sleep < 0)
			sleep = 2;
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
		beforeTime = System.currentTimeMillis();
	}

}
