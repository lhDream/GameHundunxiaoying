package com.luhua.hundun.frame;

import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JFrame;

import com.luhua.hundun.code.Code;
import com.luhua.hundun.util.Images;

public class StartJFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	public StartJFrame(Code code) {
		super(code.title);
		setSize(700, 600);
		setLayout(new FlowLayout());
		try {
			setIconImage(Images.getBufferedImage("images/logo.jpg"));
		} catch (IOException e3) {
			MainJframe.err("logo not find!");
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
	}
	
}
