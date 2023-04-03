package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Bomb;
import model.Game;

public class CharacterKey implements KeyListener{

	Game game;
	
	public CharacterKey( Game game ) {
		this.game = game;
		game.thread = new Thread( new MoveThread() );
		game.thread.start();
		
		game.thread2 = new Thread( new MoveThread2() );
		game.thread2.start();
	}

	@Override
	public void keyPressed(KeyEvent event ) {
	int key = event.getKeyCode();
		
		if( key == KeyEvent.VK_RIGHT ){
			game.p2.isRight = true;
		}
		
		else if( key == KeyEvent.VK_LEFT ){
			game.p2.isLeft = true;
		}
		
		else if( key == KeyEvent.VK_UP ){
			game.p2.isUp = true;
		}
		
		else if( key == KeyEvent.VK_DOWN ){
			game.p2.isDown = true;
		}
		
		else if( key == KeyEvent.VK_L &&  ( ( game.bomb2.canBomb ) || game.map.getMapArray()[ game.p2.getI() ][ game.p2.getJ() ] != game.map.isBOMB && game.p2.numOfBombs > 0 ) ){ 
			if( game.p2.numOfBombs >0 )
				game.p2.numOfBombs--;
			game.map.getMapArray()[ game.p2.getI() ][ game.p2.getJ() ] = game.map.isBOMB;
			
			game.bomb2.dropBomb = true;
			game.bomb2 = new Bomb(  game.p2.getXMapPos() + 3 ,  game.p2. getYMapPos(), game, game.bomb2.bombIntensity, true, game.p2.getBombColor() ); 
			game.bomb2.setI( game.p2.getI() );												  
			game.bomb2.setJ( game.p2.getJ() );												   
			game.bombs2Copy.add(game.bomb2);
			
			game.bomb2.dropBomb = true;
			game.bomb2 = new Bomb(  game.p2.getXMapPos() + 3 ,  game.p2. getYMapPos(), game, game.bomb2.bombIntensity, true, game.p2.getBombColor() ); 
			game.bomb2.setI( game.p2.getI() );												   
			game.bomb2.setJ( game.p2.getJ() );												   
			game.bomb2.explode();
			game.bombs2.add( game.bomb2 );													   
		}
		
		else if( key == KeyEvent.VK_A ){
			game.p1.isLeft = true;
		}
		
		else if( key == KeyEvent.VK_D ){
			game.p1.isRight = true;
		}
		
		else if( key == KeyEvent.VK_W ){
			game.p1.isUp = true;
		}
		
		else if( key == KeyEvent.VK_S ){
			game.p1.isDown = true;
		}
		
		else if( key == KeyEvent.VK_SPACE  && ( ( game.bomb1.canBomb  ) || game.map.getMapArray()[ game.p1.getI()][game.p1.getJ()] != game.map.isBOMB && game.p1.numOfBombs > 0 ) ){ 
			if( game.p1.numOfBombs >0 )
				game.p1.numOfBombs--;
			game.map.getMapArray()[ game.p1.getI() ][ game.p1.getJ() ] = game.map.isBOMB;
			
			game.bomb1.dropBomb = true;
			game.bomb1 = new Bomb(  game.p1.getXMapPos() + 3 ,  game.p1. getYMapPos(), game, game.bomb1.bombIntensity, true, game.p1.getBombColor() );   
			game.bomb1.setI( game.p1.getI() );													  
			game.bomb1.setJ( game.p1.getJ() );													  
			game.bombsCopy.add(game.bomb1);
			
			game.bomb1.dropBomb = true;
			game.bomb1 = new Bomb(  game.p1.getXMapPos() + 3 ,  game.p1. getYMapPos(), game, game.bomb1.bombIntensity, true, game.p1.getBombColor() );    
			game.bomb1.setI( game.p1.getI() );													  
			game.bomb1.setJ( game.p1.getJ() );													  
			game.bomb1.explode();
			game.bombs.add( game.bomb1 );
			
			
			
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		int key = event.getKeyCode();
		
		if( key == KeyEvent.VK_RIGHT ){
			game.p2.isRight = false;
			game.p2.currentImg = game.p2.steadyR.getImage();
		}
		
		else if( key == KeyEvent.VK_LEFT ){
			game.p2.isLeft = false;
			game.p2.currentImg = game.p2.steadyL.getImage();
		}
		
		else if( key == KeyEvent.VK_DOWN ){
			game.p2.isDown = false;
			game.p2.currentImg = game.p2.steadyD.getImage();
		}
		
		else if( key == KeyEvent.VK_UP ){
			game.p2.isUp = false;
			game.p2.currentImg = game.p2.steadyU.getImage();
		}
		
		else if( key == KeyEvent.VK_L ){
			
		}
		
		else if( key == KeyEvent.VK_SPACE ){
			
		}
		
		else if( key == KeyEvent.VK_A ){
			game.p1.isLeft = false;
			game.p1.currentImg = game.p1.steadyL.getImage();
		}
		
		else if( key == KeyEvent.VK_D ){
			game.p1.isRight = false;
			game.p1.currentImg = game.p1.steadyR.getImage();
		}
		
		else if( key == KeyEvent.VK_W ){
			game.p1.isUp = false;
			game.p1.currentImg = game.p1.steadyU.getImage();
		}
		
		else if( key == KeyEvent.VK_S ){
			game.p1.isDown = false;
			game.p1.currentImg = game.p1.steadyD.getImage();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	public class MoveThread2 implements Runnable{
		@Override
		public void run() {
			while( true ){
				
	
				game.playerAction( game.p2, game.bomb2 );
				
				for( int i = 0; i < game.bombs2.size(); i++ ){
					game.bombCollision( game.p2, game.bombs2.get( i ) );
					game.bombCollision( game.p1, game.bombs2Copy.get( i ) );
					game.bombOrientation( game.p2, game.bombs2.get( i ) );
					game.bombOrientation( game.p1, game.bombs2Copy.get( i ) );
					if( game.bombs2.get(i).buto ){
							game.bombs2.remove(i);
							game.bombs2Copy.remove(i);
							game.p2.numOfBombs++;
					}
				}
		
				if( game.p2.isRight)
					game.p2.moveRight();
				else if( game.p2.isLeft )
					game.p2.moveLeft();
				else if( game.p2.isDown )
					game.p2.moveDown();
				else if( game.p2.isUp )
					game.p2.moveUp();
				Game.delay( game.p2.playerSpeed );
			}
		}
		
	}
	public class MoveThread implements Runnable{

		public void run() {
			while( true ){
				
				
				game.playerAction( game.p1, game.bomb1 );
				
				for( int i = 0; i < game.bombs.size(); i++ ){
					game.bombCollision( game.p1, game.bombs.get( i ) );
					game.bombCollision( game.p2, game.bombsCopy.get( i ) );
					game.bombOrientation( game.p1, game.bombs.get( i ) );
					game.bombOrientation( game.p2, game.bombsCopy.get( i ) );
					if( game.bombs.get(i).buto ){
							game.bombs.remove(i);
							game.bombsCopy.remove(i);
							game.p1.numOfBombs++;
					}
				}
							
				if( game.p1.isRight )
						game.p1.moveRight();
				else if( game.p1.isLeft )
						game.p1.moveLeft();
				else if( game.p1.isDown )
						game.p1.moveDown();
				else if( game.p1.isUp )
						game.p1.moveUp();
				
				Game.delay( game.p1.playerSpeed );
			}
			
		}
		
	}

}
