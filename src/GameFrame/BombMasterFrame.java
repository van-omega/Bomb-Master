package GameFrame;

import java.awt.Cursor;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import view.Credits;
import view.Exit;
import view.GameMenu;
import view.GamePanel;
import view.HowTo;
import view.SplashScreen;

@SuppressWarnings("serial")
public class BombMasterFrame extends JFrame{

	public SplashScreen splash;
	public GameMenu menu;
	public GamePanel gamePanel;
	public HowTo howTo;
	public Exit exit;
	public Credits credits;
	public static GraphicsConfiguration gc;
	public static GraphicsEnvironment ge;
	public static GraphicsDevice gd;
	
	public BombMasterFrame() {
		super( "Bomb Master" );
		initComponents();
		addComponents();
		setCursor();
		setFrame();
	
	}
	
	public void initComponents(){
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gd = ge.getDefaultScreenDevice();
		gc = gd.getDefaultConfiguration();
		howTo = new HowTo( this );
		credits = new Credits( this );
		exit = new Exit( this );
		splash = new SplashScreen(this );
		new Thread( splash ).start();
		menu = new GameMenu( this );
	}
	
	public void addComponents(){
		this.getContentPane().add( splash );
		this.getContentPane().add( menu );
		this.getContentPane().add( howTo );
		this.getContentPane().add( credits );
		this.getContentPane().add( exit );
	}
	
	public void setFrame(){
		pack();
		setSize( 1006, 628 );
		setIconImage( new ImageIcon( "Images/Others/bomb.png" ).getImage() );
		setLocationRelativeTo( null );
		setLayout( null );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setResizable( false );
		setVisible( true );
	}
	
	public void setCursor(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("Images/Others/Cursor.png");
		Cursor c = toolkit.createCustomCursor(image,new Point(this.getX(),this.getY()), "img");
		this.setCursor (c);
	}
}
