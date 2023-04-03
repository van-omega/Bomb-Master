package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.SelectionScreenController;

import model.GameButton;


import GameFrame.BombMasterFrame;

@SuppressWarnings("serial")
public class SelectionScreen extends JPanel{

	public BombMasterFrame frame;
	public Timer t;
	public JPanel p1Panel, p2Panel, rootPanel, mapPanel;
	public ImageIcon[] genders = { new ImageIcon( "Images/SelectionScreen/male.png" ), new ImageIcon( "Images/SelectionScreen/female.png" ) };
	public ImageIcon[] types = { new ImageIcon( "Images/SelectionScreen/1.png" ), new ImageIcon( "Images/SelectionScreen/2.png" ) };
	public ImageIcon[] bombs = { new ImageIcon( "Images/SelectionScreen/bomb1.png" ), new ImageIcon( "Images/SelectionScreen/bomb2.png" ), new ImageIcon( "Images/SelectionScreen/bomb3.png" ) };
	public ImageIcon[] explosions = { new ImageIcon( "Images/Others/explosion1.png" ), new ImageIcon( "Images/Others/explosion2.png" ), new ImageIcon( "Images/Others/explosion3.png" ) };
	public ImageIcon[] breakables1 = { new ImageIcon( "Images/Map/breakable1.png" ), new ImageIcon( "Images/Map/breakable2.png" ), new ImageIcon( "Images/Map/breakable3.png" ) };
	public ImageIcon[] breakables2 = { new ImageIcon( "Images/Map/breakable4.png" ), new ImageIcon( "Images/Map/breakable5.png" ), new ImageIcon( "Images/Map/breakable6.png" ) };
	public ImageIcon[] barricades = { new ImageIcon( "Images/Map/brick1.png" ), new ImageIcon( "Images/Map/brick2.png" ), new ImageIcon( "Images/Map/brick3.png" ) };
	public ImageIcon[] floors = { new ImageIcon( "Images/Map/tile1.png" ), new ImageIcon( "Images/Map/tile2.png" ), new ImageIcon( "Images/Map/tile3.png" ) };
	public ImageIcon hover = new ImageIcon( "Images/Map/hover.png" );
	public JButton[] gender1 = new JButton[2], gender2 = new JButton[2];
	public JButton[] bColor1 = new JButton[3], bColor2 = new JButton[3];
	public JButton[] exploColor1 = new JButton[3], exploColor2 = new JButton[3];
	public JButton[] p1Type = new JButton[2], p2Type = new JButton[2];
	public JButton[] barricade = new JButton[3], breakable1 = new JButton[3], breakable2 = new JButton[3], floor = new JButton[3];
	public GameButton ok1, ok2, close, next, play;
	public JButton start[];
	private JLabel background, selectScreenBg, mapBg;
	public JLabel[] tileClicked = new JLabel[4];
	public JLabel p1Ready, p2Ready, p2Wait;
	private int i = -600;

	public SelectionScreen( BombMasterFrame frame ) {
		super();
		this.frame = frame;
		setSelectionScreen();
		components();
		restartSScreen();
		new SelectionScreenController( frame, this );
	}
	
	public void setSelectionScreen(){
		setLayout( null );
		setVisible( true );
		setBounds( 0, 0, 1000, 600 );
	}
	
