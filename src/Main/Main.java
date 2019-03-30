package Main;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
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
		
//	       InputMap im = panel.getInputMap(JPanel.WHEN_FOCUSED);
//	        ActionMap am = panel.getActionMap();
//
//	        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "onEnter");
//
//	        am.put("onEnter", new AbstractAction() {
//	            @Override
//	            public void actionPerformed(ActionEvent e) {
//	                // Enter pressed
//	            	System.out.println("Enter pressed!");
//	            	panel.Scale +=100;
////	            	panel.calcHypno();
//////	            	panel.clearGui();
////	            	panel.UpdateGUI();
////	            	panel.repaint();
//	            	panel.clearGui();
//	            }
//	        });
		setKeyBindings(panel);
	      

	        
	        
		graphicsLastUpdateTime = System.currentTimeMillis();
		panel.UpdateGUI();
		panel.repaint();
//		Loop();
		
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
                // Enter pressed
            	System.out.println("Enter pressed!");
            	panel.yLocaitonShift -= 100;
//            	panel.calcHypno();
////            	panel.clearGui();
//            	panel.UpdateGUI();
//            	panel.repaint();
            	panel.clearGui();
            }});
	    gridPanel.getActionMap().put("down arrow", 
	    		 new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enter pressed
            	System.out.println("Enter pressed!");
            	panel.yLocaitonShift += 100;
//            	panel.calcHypno();
////            	panel.clearGui();
//            	panel.UpdateGUI();
//            	panel.repaint();
            	panel.clearGui();
            }});
	    gridPanel.getActionMap().put("left arrow", 
	    		 new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enter pressed
            	System.out.println("Enter pressed!");
            	panel.xLocationShift -= 100;
//            	panel.calcHypno();
////            	panel.clearGui();
//            	panel.UpdateGUI();
//            	panel.repaint();
            	panel.clearGui();
            }});
	    gridPanel.getActionMap().put("right arrow", 
	    		 new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enter pressed
            	System.out.println("Enter pressed!");
            	panel.xLocationShift += 100;
//            	panel.calcHypno();
////            	panel.clearGui();
//            	panel.UpdateGUI();
//            	panel.repaint();
            	panel.clearGui();
            }});
	    gridPanel.getActionMap().put("zoom", 
	    		 new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
               // Enter pressed
           	System.out.println("zoom");
//           	
//            double oldWidth = panel.Scale * 0.10;
//            double oldHeight = panel.Scale * 0.10;
//            
//            double newWidth =   	panel.Scale* 0.10;
//            double newHeight =  	panel.Scale * 0.10;
//
//            // Calculate the difference (and divide it by 2)
//            double difWidth = (1000) / 2;
//            double difHeight = (1000/2) / 2;
            double oldScale =   panel.Scale ;
            panel.Scale +=100*scaleFactor*2;
        	double amountScaled = (double) (1000/panel.Scale);
           	System.out.println("amount scaled :" + (panel.Scale - oldScale/2));
//            panel.xLocationShift+=(panel.Scale - oldScale)/2;
//            panel.yLocaitonShift+=(panel.Scale - oldScale)/2;
////           	panel.calcHypno();
////           	panel.clearGui();
//           	panel.UpdateGUI();
//           	panel.repaint();
           	panel.clearGui();
           }});

	    gridPanel.getActionMap().put("dezoom", 
	    		 new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
               // Enter pressed
           	System.out.println("dezoom");
            double oldScale =   panel.Scale ;
            panel.Scale -=100*scaleFactor*2;
        	double amountScaled = (double) (1000/panel.Scale);
           	System.out.println("amount scaled :" + (panel.Scale - oldScale/2));
//            panel.xLocationShift+=(panel.Scale - oldScale)/2;
//            panel.yLocaitonShift+=(panel.Scale - oldScale)/2;
          
//           	panel.calcHypno();
////           	panel.clearGui();
//           	panel.UpdateGUI();
//           	panel.repaint();
           	panel.clearGui();
           }});

	}
	private void Loop() {
//		while (true){
//			if(graphicsLastUpdateTime + timeBetweenGraphicsUpdate > System.currentTimeMillis()) {
////				panel.UpdateGUI();
////				panel.repaint();
//				
//			}
//		}
	}
}
