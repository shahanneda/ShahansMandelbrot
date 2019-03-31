package ca.shahan.graphics;
import Main.Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Objects;

import javax.swing.JPanel;

public class ViewDisplay extends JPanel{

	private static final long serialVersionUID = -6080146226481701265L;
	Color[][] cells =  new Color[1000][1000];
	public static Color backgroundColor = Color.WHITE;
	public HashMap<XYPAIR,Color> savedColors = new HashMap<XYPAIR,Color>(30000000); 
	Image img ;//Maybe buffred?
	
	public ViewDisplay(int width, int height) {
		img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		calcHypno();
        System.out.println(isMandelbrot(0,0,3,4));
//		for(int i = 0 ; i < cells[0].length;i++) {
//			for(int j = 0; j<cells.length; j++) {
////				cells[i][j] = Color.RED;
//				if(hypo[i][j]) {
//					cells[i][j] = Color.BLACK;
//				}else {
//					cells[i][j] = Color.WHITE;
//				}
//			}
//		}
//		cells[4][5] = Color.BLACK;
//		cells = hypo;''
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, this);
		System.out.println("Drawing");
	}
	public void clearGui() {
//		cells = new Color[1000][1000];
//		img = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_ARGB);
//		for(int i = 0 ; i < cells[0].length;i++) {
//			for(int j = 0; j<cells.length; j++) {
//				cells[i][j] = Color.blue;
//				
//			}
//		}
		long timeTaken = System.nanoTime();
		calcHypno();
		System.out.println("Calc Hypno took:" + (System.nanoTime()-timeTaken)/ 1_000_000_000.0);
		timeTaken = System.nanoTime();
		UpdateGUI();
		System.out.println("Update Gui  took:" + (System.nanoTime()-timeTaken)/ 1_000_000_000.0);
		timeTaken = System.nanoTime();
		
		repaint();
		System.out.println("Repaint took" + (System.nanoTime()-timeTaken)/ 1_000_000_000.0);
		timeTaken = System.nanoTime();
//		calcHypno();
		
	    
	}
	public void UpdateGUI() {
		//  use when u want to clear g.clearrect
		System.out.println("UPDATING");
		Graphics g = img.getGraphics();
		((Graphics2D) g).setBackground(backgroundColor);
//		g.clearRect(0, 0,  Main.width, Main.height );
//		g.setColor(Color.black);
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
//		g.setColor(Color.red);
//		g.fillRect(500, 500, 1, 1);

	}
	
	
	
	
//	public boolean[][] hypo = new boolean[1000][1000];
	public double Scale = 1000;
	public double OriginalScale = 1000;
	// 4/length
   public int xLocationShift = 1;
   public int yLocaitonShift = 1;
	
	public void calcHypno() {
		double between = 4/Scale;
		double shift = (Scale/2);
		System.out.println("CALCUTAITM with scale" + Scale + "Scale Amount = " + Scale/OriginalScale);
		double xM2 = between * (0+(xLocationShift*Scale/OriginalScale) - shift);
		double yM2 = between * (0+(yLocaitonShift*Scale/OriginalScale) - shift);
		System.out.println(xM2 + " " + yM2);
		for(int i = 0; i < 1000; i++) {
			for(int j = 0; j< 1000; j++) {
				double xM = between * (i+(xLocationShift*Scale/OriginalScale) - shift);
				double yM = between * (j+(yLocaitonShift*Scale/OriginalScale) - shift);
//				double mandelConst = Math.pow(xM, 2) + Math.pow(yM, 2);
				
				Color savedColor = savedColors.get (new XYPAIR(xM, yM));
//				System.out.println(savedColor);
				if(savedColor==null) {
					
					int numbers = isMandelbrot(0, 0,xM,yM);

					if(numbers == -1) {
						//					System.out.println(number);
						//					hypo[i][j] = true;//I KNOW THAT THIS IS UNNECCECRY BUT ITS NEEDED LATER
						cells[i][j] = Color.BLACK;
					}else {
						int colorNumber = numbers;
						int r = 255- (int) Math.max(0d, Math.min(255d, colorNumber * 5));
						int g = 255- (int) Math.max(0d, Math.min(255d, colorNumber * 5));
						int b = 255- (int) Math.max(0d, Math.min(255d, colorNumber * 7));
						//					Color c = Color.WHITE;
						//					cells[i][j]  =MapColor(colorNumber, xM, yM);
//						Color c = Color.getHSBColor((float) scaleRange(colorNumber,0,1), 1, 1);
						Color c =new Color(100,r,55);
						cells[i][j] = c;
						savedColors.put(new XYPAIR(xM, yM),c); 
						//					hypo[i][j] = false;
					} 
					//				System.out.println(mand el);
				}else {
//					System.out.println("using saved colro");
					cells[i][j] = savedColor;
				}
			}
		}
	}

	double scaleRange(double number, double high, double low)
	{
	  return (high + low) * 0.5f;
	}
	int currentCount = 0;//use this to instead get diffrene colors
	
	 private Color MapColor(int i, double r, double c){
//		 System.out.println("Mapping color " + r + " " + c);
     double di=(double )i;
     double zn;
     double hue; 

         zn = Math.sqrt(r + c);
         hue = di + 1.0 - Math.log(Math.log(Math.abs(zn))) / Math.log(2.0);  // 2 is escape radius
         hue = 0.95 + 20.0 * hue; // adjust to make it prettier
         // the hsv function expects values from 0 to 360
         while (hue >= 360.0)
             hue -= 360.0;
         while (hue <= 0.0)
             hue += 360.0;

         return Color.getHSBColor((float)hue, 0.8f, 1.0f);
     }
//	public boolean isMandelbrot(double constNum, double currentnum) {
//		double value = Math.pow(currentnum, 2) + constNum;
////		double value =  Math.pow(currentnum, 2d) + constNum;
//		currentCount++;
////		if(Math.abs(value) >=2) {
////			return false; //will keep going forever
////		}
////		if(value == currentnum) {
//////			System.out.println("Stayed Contained");
////			return false;
////		}
//		if(currentCount >= maxCount) {
//			currentCount=0;
//			if(Math.abs(value) < 4) {//maybe change the =  ?
//				return true;//Stayed in
//				
//			}else {
//				return false; //escaped
//			}
//			
////			System.out.println("ESCAPED TO INFINITY!!");
//			
//		}
////		System.out.println(value);
//		
//		return isMandelbrot(constNum, value);
//		
	
	
////	}
	final int maxCount = 255;
	
	public int isMandelbrot(double a, double b, double c1, double c2) {//-1 means in madel
		double newA = a*a - b*b + c1; 
		double newB = 2*a*b + c2;
//		System.out.println("" + newA + " " + newB);
		for(int i = 0; i < maxCount; i++) {
			double oldA = newA;
			newA =  newA*newA - newB*newB + c1;
			newB =  2*oldA*newB + c2;
			if(newA*newA + newB*newB > 4) {
//				System.out.println("Escaped to infinity!!");
//				System.out.println(i);
				return i;
			}
			if(i >= maxCount-2) {
//				
//					System.out.println("Stayed in, " + newA + "" + newB);
					return -1;
				
				
			}
		}
	    return -1;
	    	
		
	}
}

class XYPAIR{
	double x;
	double y;
	public XYPAIR(double x, double y) {
		this.x = x;
		this.y = y;
	}
	@Override    
	public boolean equals(Object o) {        
		return ((XYPAIR)o).x == x && ((XYPAIR)o).y == y;
	}    
	@Override    
	public int hashCode() {        
		return Objects.hash(x,y);
	}    
}
