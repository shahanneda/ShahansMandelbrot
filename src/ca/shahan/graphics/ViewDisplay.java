package ca.shahan.graphics;
import Main.Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;
import javax.swing.JPanel;

public class ViewDisplay extends JPanel{

	private static final long serialVersionUID = -6080146226481701265L;
	Color[][] cells =  new Color[1000][1000];
	public static Color backgroundColor = Color.WHITE;
	Image img ;
	public ViewDisplay(int width, int height) {
		img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		calcHypno();
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, this);;
	}
	
	public void UpdateGame() {
		calcHypno();
		UpdateGUI();
		repaint();   
	}
	
	public void UpdateGUI() {
		int cellWidth = Main.width / cells.length ; //Calculatest the size of each cell
		int cellHeight = Main.height / cells[0] .length;		
		IntStream.range(0, cells.length).parallel().forEach(id -> {//Loops over all the cells, with multiple threads
			Graphics g2 = img.getGraphics();
			for(int j = 0; j<cells.length; j++) {
				g2.setColor(cells[id][j]);
				g2.fillRect(id*cellWidth,				
						j*cellHeight,		
						cellWidth,	
						cellHeight);	
			}
				
		});			        
	}
   public double Scale = 1000; 
   public double OriginalScale = 1000;//Used for calulating the amount scaled
   public int xLocationShift = 1;//used for translating
   public int yLocaitonShift = 1;
   long timeTakenh = System.nanoTime();
   
   public void calcHypno() {
		double between = 4/Scale;// 4 as thats where the mandelbrot lies, -2 to +2
		double shift = (Scale/2); // How much to shift by the center the picture

		IntStream.range(0,1000).parallel().forEach(i -> {//Loops over all the cells
			IntStream.range(0, 1000).parallel().forEach(j -> {
				double xM = between * (i+(xLocationShift) - shift); //This is the x of point of mandelbrot we are drawing
				double yM = between * (j+(yLocaitonShift) - shift);

				int numbers = isMandelbrot(0, 0,xM,yM);//Check if xM, ym is in the set
			
				
				if(numbers == -1) {//returns -1 if is in the set
					cells[i][j] = Color.BLACK;
				}else {
					int colorNumber = numbers;
					int r = 255- (int) Math.max(0d, Math.min(255d, colorNumber * 5));
					int g = 255- (int) Math.max(0d, Math.min(255d, colorNumber * 5));
					int b = 255- (int) Math.max(0d, Math.min(255d, colorNumber * 7));

					Color c =new Color(100,r,55);//Makes the color for the cell
					cells[i][j] = c;//Asigns the color to the cells array, to draw later
					
				} 								
			});
		});		
	}

	final int maxCount = 255;// The number of iterations, makes picture more detialed 
	public int isMandelbrot(double a, double b, double c1, double c2) {//-1 means in madel
		double newA = a*a - b*b + c1;  // finds the new a and b, complex number multiplaciation
		double newB = 2*a*b + c2;
		for(int i = 0; i < maxCount; i++) {
			
			double oldA = newA; // Saves the old a temp
			newA =  newA*newA - newB*newB + c1;
			newB =  2*oldA*newB + c2;
			
			if(newA*newA + newB*newB > 4) {//Went of to infinity
				return i;
			}
			if(i >= maxCount-2) {//is in the set
			    return -1;
			}
		}
	    return -1;
	}
}

