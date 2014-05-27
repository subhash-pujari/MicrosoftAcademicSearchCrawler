package com.micrsoftapi.searchapp.ui;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainWindow extends JFrame{
	
	JButton startCrawling;
	JButton stopCrawling;
	JButton pauseCrawling;
	
	public MainWindow() {
		// TODO Auto-generated constructor stub
		startCrawling = new JButton("start");
		stopCrawling = new JButton("stop");
		pauseCrawling = new JButton("pause");
		
		this.add(startCrawling, 1);
		this.add(stopCrawling, 2);
		this.add(pauseCrawling, 3);
		
	}
}
