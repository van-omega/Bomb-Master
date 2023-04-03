package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Bomb;
import model.Game;
import model.Map;
import model.Player;

import GameFrame.BombMasterFrame;

import view.GamePanel;
import view.SelectionScreen;

public class SelectionScreenController implements ActionListener{

	public Player p1, p2;
	public Map map;
	public Game game;
	public Bomb bomb1, bomb2;
	public String gender1 = "Male", bColor1 = "1", exploColor1 = "1", ptype1 = "char1", breakable1 = "1", breakable2 = "4", barricade = "1", floor = "1";
	public String gender2 = "Male", bColor2 = "1", exploColor2 = "1", ptype2 = "char1";
	public int chosen = 0;
	public SelectionScreen selectScreen;
	public BombMasterFrame frame;
	
	public SelectionScreenController( BombMasterFrame frame, SelectionScreen selectScreen ) {
		this.selectScreen = selectScreen;
		this.frame = frame;
		this.selectScreen.buttonListener( this );
	}
	
	@Override
	public void actionPerformed(ActionEvent event ) {
		
		if( event.getSource() == selectScreen.gender1[0] ){
			gender1 = "Male";
			chosen = 0;
			selectScreen.gender1[0].setContentAreaFilled( true );
			selectScreen.gender1[1].setContentAreaFilled( false );
		}
		
		else if( event.getSource() == selectScreen.gender1[1] ){
			gender1 = "Female";
			chosen = 1;
			selectScreen.gender1[0].setContentAreaFilled( false );
			selectScreen.gender1[1].setContentAreaFilled(true);
		}
		
		else if( event.getSource() == selectScreen.gender2[0] ){
			gender2 = "Male";
			selectScreen.gender2[0].setContentAreaFilled( true );
			selectScreen.gender2[1].setContentAreaFilled( false );
		}
		
		else if( event.getSource() == selectScreen.gender2[1] ){
			gender2 = "Female";
			selectScreen.gender2[0].setContentAreaFilled( false );
			selectScreen.gender2[1].setContentAreaFilled( true );
		}
		
		else if( event.getSource() == selectScreen.p1Type[0] ){
			ptype1 = "char1";
			selectScreen.p1Type[0].setContentAreaFilled( true );
			selectScreen.p1Type[1].setContentAreaFilled( false );
		}
		
		else if( event.getSource() == selectScreen.p1Type[1] ){
			ptype1 = "char2";
			selectScreen.p1Type[0].setContentAreaFilled( false );
			selectScreen.p1Type[1].setContentAreaFilled( true );
		}
		
		else if( event.getSource() == selectScreen.p2Type[0] ){
			ptype2 = "char1";
			selectScreen.p2Type[0].setContentAreaFilled( true );
			selectScreen.p2Type[1].setContentAreaFilled( false );
		}
		
		else if( event.getSource() == selectScreen.p2Type[1] ){
			ptype2 = "char2";
			selectScreen.p2Type[0].setContentAreaFilled( false );
			selectScreen.p2Type[1].setContentAreaFilled( true );
		}
		
		else if( event.getSource() == selectScreen.bColor1[0] ){
			bColor1 = "1";
			selectScreen.bColor1[0].setContentAreaFilled( true );
			selectScreen.bColor1[1].setContentAreaFilled( false );
			selectScreen.bColor1[2].setContentAreaFilled( false );
		}
		
		else if( event.getSource() == selectScreen.bColor1[1] ){
			bColor1 = "2";
			selectScreen.bColor1[0].setContentAreaFilled( false );
			selectScreen.bColor1[1].setContentAreaFilled( true );
			selectScreen.bColor1[2].setContentAreaFilled( false );
		}
		
		else if( event.getSource() == selectScreen.bColor1[2] ){
			bColor1 = "3";
			selectScreen.bColor1[0].setContentAreaFilled( false );
			selectScreen.bColor1[1].setContentAreaFilled( false );
			selectScreen.bColor1[2].setContentAreaFilled( true );
		}
		
		else if( event.getSource() == selectScreen.bColor2[0] ){
			bColor2= "1";
			selectScreen.bColor2[0].setContentAreaFilled( true );
			selectScreen.bColor2[1].setContentAreaFilled( false );
			selectScreen.bColor2[2].setContentAreaFilled( false );
		}
		
		else if( event.getSource() == selectScreen.bColor2[1] ){
			bColor2 = "2";
			selectScreen.bColor2[0].setContentAreaFilled( false );
			selectScreen.bColor2[1].setContentAreaFilled( true );
			selectScreen.bColor2[2].setContentAreaFilled( false );
		}
		
		else if( event.getSource() == selectScreen.bColor2[2] ){
			bColor2 = "3";
			selectScreen.bColor2[0].setContentAreaFilled( false );
			selectScreen.bColor2[1].setContentAreaFilled( false );
			selectScreen.bColor2[2].setContentAreaFilled( true );
		}
		
		else if( event.getSource() == selectScreen.exploColor1[0] ){
			exploColor1 = "1";
			selectScreen.exploColor1[0].setContentAreaFilled( true );
			selectScreen.exploColor1[1].setContentAreaFilled( false );
			selectScreen.exploColor1[2].setContentAreaFilled( false );
		}
		
		else if( event.getSource() == selectScreen.exploColor1[1] ){
			exploColor1 = "2";
			selectScreen.exploColor1[0].setContentAreaFilled( false );
			selectScreen.exploColor1[1].setContentAreaFilled( true );
			selectScreen.exploColor1[2].setContentAreaFilled( false );
		}
		
		else if( event.getSource() == selectScreen.exploColor1[2] ){
			exploColor1 = "3";
			selectScreen.exploColor1[0].setContentAreaFilled( false );
			selectScreen.exploColor1[1].setContentAreaFilled( false );
			selectScreen.exploColor1[2].setContentAreaFilled( true );
		}
		
		else if( event.getSource() == selectScreen.exploColor2[0] ){
			exploColor2 = "1";
			selectScreen.exploColor2[0].setContentAreaFilled( true );
			selectScreen.exploColor2[1].setContentAreaFilled( false );
			selectScreen.exploColor2[2].setContentAreaFilled( false );
		}
		
		else if( event.getSource() == selectScreen.exploColor2[1] ){
			exploColor2 = "2";
			selectScreen.exploColor2[0].setContentAreaFilled( false );
			selectScreen.exploColor2[1].setContentAreaFilled( true );
			selectScreen.exploColor2[2].setContentAreaFilled( false );
		}
		
		else if( event.getSource() == selectScreen.exploColor2[2] ){
			exploColor2 = "3";
			selectScreen.exploColor2[0].setContentAreaFilled( false );
			selectScreen.exploColor2[1].setContentAreaFilled( false );
			selectScreen.exploColor2[2].setContentAreaFilled( true );
		}
		
		else if( event.getSource() == selectScreen.ok1 ){
			selectScreen.gender2[0].setContentAreaFilled(true);
			selectScreen.gender2[1].setContentAreaFilled(true);
			if(gender1.equals("Male")){
				selectScreen.gender2[chosen].setVisible( false );
				gender2 = "Female";
			}
			else{
				selectScreen.gender2[chosen].setVisible( false );
				gender2 = "Male";
			}
			selectScreen.p1Ready.setVisible( true );
			selectScreen.p1Panel.setVisible(false);
			selectScreen.p2Wait.setVisible( false );
			selectScreen.p2Panel.setVisible( true );
		}
		
		else if( event.getSource() == selectScreen.ok2 ){
			selectScreen.p2Ready.setVisible( true );
			selectScreen.p2Panel.setVisible(false);
			selectScreen.next.setVisible( true );
		}
		
		else if( event.getSource() == selectScreen.close ){
			selectScreen.t = new Timer( 1, selectScreen. new SelectionScreenClose() );
			selectScreen.t.start();
		}
		
		else if( event.getSource() == selectScreen.next ){
			selectScreen.next.setVisible( false );
			selectScreen.p1Panel.setVisible( false );
			selectScreen.p2Panel.setVisible( false );
			selectScreen.mapPanel.setVisible( true );
		}
		
		else if( event.getSource() == selectScreen.breakable1[0] ){
			breakable1 = "1";
			selectScreen.tileClicked[0].setBounds( 430, 226, 40, 40 );
		}
		
		else if( event.getSource() == selectScreen.breakable1[1] ){
			breakable1 = "2";
			selectScreen.tileClicked[0].setBounds( 430+60, 226, 40, 40 );
		}
		
		else if( event.getSource() == selectScreen.breakable1[2] ){
			breakable1 = "3";
			selectScreen.tileClicked[0].setBounds( 430+120, 226, 40, 40 );
		}
		
		else if( event.getSource() == selectScreen.breakable2[0] ){
			breakable2 = "4";
			selectScreen.tileClicked[1].setBounds( 430, 285, 40, 40 );
		}
		
		else if( event.getSource() == selectScreen.breakable2[1] ){
			breakable2 = "5";
			selectScreen.tileClicked[1].setBounds( 430+60 , 285, 40, 40 );
		}
		
		else if( event.getSource() == selectScreen.breakable2[2] ){
			breakable2 = "6";
			selectScreen.tileClicked[1].setBounds( 430+120, 285, 40, 40 );
		}
		
		else if( event.getSource() == selectScreen.barricade[0] ){
			barricade = "1";
			selectScreen.tileClicked[2].setBounds( 410, 341, 40, 40 );
		}
	
		else if( event.getSource() == selectScreen.barricade[1] ){
			barricade = "2";
			selectScreen.tileClicked[2].setBounds( 410+60, 341, 40, 40 );
		}
		
		else if( event.getSource() == selectScreen.barricade[2] ){
			barricade = "3";
			selectScreen.tileClicked[2].setBounds( 410+120, 341, 40, 40 );
		}
		
		else if( event.getSource() == selectScreen.floor[0] ){
			floor = "1";
			selectScreen.tileClicked[3].setBounds( 310, 397, 40, 40 );
		}
		
		else if( event.getSource() == selectScreen.floor[1] ){
			floor = "2";
			selectScreen.tileClicked[3].setBounds( 310+60, 397, 40, 40 );
		}
		
		else if( event.getSource() == selectScreen.floor[2] ){
			floor = "3";
			selectScreen.tileClicked[3].setBounds( 310+120, 397, 40, 40 );
		}
		
		else if( event.getSource() == selectScreen.play ){
			selectScreen.setVisible(false);
			map = new Map( breakable1, breakable2, barricade, floor );
			p1 = new Player( 255, 16, ptype1, map, exploColor1 , gender1, bColor1 );
			p2 = new Player( 815, 496, ptype2, map, exploColor2, gender2, bColor2 );
			game = new Game( p1, p2, map );
			frame.gamePanel = new GamePanel( game, frame );
			frame.getContentPane().add( frame.gamePanel);
			frame.gamePanel.setVisible( true );
			frame.gamePanel.setFocusable( true );
			frame.gamePanel.setRequestFocusEnabled( true );
			frame.gamePanel.grabFocus(); 
		}
	}
}
