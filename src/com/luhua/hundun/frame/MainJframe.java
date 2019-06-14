package com.luhua.hundun.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.luhua.hundun.code.Code;
import com.luhua.hundun.util.DomXML;
import com.luhua.hundun.util.Images;


/**
 * ����������
 * @author 	luhua
 * @date	2019��6��13��
 */
public class MainJframe extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel jp1,jp2;
	private JLabel value;
	private Code code;
	private int index = 0;

	public MainJframe(Code code) {
		super(code.title);
		this.code = code;
		
		try {
			setIconImage(Images.getBufferedImage("images/logo.jpg"));
		} catch (IOException e3) {
			alert("not find image!!!");
		}
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());

		jp2 = new JPanel();
		jp2.setSize(700, 200);
		
		jp1 = new JPanel();
		jp1.setPreferredSize(new Dimension(700, 450));
		value = new JLabel();
		value.setSize(700, 0);
		value.setFont(new Font("宋体",Font.CENTER_BASELINE, 24));
		jp1.add(value);
		jp2 = new JPanel();
		jp2.setPreferredSize(new Dimension(700, 150));
		
		
		add(jp1);
		add(jp2);
		nextText();
		setVisible(true);
		
		jp1.setBackground(new java.awt.Color(0, 0, 0,0));
		jp2.setBackground(new java.awt.Color(0, 0, 0,0));
		setBg();
//		Graphics g = this.getGraphics();
//		bg(g);
	}

	public static void alert(Object message) {
		JOptionPane.showMessageDialog(null,message,"alert",JOptionPane.PLAIN_MESSAGE);
	}

	public static void err(Object message) {
		JOptionPane.showMessageDialog(null,message,"err",JOptionPane.PLAIN_MESSAGE);
	}

	void JlabelSetText(JLabel jLabel, String longString) 
			throws InterruptedException {
		StringBuilder builder = new StringBuilder("<html>");
		char[] chars = longString.toCharArray();
		FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
		int start = 0;
		int len = 0;
		while (start + len < longString.length()) {
			while (true) {
				len++;
				if (start + len > longString.length())break;
				if (fontMetrics.charsWidth(chars, start, len) 
						> jLabel.getWidth()) {
					break;
				}
			}
			builder.append(chars, start, len-1).append("<br/>");
			start = start + len - 1;
			len = 0;
		}
		builder.append(chars, start, longString.length()-start);
		builder.append("</html>");
		jLabel.setText(builder.toString());
	}
	/**
	 * ��һҳ
	 */
	void nextText() {
		this.repaint();
		try {
			int len = code.value.length - index;
			if(len>400) {
				len = 400;
			}
//			jp1.removeAll();
//			jp1.repaint();
			JlabelSetText(value,new String(code.value,index,len));
			index += len;
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		if(index == this.code.value.length) {
			jp2.removeAll();
			code.button.forEach((a,b)->{
				JButton button = new JButton(a);
				button.setFocusPainted(false);
				jp2.add(button);
				button.addActionListener((e)->{
					this.setVisible(false);
					try {
						new MainJframe(DomXML.nextCode((b)));
					} catch (Exception e1) {
						alert("file not find");
						System.exit(0);
					}
				});
			});
		}else {
			JButton button = new JButton("下一页");
			button.setFocusPainted(false);
			button.addActionListener((e)->{
				nextText();
			});
			jp2.removeAll();
			jp2.add(button);
		}
	}

	@Override
	public void update(Graphics g) {
		super.update(g);
	}


	public void setBg(){ 
    	((JPanel)this.getContentPane()).setOpaque(false); 
    	ImageIcon img = Images.getImageIcon(this.code.backGroundImage);
    	JLabel background = new JLabel(img);
    	this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE)); 
    	background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()); 
    }

	
}
