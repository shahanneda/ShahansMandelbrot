package ca.shahan.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ViewDisplay extends JPanel{
	
	public static Color backgroundColor = Color.black;
	Image img ;//Maybe buffred?
	public ViewDisplay(int width, int height) {
		img = new BufferedImage(width,height,1);
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, this);
	}
	
	public void UpdateGUI() {
		//  use when u want to clear g.clearrect
		Graphics g = img.getGraphics();
		((Graphics2D) g).setBackground(backgroundColor);
		g.setColor(Color.RED);
		g.fillRect(100,100,100,100);
	}
}
