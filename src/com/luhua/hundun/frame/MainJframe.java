package com.luhua.hundun.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;

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
public class MainJframe extends JPanel{
	private static final long serialVersionUID = 1L;
	private JPanel jp1,jp2;
	private JLabel value;
	private Code code;
	private int index = 0;
	private JFrame main;

	public MainJframe(Code code,JFrame main) {
		this.main = main;
		this.code = code;
		
		setPreferredSize(new Dimension(700, 600));
		setLayout(new FlowLayout());

		jp1 = new JPanel();
		jp1.setPreferredSize(new Dimension(700, 450));
		value = new JLabel();
		value.setSize(700, 450);
		value.setPreferredSize(new Dimension(700, 450));
		value.setFont(new Font("宋体",Font.CENTER_BASELINE, 24));
		value.setForeground(code.color);
		
		
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
		if(code.code == 1) {
			value.setText(new String(code.value).trim());
		}else {
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
		}
		if(index == this.code.value.length||code.code == 1) {
			jp2.removeAll();
			code.button.forEach((a,b)->{
				JButton button = new JButton(a);
				button.setFocusPainted(false);
				jp2.add(button);
				button.addActionListener((e)->{
					this.main.removeAll();
					this.main.repaint();
					try {
						Code nextCode = DomXML.nextCode((b));
						if(nextCode.code == 1) {
							this.main.add(new BrowJFrame(nextCode,this.main));
						}else {
							this.main.add(new MainJframe(nextCode,this.main));
						}
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

	public void setBg(){ 
    	this.setOpaque(false); 
    	ImageIcon img = Images.getImageIcon(this.code.backGroundImage);
    	JLabel background = new JLabel(img);
    	this.add(background, new Integer(Integer.MIN_VALUE)); 
    	background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()); 
    }

	
}
