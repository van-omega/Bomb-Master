package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GameFrame.BombMasterFrame;

@SuppressWarnings("serial")
public class SplashScreen extends JPanel implements Runnable{

	public JLabel splash;
	public BombMasterFrame frame;
	
	public SplashScreen( BombMasterFrame frame ) {
		
		this.frame = frame;
		setSplashScreen();
		splash = new JLabel( new ImageIcon ( "Images/SplashScreen/Splash.gif" ) );
		splash.setBounds( 0, 0, 1000, 600 );
		add( splash );
	}
	
	public void setSplashScreen(){
		setLayout( null );
		setVisible( true );
		setBounds( 0, 0, 1000, 600 );
	}

	@Override
	public void run() {
		for( int i = 0; i < 10; i++ ){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {	System.out.println("interrupted"); }
		}
		setVisible( false );
		frame.menu.setVisible( true );
	}

}