	public void components(){
		
		rootPanel = new JPanel();
		rootPanel.setLayout( null );
		rootPanel.setOpaque(false);
		rootPanel.setBounds( 0, i, 1000, 600 ); 
		add( rootPanel );
		
		mapPanel = new JPanel();
		mapPanel.setLayout( null );
		mapPanel.setOpaque( false );
		mapPanel.setBounds( 0, 0, 1000, 600 );
		rootPanel.add( mapPanel );
		
		for( int i = 0; i < 4; i++ ){
			tileClicked[i] = new JLabel( hover );
			mapPanel.add(tileClicked[i]);
		}
		
		for( int l = 0; l < 3; l++ ){
			breakable1[l] = new JButton( breakables1[l] );
			breakable1[l].setBounds( 430 + 60 * l, 226, 40, 40 );
			breakable1[l].setContentAreaFilled( false );
			breakable1[l].setBorderPainted(false);
			mapPanel.add( breakable1[l] );
			
			breakable2[l] = new JButton( breakables2[l] );
			breakable2[l].setBounds( 430 + 60 * l, 285, 40, 40 );
			breakable2[l].setContentAreaFilled( false );
			breakable2[l].setBorderPainted(false);
			mapPanel.add( breakable2[l] );
			
			barricade[l] = new JButton( barricades[l] );
			barricade[l].setBounds( 410 + 60 * l, 341, 40, 40 );
			barricade[l].setContentAreaFilled( false );
			barricade[l].setBorderPainted(false);
			mapPanel.add( barricade[l] );
			
			floor[l] = new JButton( floors[l] );
			floor[l].setBounds( 310 + 60 * l, 397, 40, 40 );
			floor[l].setBorderPainted(false);
			floor[l].setContentAreaFilled( false );
			mapPanel.add( floor[l] );
		}
		
		play = new GameButton( new ImageIcon( "Images/SelectionScreen/play.png" ), new ImageIcon( "Images/SelectionScreen/playHover.png" ), 440, 410, 136, 144, true );
		mapPanel.add( play );
		
		mapBg = new JLabel( new ImageIcon( "Images/SelectionScreen/map.png" ) );
		mapBg.setBounds( 39, 154, 930, 394 );
		mapPanel.add( mapBg );
		
		next = new GameButton( new ImageIcon( "Images/HelpPanel/Next.png" ), new ImageIcon( "Images/HelpPanel/NextHover.png" ), 770, 60, 63, 57, false );
		rootPanel.add( next );
		
		p2Wait = new JLabel( new ImageIcon( "Images/SelectionScreen/p2Wait.png" ) );
		p2Wait.setBounds( 528, 154, 441, 393 );
		rootPanel.add( p2Wait );
		
		p1Ready = new JLabel( new ImageIcon( "Images/SelectionScreen/p1Ready.png" ) );
		p1Ready.setBounds( 39, 154, 420, 393);
		p1Ready.setVisible( false );
		rootPanel.add( p1Ready );
		
		p2Ready = new JLabel( new ImageIcon( "Images/SelectionScreen/p2Ready.png" ) );
		p2Ready.setBounds( 528, 154, 441, 393);
		p2Ready.setVisible( false );
		rootPanel.add( p2Ready );
		
		
		p1Panel = new JPanel();
		p1Panel.setLayout( null );
		p1Panel.setOpaque( false );
		p1Panel.setBounds( 10, 10, 460, 540 );
		rootPanel.add( p1Panel );
		
		p2Panel = new JPanel();
		p2Panel.setLayout( null );
		p2Panel.setOpaque( false );
		p2Panel.setBounds( 490, 10, 460, 540 );
		p2Panel.setVisible( false );
		rootPanel.add( p2Panel );
		
		ok1 = new GameButton( new ImageIcon( "Images/SelectionScreen/ok.png" ), new ImageIcon( "Images/SelectionScreen/okHover.png" ), 280, 460, 145, 44, true );
		p1Panel.add( ok1 );
		
		ok2 = new GameButton( new ImageIcon( "Images/SelectionScreen/ok.png" ), new ImageIcon( "Images/SelectionScreen/okHover.png" ), 280, 460, 145, 44, true );
		p2Panel.add( ok2 );
		
		for( int l = 0; l < 2; l++ ){
			gender1[l] = new JButton( genders[l] );
			gender1[l].setBounds( 260 + 48 * l, 220, 40, 40 );
			gender1[l].setBorderPainted( false );
			gender1[l].setContentAreaFilled( false );
			p1Panel.add( gender1[l] );
			
			p1Type[l] = new JButton( types[l] );
			p1Type[l].setBounds( 220 + 48 * l, 281, 40, 40 );
			p1Type[l].setBorderPainted( false );
			p1Type[l].setContentAreaFilled( false );
			p1Panel.add( p1Type[l] );
			
			gender2[l] = new JButton( genders[l] );
			gender2[l].setBounds( 235 + 48 * l, 220, 40, 40 );
			gender2[l].setBorderPainted( false );
			gender2[l].setContentAreaFilled( false );
			p2Panel.add( gender2[l ] );
			
			p2Type[l] = new JButton( types[l] );
			p2Type[l].setBounds( 195 + 48 * l, 281, 40, 40 );
			p2Type[l].setBorderPainted( false );
			p2Type[l].setContentAreaFilled( false );
			p2Panel.add( p2Type[l] );
		}
		
		for( int l = 0; l < 3; l++ ){
			bColor1[l] = new JButton( bombs[l]);
			bColor1[l].setBounds( 240 + 48 * l, 345, 40, 40 );
			bColor1[l].setBorderPainted( false );
			bColor1[l].setContentAreaFilled( false );
			p1Panel.add( bColor1[l] );
			
			bColor2[l] = new JButton( bombs[l] );
			bColor2[l].setBounds( 225 + 48 * l, 345, 40, 40 );
			bColor2[l].setBorderPainted( false );
			bColor2[l].setContentAreaFilled( false );
			p2Panel.add( bColor2[l] );
			
			exploColor1[l] = new JButton( explosions[l] );
			exploColor1[l].setBounds( 283 + 48 * l, 407, 40, 40 );
			exploColor1[l].setBorderPainted( false );
			exploColor1[l].setContentAreaFilled( false );
			p1Panel.add( exploColor1[l]);
			
			exploColor2[l] = new JButton( explosions[l] );
			exploColor2[l].setBounds( 260 + 48 * l, 407, 40, 40 );
			exploColor2[l].setBorderPainted( false );
			exploColor2[l].setContentAreaFilled( false );
			p2Panel.add( exploColor2[l]);
			
		}		
		
		close = new GameButton( new ImageIcon( "Images/HelpPanel/exit2.png" ), new ImageIcon( "Images/HelpPanel/exitrollover2.png" ), 930, 40, 33, 26, true );
		rootPanel.add( close );
		
		selectScreenBg = new JLabel( new ImageIcon( "Images/SelectionScreen/selectScreen.png" ) );
		selectScreenBg.setBounds( 0, 0, 1000, 600 );
		rootPanel.add( selectScreenBg );
		
		background = new JLabel( new ImageIcon( "Images/Menu/Background.png" ) );
		background.setBounds( 0, 0, 1000, 600 );
		add( background );
	}
	
