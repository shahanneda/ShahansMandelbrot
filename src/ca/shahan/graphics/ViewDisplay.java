package ca.shahan.graphics;
import Main.Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ViewDisplay extends JPanel{

	private static final long serialVersionUID = -6080146226481701265L;
	Color[][] cells =  new Color[100][100];
	public static Color backgroundColor = Color.WHITE;
	Image img ;//Maybe buffred?
	
	public ViewDisplay(int width, int height) {
		img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		

		for(int i = 0 ; i < cells[0].length;i++) {
			for(int j = 0; j<cells.length; j++) {
				cells[i][j] = Color.RED;
			}
		}
		cells[4][5] = Color.BLACK;
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, this);
	}
	
	public void UpdateGUI() {
		//  use when u want to clear g.clearrect
		Graphics g = img.getGraphics();
		((Graphics2D) g).setBackground(backgroundColor);
		
		
		int cellWidth = Main.width / cells.length ;
		int cellHeight = Main.height / cells[0] .length;//MIGHT HAVE MixED THESE UP
		for(int i = 0 ; i < cells[0].length;i++) {
			for(int j = 0; j<cells.length; j++) {
				
				
				g.setColor(cells[i][j]);
				g.fillRect(i*cellWidth,
						
						j*cellHeight,
						
						cellWidth,
						
						cellHeight);
				
			}
		}
		g.setColor(Color.RED);
		g.fillRect(100,100,100,100);
	}
}
