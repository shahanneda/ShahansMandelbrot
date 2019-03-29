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
	Color[][] cells =  new Color[1000][1000];
	public static Color backgroundColor = Color.WHITE;
	Image img ;//Maybe buffred?
	
	public ViewDisplay(int width, int height) {
		img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		calcHypno();
        
		for(int i = 0 ; i < cells[0].length;i++) {
			for(int j = 0; j<cells.length; j++) {
//				cells[i][j] = Color.RED;
				if(hypo[i][j]) {
					cells[i][j] = Color.BLACK;
				}else {
					cells[i][j] = Color.WHITE;
				}
			}
		}
//		cells[4][5] = Color.BLACK;
//		cells = hypo;''
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

	}
	
	
	
	
	public boolean[][] hypo = new boolean[1000][1000];
	double number = 1000;
	double between = 4/number; // 4/length

	double shift = number/2;
	public void calcHypno() {
		for(int i = 0; i < hypo[0].length; i++) {
			for(int j = 0; j< hypo.length; j++) {
				double mandel = between * (i - shift);
				
				if(isMandelbrot(mandel)) {
					hypo[i][j] = true;//I KNOW THAT THIS IS UNNECCECRY BUT ITS NEEDED LATER
				}else {
					hypo[i][j] = false;
				} 
//				System.out.println(mand el);
			}
		}
	}
	final int maxCount = 10;
	
	int currentCount = 0;//use this to instead get diffrene colors
	
	public boolean isMandelbrot(double num) {
		Double value =  Math.pow(num, 2d) + num;
		currentCount++;
		if(Math.abs(value) >=2) {
			return false; //will keep going forever
		}
		if(currentCount >= maxCount) {
			currentCount=0;
			return true;
		}
		
		return isMandelbrot(value);
		
	}
}
