package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import GameFrame.BombMasterFrame;

@SuppressWarnings("serial")
abstract class Panel extends JPanel{
	
	public int bufferWidth;
	public int bufferHeight;
	public BufferedImage bufferImage;
	public Graphics bufferGraphics;
	
	public boolean paint = false;
	
	public Panel(LayoutManager layout, Rectangle bound){
		setBounds(bound.x, bound.y, bound.width, bound.height);
		setLayout(layout);
		setDoubleBuffered(true);
		setBackground(Color.BLACK);
	}
	
	public void showPanel(){
		setVisible(true);
	}
	
	public void hidePanel(){
		setVisible(false);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		//	checks the buffersize with the current panelsize
		//	or initialises the image with the first paint
		if(bufferWidth!=getSize().width || bufferHeight!=getSize().height || bufferImage==null || bufferGraphics==null)
			resetBuffer();
		
		if(bufferGraphics!=null){
			//this clears the offscreen image, not the onscreen one
			bufferGraphics.fillRect(0,0,bufferWidth,bufferHeight);

			//calls the paintbuffer method with the offscreen graphics as a param
			paintBuffer(bufferGraphics);

		//	we finally paint the offscreen image onto the onscreen image
			g2d.drawImage(bufferImage,0,0,null);
		}

		if(paint)
			repaint();
		
		Thread.yield();
	}

	private void resetBuffer(){
		 //always keep track of the image size
		bufferWidth=getSize().width;
		bufferHeight=getSize().height;

		//	clean up the previous image
		if(bufferGraphics!=null){
			bufferGraphics.dispose();
			bufferGraphics=null;
		}
		if(bufferImage!=null){
			bufferImage.flush();
			bufferImage=null;
		}
		System.gc();

		//	create the new image with the size of the panel
		bufferImage = BombMasterFrame.gc.createCompatibleImage(bufferWidth,bufferHeight);
		bufferGraphics=bufferImage.getGraphics();
	}
	
	public abstract void paintBuffer(Graphics g);
}