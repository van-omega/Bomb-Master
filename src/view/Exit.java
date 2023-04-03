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
public class Exit extends JPanel implements ActionListener{

	private int i = -600;
	public GameButton check, x;
	public JLabel background, exit;
	public JPanel panel;
	public Timer t;
	public BombMasterFrame frame;
	
	public Exit( BombMasterFrame frame ) {
		super();
		this.frame = frame;
		setExit();
		components();
	}
	
	public void setExit(){
		setLayout(null);
		setVisible( false );
		setBounds( 0, 0, 1000, 600 );
	}
	
	public void components(){
		
		panel = new JPanel();
		panel.setLayout( null );
		panel.setOpaque( false );
		panel.setBounds( 0, 0, 1000, 600 );
		add( panel );
		
		check = new GameButton( new ImageIcon( "Images/ExitPanel/Check.png" ), new ImageIcon( "Images/ExitPanel/CheckHover.png" ), 160, 240, 224, 218, true );
		check.addActionListener( this );
		panel.add( check );
		
		x = new GameButton( new ImageIcon( "Images/ExitPanel/X.png" ), new ImageIcon( "Images/ExitPanel/XHover.png" ), 610, 250, 233, 209, true );
		x.addActionListener( this );
		panel.add( x );
		
		exit = new JLabel( new ImageIcon( "Images/ExitPanel/Exit.png" ) );
		exit.setBounds( 0, 0, 1000, 600 );
		panel.add( exit );
		
		background = new JLabel( new ImageIcon( "Images/Menu/Background.png" ) );
		background.setBounds( 0, 0, 1000, 600 );
		background.setFocusable(false);
		add( background );
	}
	
	@Override
	public void actionPerformed(ActionEvent event ) {
		if( event.getSource() == check ){
			System.exit(0);
		}
		else if( event.getSource() == x ){
			t = new Timer( 1, new ExitClose() );
			t.start();
		}
	}
	
	public class ExitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
				panel.setBounds(0, i+=2, 1000, 600 );
				if( i == 10 )
					t.stop();
		}
		
	}
	
	public class ExitClose implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			panel.setBounds( 0, i-=2, 1000, 600 );
			if( i == -600 ){
				setVisible(false);
				frame.menu.setVisible( true );
				t.stop();
			}
		}
	}
}
