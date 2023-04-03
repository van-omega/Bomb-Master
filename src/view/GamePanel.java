package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import GameFrame.BombMasterFrame;

import controller.CharacterKey;
import controller.GameController;

import model.Game;
import model.GameButton;

@SuppressWarnings("serial")
public class GamePanel extends Panel implements ActionListener{

	public Game game;
	public BombMasterFrame frame;
	public SelectionScreen selectScreen;
	public CharacterKey key;
	public JButton p1MugShot;
	public JButton p2MugShot;
	public GameButton pause, resume, menu;
	public JPanel pausePanel;
	private ImageIcon background = new ImageIcon( "Images/InGame/background.png" );
	public JLabel pauseLabel, p1WinLabel, p2WinLabel, drawLabel;
	
	public GamePanel( Game game, BombMasterFrame frame ){
		super( null, new Rectangle( 0, 0, 1000, 600 ));
		setGame();
		this.game = game;
		this.frame = frame;
		initComponents();
		gameListeners();
		new Timer( 10, this ).start();
		new GameController( this, frame );
	}
	
	public void initComponents(){
		key = new CharacterKey( game );
	
		pausePanel = new JPanel();
		pausePanel.setLayout( null );
		pausePanel.setOpaque( false );
		pausePanel.setBounds( 0, 0, 1000, 600 );
		pausePanel.setVisible( false );
		add( pausePanel );
		
		menu = new GameButton( new ImageIcon( "Images/InGame/menu.png" ), new ImageIcon( "Images/InGame/menuHover.png" ), 300, 260, 491, 79, true );
		pausePanel.add(menu);
		
		resume = new GameButton( new ImageIcon( "Images/InGame/resume.png" ), new ImageIcon( "Images/InGame/resumeHover.png" ), 300, 390, 491, 79, true );
		pausePanel.add( resume );
		
		pauseLabel = new JLabel( new ImageIcon( "Images/InGame/paused.png" ) );
		pauseLabel.setBounds( 0, 0, 1000, 600 );
		pauseLabel.setVisible( false );
		pausePanel.add( pauseLabel );
		
		p1WinLabel = new JLabel( new ImageIcon( "Images/InGame/Player1Wins.png" ) );
		p1WinLabel.setBounds( 0, 0, 1000, 600 );
		p1WinLabel.setVisible( false );
		pausePanel.add( p1WinLabel );
		
		p2WinLabel = new JLabel( new ImageIcon( "Images/InGame/Player2Wins.png" ) );
		p2WinLabel.setBounds( 0, 0, 1000, 600 );
		p2WinLabel.setVisible( false );
		pausePanel.add( p2WinLabel );
		
		drawLabel = new JLabel( new ImageIcon( "Images/InGame/draw.png" ) );
		drawLabel.setBounds( 0, 0, 1000, 600 );
		drawLabel.setVisible( false );
		pausePanel.add( drawLabel );
		
		pause = new GameButton( new ImageIcon( "Images/InGame/pause.png" ), new ImageIcon( "Images/InGame/pauseHover.png" ), 15, 510, 178, 53, true );
		add( pause);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if( game.p1.DEAD && game.p2.DEAD ){
			setFocusable( false );
			resume.setVisible(false);
			drawLabel.setVisible(true);
			pausePanel.setVisible(true);
		}
		
		else if( game.p1.DEAD ){
			setFocusable( false );
			resume.setVisible(false);
			p2WinLabel.setVisible(true);
			pausePanel.setVisible(true);
		}
		
		else if( game.p2.DEAD ){
			setFocusable( false );
			resume.setVisible(false);
			p1WinLabel.setVisible(true);
			pausePanel.setVisible(true);
		}
	}
	
	public void buttonListener( ActionListener listener ){
		pause.addActionListener(listener);
		resume.addActionListener(listener);
		menu.addActionListener( listener );
	}
	
	public void setGame(){
		setLayout( null );
		setBounds( 0, 0, 1000, 600 );
		setDoubleBuffered( true );
		setVisible( true );
		setFocusable( true );
		setRequestFocusEnabled( true );
		grabFocus(); 
	}
	
	public void gameListeners(){
		addKeyListener( key );	
	}

	@Override
	public void paintBuffer(Graphics g) {
		g.drawImage( background.getImage(), 0, 0, null);
		g.setColor( Color.WHITE);
		g.setFont( new Font( "Serif", Font.BOLD, 26 ) );
		g.drawString( game.p1.numOfBombs + "", 75, 134  );
		g.drawString( game.bomb1.bombIntensity + "", 145, 134  );
		g.drawString( game.p1.life + "", 75, 180  );
		g.drawString( 16 - game.p1.playerSpeed  + "", 145, 180  );
		
		g.drawString( game.p2.numOfBombs + "", 75, 377  );
		g.drawString( game.bomb2.bombIntensity + "", 145, 377  );
		g.drawString( game.p2.life + "", 75, 428  );
		g.drawString( 16 - game.p2.playerSpeed  + "", 145, 428  );
		
		game.drawMap(g, game.map.getMapArray());																							   	 // draw Game Map
		for( int i = 0 ; i <game.bombs.size(); i++ )   
			g.drawImage( game.bombs.get(i).getImage(), game.bombs.get(i).getXpos(), game.bombs.get(i).getYpos(), null ); 	//g.drawImage( game.bomb1.getImage(), game.bomb1.getXpos(), game.bomb1.getYpos(), null );	 			 // draw bomb for player1
		for( int i = 0 ; i <game.bombs2.size(); i++ )   
			g.drawImage( game.bombs2.get(i).getImage(), game.bombs2.get(i).getXpos(), game.bombs2.get(i).getYpos(), null ); 	//g.drawImage( game.bomb1.getImage(), game.bomb1.getXpos(), game.bomb1.getYpos(), null );	 			 // draw bomb for player1
		
		for( int i = 0; i < game.bombs.size(); i++ )
			if( game.bombs.get(i).explode )			
				game.drawExplosion( g , game.bombs.get(i).getI(), game.bombs.get(i).getJ(), game.map.getMapArray(), game.bombs.get(i), game.p1 );	 	//test 20							 // draw Explosion for player1
	
		for( int i = 0; i < game.bombs2.size(); i++ )	
			if( game.bombs2.get(i).explode )			
				game.drawExplosion( g , game.bombs2.get(i).getI(), game.bombs2.get(i).getJ(), game.map.getMapArray(), game.bombs2.get(i), game.p2 );	 	//test 20							 // draw Explosion for player1
		
		if(game. p1.getYpos() <= game.p2.getYpos() ){       									 				 								 // drawing Player1 and Player2 - 
			g.drawImage( game.p1.getImage(), game.p1.getXpos(), game.p1.getYpos(), null );		 				 								 // ( The one above should be drawn before t)he one under)
			g.drawImage( game.p2.getImage(), game.p2.getXpos(), game.p2.getYpos(), null );
		}
		else {
			g.drawImage( game.p2.getImage(), game.p2.getXpos(), game.p2.getYpos(), null );
			g.drawImage( game.p1.getImage(), game.p1.getXpos(), game.p1.getYpos(), null );
		}
		paint = true;

	}
}
