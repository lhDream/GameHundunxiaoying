package com.luhua.hundun.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.luhua.hundun.code.Code;

/**
 * ����������
 * @author 	luhua
 * @date	2019��6��13��
 */
public class MainJframe extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel jp1,jp2;
	private JTextField value;
	private Code code;
	
	public MainJframe(Code code) {
		super(code.title);
		this.code = code;
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
