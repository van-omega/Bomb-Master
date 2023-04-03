package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import GameFrame.BombMasterFrame;

import model.GameButton;

@SuppressWarnings("serial")
public class Credits extends JPanel implements ActionListener{

	private JLabel background, credit;
	private GameButton back;
	private int y = 610 ;
	public Timer t;
	public BombMasterFrame frame;
	
	public Credits( BombMasterFrame frame ) {
		super();
		this.frame = frame;
		setCredits();
		components();
	}
	
	public void setCredits(){
		setLayout(null);
		setVisible( false );
		setBounds( 0, 0, 1000, 600 );
	}
	
	public void components(){
		
		back = new GameButton( new ImageIcon( "Images/CreditsPanel/Back.png" ), new ImageIcon( "Images/CreditsPanel/BackHover.png" ),  40, 493, 77, 72, true );
		add( back );
		back.addActionListener(this);
		
		credit = new JLabel( new ImageIcon( "Images/CreditsPanel/Credits.png" ) );
		credit.setBounds( 0, y, 1000, 1768 );
		add( credit );
		
		background = new JLabel( new ImageIcon ( "Images/Menu/Background.png" ) );
		background.setBounds( 0, 0, 1000, 600 );
		add( background );
	}

	public void actionPerformed(ActionEvent event) {
		if( event.getSource() == back ){
			this.setVisible( false );
			frame.menu.setVisible( true );
			y = 610;
			t.stop();
		}
		
	}
	
	public class CreditListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			credit.setBounds( 0, y--, 1000, 1770 );
			if( y < -1770 )
				y = 610;
		}
		
	}
}
