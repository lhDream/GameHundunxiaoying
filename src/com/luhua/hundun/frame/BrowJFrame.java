package com.luhua.hundun.frame;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;

import com.luhua.hundun.code.Code;
import com.luhua.hundun.util.DomXML;
import com.luhua.hundun.util.Images;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.JSValue;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;


/**
 * 增加了浏览器插件的图形界面
 * @author 	luhua
 * @date	2019年6月14日
 */
public class BrowJFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public BrowJFrame(Code code) {
		super(code.title);
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

		JFrame java = this;
		
		browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                if (event.isMainFrame()) {
                    JSValue window = browser.executeJavaScriptAndReturnValue("window");
                    // 给jswindows对象添加一个扩展的属性
                    window.asObject().setProperty("java", java);
                }
            }
        });
		
		if(code.code == 1) {
			browser.loadHTML(new String(code.value));
		}else if(code.code == 2){
			browser.loadURL(DomXML.getRootURL()+new String(code.value));
		}
	}
	
	public void nextUrl(String path) {
		this.setVisible(false);
		try {
			Code nextCode = DomXML.nextCode((path));
			if(nextCode.code == 1) {
				new BrowJFrame(nextCode);
			}else {
				new MainJframe(nextCode);
			}
		} catch (Exception e1) {
			MainJframe.alert("file not find");
			System.exit(0);
		}
		
	}
	
	public void alert(Object obj) {
		MainJframe.alert(obj);
	}

}
