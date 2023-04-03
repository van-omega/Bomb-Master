package view;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import GameFrame.BombMasterFrame;

import controller.GameMenuController;

import model.GameButton;

@SuppressWarnings("serial")
public class GameMenu extends JPanel{
	
	public BombMasterFrame frame;
	public JButton play, howTo, exit, gear, info, music, mute;
	public JLabel background, copyRight;
	public ImageIcon gearRotate = new ImageIcon( "Images/Menu/gear.gif" ), gearSteady = new ImageIcon( "Images/Menu/Gear.png" ), gearSteadyHover = new ImageIcon( "Images/Menu/GearHover.png" );

	public GameMenu( BombMasterFrame frame) {
		super();
		this.frame = frame;
		setGameMenu();
		components();
		new GameMenuController( frame, this  );
	}
	
	public void setGameMenu(){
		setLayout(null);
		setVisible( false );
		setBounds( 0, 0, 1000, 600 );
	}
	
	public void components(){
		
		exit = new GameButton( new ImageIcon( "Images/Menu/Exit.png" ), new ImageIcon( "Images/Menu/ExitHover.png" ), 363, 455, 253, 76, true  );
		add( exit );	
		play = new GameButton( new ImageIcon( "Images/Menu/Start.png" ), new ImageIcon( "Images/Menu/StartHover.png" ), 350, 265, 265, 90, true );
		add( play );
		howTo = new GameButton( new ImageIcon( "Images/Menu/HowToPlay.png" ), new ImageIcon( "Images/Menu/HowToPlayHover.png" ), 341, 376, 274, 65, true );
		add( howTo );
		
		gear = new GameButton( new ImageIcon( "Images/Menu/Gear.png" ), new ImageIcon( "Images/Menu/GearHover.png" ), 893, 493, 77, 71 , true );
		add( gear );
		
		info = new GameButton( new ImageIcon( "Images/Menu/Info.png" ), new ImageIcon( "Images/Menu/InfoHover.png" ), 904, 370, 53, 48, false );
		add( info );
		
		music = new GameButton( new ImageIcon( "Images/Menu/Music.png" ), new ImageIcon( "Images/Menu/MusicHover.png" ), 904, 430, 53, 48, false  );
		add( music );
		
		mute = new GameButton( new ImageIcon( "Images/Menu/Mute.png" ), new ImageIcon( "Images/Menu/MuteHover.png" ), 904, 430, 53, 48, false );
		add( mute );
		
		copyRight = new JLabel( new ImageIcon( "Images/Menu/CopyRight.png" ) );
		copyRight.setBounds( 904, 310, 53, 48 );
		copyRight.setVisible( false );
		add( copyRight );
			
		background = new JLabel( new ImageIcon( "Images/Menu/MenuBackground1.png" ) );
		background.setBounds( 0, 0, 1000, 600 );
		add( background );
	}
	
	public void buttonListener( ActionListener listener ){
		play.addActionListener( listener );
		howTo.addActionListener( listener );
		exit.addActionListener( listener );
		gear.addActionListener( listener );
		info.addActionListener( listener );
		music.addActionListener( listener );
		mute.addActionListener( listener );
	}

}
