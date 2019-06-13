package com.luhua.hundun.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.io.IOException;

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
			setIconImage(Images.getBufferedImage("/static/images/logo.jpg"));
		} catch (IOException e3) {
			alert("logoͼƬ��ʧ��");
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
		value.setFont(new Font("����",Font.CENTER_BASELINE, 24));
		jp1.add(value);
		jp2 = new JPanel();
		jp2.setPreferredSize(new Dimension(700, 150));
		
		nextText();
		
		this.add(jp1);
		add(jp2);
		setVisible(true);
	}

	public static void alert(Object message) {
		JOptionPane.showMessageDialog(null,message,"ִ�н��",JOptionPane.PLAIN_MESSAGE);
	}

	public static void err(Object message) {
		JOptionPane.showMessageDialog(null,message,"�쳣��ʾ",JOptionPane.PLAIN_MESSAGE);
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
		try {
			int len = code.value.length - index;
			if(len>400) {
				len = 400;
			}
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
						alert("�ļ���ʧ");
						System.exit(0);
					}
				});
			});
		}else {
			JButton button = new JButton("��һҳ");
			button.setFocusPainted(false);
			button.addActionListener((e)->{
				nextText();
			});
			jp2.removeAll();
			jp2.add(button);
		}
	}
}
