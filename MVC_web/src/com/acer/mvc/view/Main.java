package com.acer.mvc.view;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		// showFlowLayout frame=new showFlowLayout("²Ä¤@­Ó®Ø¬[");
		GridLayoutFrame frame = new GridLayoutFrame();
		frame.setTitle("Member Profile");
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
