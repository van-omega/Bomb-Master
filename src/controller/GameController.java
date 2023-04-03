package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GameFrame.BombMasterFrame;
import view.GameMenu;
import view.GamePanel;

public class GameController implements ActionListener{

	public GamePanel gamePanel;
	public BombMasterFrame frame;
	
	public GameController( GamePanel gamePanel, BombMasterFrame frame ) {
		this.gamePanel = gamePanel;
		this.frame = frame;
		this.gamePanel.buttonListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event ) {
		if( event.getSource() == gamePanel.pause ){
			gamePanel.setFocusable(false);
			gamePanel.pauseLabel.setVisible(true);
			gamePanel.pausePanel.setVisible( true );
		}
		
		else if( event.getSource() == gamePanel.menu ){
			gamePanel.setVisible( false );
			frame.getContentPane().remove(frame.gamePanel);
			frame.getContentPane().remove(frame.menu);
			frame.menu = new GameMenu(frame);
			frame.menu.setVisible( true );
			frame.getContentPane().add(frame.menu);
			frame.getContentPane().invalidate();
			frame.getContentPane().validate();
		}
		
		else if( event.getSource() == gamePanel.resume ){
			gamePanel.pausePanel.setVisible(false);
			gamePanel.setFocusable( true );
			gamePanel.setRequestFocusEnabled( true );
			gamePanel.grabFocus();
		}
	}

}
