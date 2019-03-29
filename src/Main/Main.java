package Main;
import javax.swing.JFrame;

import ca.shahan.graphics.ViewDisplay;

public class Main {
	public static int width = 1000;
	public static int height = 1000;
	public ViewDisplay panel;
	long graphicsLastUpdateTime;
	long timeBetweenGraphicsUpdate = 100;
	
	public static void main(String[] args) {
		new Main();
	}

	
	public Main() {
		JFrame window = new JFrame();
		window.setSize(width, height);
	    panel = new ViewDisplay(width,height);
		
	
		window.add(panel);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		graphicsLastUpdateTime = System.currentTimeMillis();
		panel.repaint();
		Loop();
		
	}


	private void Loop() {
		while (true){
			if(graphicsLastUpdateTime + timeBetweenGraphicsUpdate > System.currentTimeMillis()) {
				panel.UpdateGUI();
				panel.repaint();
			}
		}
	}
}
