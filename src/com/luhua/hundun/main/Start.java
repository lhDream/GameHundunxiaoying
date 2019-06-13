package com.luhua.hundun.main;

import com.luhua.hundun.code.Code;
import com.luhua.hundun.frame.MainJframe;
import com.luhua.hundun.util.DomXML;
import com.luhua.hundun.util.Propertie;

public class Start {
	
	public static void main(String[] args) {
		String path;
		try {
			path = Propertie.get("flag");
			if(path == null || "".equals(path)) {
				path = "xml/1.xml";
			}
			Code code = DomXML.nextCode(path);
			new MainJframe(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
