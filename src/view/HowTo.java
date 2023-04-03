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
public class HowTo extends JPanel implements ActionListener{
	
	private JLabel background, controls, powerUps;
	private GameButton back, next, close;
	private int i = -600;
	public JPanel panel;
	public Timer t;
	public BombMasterFrame frame;
	
	public HowTo( BombMasterFrame frame ) {
		super();
		this.frame = frame;
		setHowTo();
		components();
	}
	
	public void setHowTo(){
		setLayout(null);
		setVisible( false );
		setBounds( 0, 0, 1000, 600 );
	}
	
	public void components(){
		
		panel = new JPanel();
		panel.setLayout( null );
		panel.setOpaque(false);
		panel.setBounds( 0, i, 1000, 600 );
		add( panel );
		
		back = new GameButton( new ImageIcon( "Images/HelpPanel/Back.png" ), new ImageIcon( "Images/HelpPanel/BackHover.png" ), 170, 60, 63, 57, true );
		back.addActionListener(this);
		panel.add( back );
		
		next = new GameButton( new ImageIcon( "Images/HelpPanel/Next.png" ), new ImageIcon( "Images/HelpPanel/NextHover.png" ), 770, 60, 63, 57, true );
		next.addActionListener(this);
		panel.add( next );
		
		close = new GameButton( new ImageIcon( "Images/HelpPanel/exit2.png" ), new ImageIcon( "Images/HelpPanel/exitrollover2.png" ), 930, 40, 33, 26, true );
		close.addActionListener( this );
		panel.add( close );
		
		controls = new JLabel( new ImageIcon( "Images/HelpPanel/Controls.png" ) );
		controls.setBounds( 0, 0, 1000, 600 );
		panel.add( controls );
		
		powerUps = new JLabel( new ImageIcon( "Images/HelpPanel/PowerUps.png" ) );
		powerUps.setBounds( 0, 0, 1000, 600 );
		powerUps.setVisible( false );
		panel.add( powerUps );
		
		background = new JLabel( new ImageIcon( "Images/Menu/Background.png" ) );
		background.setBounds( 0, 0, 1000, 600 );
		add( background );
	}

	@Override
	public void actionPerformed(ActionEvent event ) {
		if( event.getSource() == back ){
			controls.setVisible( true );
			powerUps.setVisible( false );
		}
		
		else if( event.getSource() == next ){
			powerUps.setVisible( true );
			controls.setVisible( false );
		}
		
		else if( event.getSource() == close ){
			t = new Timer( 1, new HowToClose() );
			t.start();
		}
	}
	
	public class HowToListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
				panel.setBounds(0, i+=2, 1000, 600 );
				if( i == 10 )
					t.stop();
		}
		
	}
	
	public class HowToClose implements ActionListener{

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
