package com.luhua.hundun.frame;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;

import com.luhua.hundun.code.Code;
import com.luhua.hundun.util.Images;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;


/**
 * 增加了浏览器插件的图形界面
 * @author 	luhua
 * @date	2019年6月14日
 */
public class BrowJFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private Code code;
	
	public BrowJFrame(Code code) {
		super(code.title);
		this.code = code;
		try {
			setIconImage(Images.getBufferedImage("images/logo.jpg"));
		} catch (IOException e3) {
		}
		Browser browser = new Browser();  
        BrowserView view = new BrowserView(browser); 
	    add(view, BorderLayout.CENTER);
	    
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);		
		
		setVisible(true);
		if(code.code == 1) {
			browser.loadHTML(new String(code.value));
		}else if(code.code == 2){
			browser.loadURL(new String(code.value));
		}
	}

}