	public void buttonListener( ActionListener listener ){
		ok1.addActionListener( listener );
		ok2.addActionListener( listener );
		close.addActionListener( listener );
		next.addActionListener( listener );
		play.addActionListener( listener );
		for( int l = 0; l < 2; l++ ){
			gender1[l].addActionListener( listener );
			p1Type[l].addActionListener( listener );
			gender2[l].addActionListener( listener );
			p2Type[l].addActionListener( listener );
		}
		
		for( int l = 0; l < 3; l++ ){
			bColor1[l].addActionListener( listener );
			bColor2[l].addActionListener( listener );
			exploColor1[l].addActionListener( listener );;
			exploColor2[l].addActionListener( listener );
			
			breakable1[l].addActionListener(listener);
			breakable2[l].addActionListener(listener);
			barricade[l].addActionListener(listener);
			floor[l].addActionListener(listener);
		}
	}
	
	public void restartSScreen(){
		tileClicked[0].setBounds( 430, 226, 40, 40 );
		tileClicked[1].setBounds( 430, 285, 40, 40 );
		tileClicked[2].setBounds( 410, 341, 40, 40 );
		tileClicked[3].setBounds( 310, 397, 40, 40 );
		gender1[0].setContentAreaFilled( true );
		gender2[0].setContentAreaFilled( true );
		p1Type[0].setContentAreaFilled( true );
		p2Type[0].setContentAreaFilled( true );
		bColor1[0].setContentAreaFilled( true );
		bColor2[0].setContentAreaFilled( true );
		exploColor1[0].setContentAreaFilled( true );
		exploColor2[0].setContentAreaFilled( true );
		p1Ready.setVisible( false );
		p2Ready.setVisible( false );
		p1Panel.setVisible( true );
		p2Panel.setVisible( false );
		p2Wait.setVisible( true );
		mapPanel.setVisible( false );
	}
	
	public class SelectionScreenListener implements ActionListener{	
		@Override
		public void actionPerformed(ActionEvent event ) {
			rootPanel.setBounds( 0, i+=2, 1000, 600 );  
			if( i == 10 )
				t.stop();
		}
	}
	
	public class SelectionScreenClose implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event ) {
			rootPanel.setBounds( 0, i-=2, 1000, 600 );
			if( i == -600 ){
				setVisible(false);
				frame.menu.setVisible( true );
				t.stop();
			}
		}
		
	}

}
