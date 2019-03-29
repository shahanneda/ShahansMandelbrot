package Main;
import javax.swing.JFrame;

import ca.shahan.graphics.ViewDisplay;

public class Main {
	public int width = 750;
	public int height = 750;
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
		Loop();
	}


	private void Loop() {
		while (true){
			if(graphicsLastUpdateTime + timeBetweenGraphicsUpdate > System.currentTimeMillis()) {
				panel.UpdateGUI();
			}
		}
	}
}
