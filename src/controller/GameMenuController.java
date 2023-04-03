package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import view.GameMenu;
import view.SelectionScreen;

import GameFrame.BombMasterFrame;

public class GameMenuController implements ActionListener{
	
	public BombMasterFrame frame;
	public GameMenu menu;
	public SelectionScreen selectScreen;
	boolean firstClick = true, mute;
	
	public GameMenuController( BombMasterFrame frame, GameMenu menu  ) {
		this.frame = frame;
		this.menu = menu;
		this.menu.buttonListener(this);
	}

	@Override
	public void actionPerformed( ActionEvent event ) {
		if( event.getSource() ==  menu.play ){
			
			frame.menu.setVisible(false);
			frame.howTo.setVisible( false );
			frame.exit.setVisible( false );
			
			selectScreen = new SelectionScreen( frame );
			frame.add(selectScreen);
			selectScreen.t = new Timer( 1, selectScreen.new SelectionScreenListener() );
			selectScreen.t.start();	
		}
		
		else if( event.getSource() == menu.howTo ){
			frame.menu.setVisible( false );
			frame.exit.setVisible( false );
			//frame.selectScreen.setVisible( false );
			
			frame.howTo.setVisible( true );
			frame.howTo.t = new Timer( 1, frame.howTo.new HowToListener() );
			frame.howTo.t.start();	
		}
		
		else if( event.getSource() == menu.exit ){
			frame.menu.setVisible( false );
			frame.howTo.setVisible( false );
			//frame.selectScreen.setVisible( false );
			
			frame.exit.setVisible( true );
			frame.exit.t = new Timer( 1, frame.exit.new ExitListener() );
			frame.exit.t.start();
		}
		
		else if( event.getSource() == menu.gear ){
			menu.gear.setIcon(menu.gearRotate);
			menu.gear.setRolloverIcon( menu.gearRotate );
			new Thread( new GearThread() ).start();
		} 
		
		else if( event.getSource() == menu.info ){
			frame.menu.setVisible( false );
			frame.credits.setVisible( true );
			frame.credits.t = new Timer( 15, frame.credits.new CreditListener() );
			frame.credits.t.start();
		}
		
		else if( event.getSource() == menu.music ){
			menu.music.setVisible( false );
			menu.mute.setVisible( true );
			mute = true;
		}
		
		else if( event.getSource() == menu.mute ){
			menu.mute.setVisible( false );
			menu.music.setVisible( true );
			mute = false;
		}	
	}
	
	public class GearThread implements Runnable{

		@Override
		public void run() {
			if( firstClick ){
				if( !mute )
					menu.music.setVisible(true);
				else 
					menu.mute.setVisible( true );
				delay( 150 );
				menu.info.setVisible( true );
				delay( 150 );
				menu.copyRight.setVisible( true );
				delay( 150 );
				firstClick = false;
				
			}
			else{
				menu.copyRight.setVisible( false );
				delay( 150 );
				menu.info.setVisible( false );
				delay( 150 );
				menu.music.setVisible(false);
				menu.mute.setVisible(false);
				delay( 150 );
				firstClick = true;
			}
			menu.gear.setIcon(menu.gearSteady );
			menu.gear.setRolloverIcon( menu.gearSteadyHover );
		}
		
	}
	
	public void delay(long time)
	{
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
