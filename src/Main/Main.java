package Main;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import ca.shahan.graphics.ViewDisplay;

public class Main {
	public static int width = 1000;
	public static int height = 1000;
	public ViewDisplay panel;
	long graphicsLastUpdateTime;
	long timeBetweenGraphicsUpdate = 100;
	public static int scaleFactor = 5;
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
		setKeyBindings(panel);       
		graphicsLastUpdateTime = System.currentTimeMillis();
		panel.UpdateGUI();
		panel.repaint();		
	}

	private void setKeyBindings(JPanel gridPanel) {
	    InputMap inputMap = 
	            gridPanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
	    inputMap.put(KeyStroke.getKeyStroke("W"), "up arrow");
	    inputMap.put(KeyStroke.getKeyStroke("Z"), "zoom");
	    inputMap.put(KeyStroke.getKeyStroke("X"), "dezoom");
	    inputMap.put(KeyStroke.getKeyStroke("S"), "down arrow");
	    inputMap.put(KeyStroke.getKeyStroke("A"), "left arrow");
	    inputMap.put(KeyStroke.getKeyStroke("D"), "right arrow");

	    inputMap.put(KeyStroke.getKeyStroke("UP"), "up arrow");
	    inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down arrow");
	    inputMap.put(KeyStroke.getKeyStroke("LEFT"), "left arrow");
	    inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right arrow");
	    inputMap = gridPanel.getInputMap(JPanel.WHEN_FOCUSED);
	    
	    inputMap.put(KeyStroke.getKeyStroke("UP"), "up arrow");
	    inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down arrow");
	    inputMap.put(KeyStroke.getKeyStroke("LEFT"), "left arrow");
	    inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right arrow");
	    inputMap.put(KeyStroke.getKeyStroke("Z"), "zoom");
	    inputMap.put(KeyStroke.getKeyStroke("X"), "dezoom");
	    gridPanel.getActionMap().put("up arrow", 
	    new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	panel.yLocaitonShift -= 100;
            	panel.UpdateGame();
        }});
	    
	    gridPanel.getActionMap().put("down arrow", 
	    new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {          
            	panel.yLocaitonShift += 100;
            	panel.UpdateGame();
        }});
	    
	    gridPanel.getActionMap().put("left arrow", 
	    new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {               
            	panel.xLocationShift -= 100;
            	panel.UpdateGame();
        }});
	    
	    
	    gridPanel.getActionMap().put("right arrow", 
	    new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	panel.xLocationShift += 100;
            	panel.UpdateGame();
        }});
	    
	    gridPanel.getActionMap().put("zoom", 
	    new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
            double oldScale =   panel.Scale ;
            panel.Scale +=100*scaleFactor*2;
        	double amountScaled = (double) (1000/panel.Scale);
           	System.out.println("amount scaled :" + (panel.Scale - oldScale/2));
            panel.xLocationShift+=(panel.Scale - oldScale)/2;
            panel.yLocaitonShift+=(panel.Scale - oldScale)/2;
           	panel.UpdateGame();
        }});

	    gridPanel.getActionMap().put("dezoom", 
	    new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
            double oldScale =   panel.Scale ;
            panel.Scale -=100*scaleFactor*2;
        	double amountScaled = (double) (1000/panel.Scale);
           	System.out.println("amount descaled :" + (panel.Scale - oldScale/2));
            panel.xLocationShift+=(panel.Scale - oldScale)/2;
            panel.yLocaitonShift+=(panel.Scale - oldScale)/2;
           	panel.UpdateGame();
        }});

	}
}
