package com.luhua.hundun.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.luhua.hundun.code.Code;
import com.luhua.hundun.util.DomXML;
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
public class BrowJFrame extends JPanel{

	private static final long serialVersionUID = 1L;
	private JFrame main;
	
	public BrowJFrame(Code code,JFrame main) {
		this.main = main;
		Browser browser = new Browser();  
        BrowserView view = new BrowserView(browser); 
	    add(view, BorderLayout.CENTER);
		setPreferredSize(new Dimension(700, 600));

		JPanel java = this;
		setVisible(true);
		
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
		this.main.removeAll();
		this.main.repaint();
		try {
			Code nextCode = DomXML.nextCode((path));
			if(nextCode.code == 1) {
				new BrowJFrame(nextCode);

			}else {
				this.add(new MainJframe(nextCode,this.main));
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
