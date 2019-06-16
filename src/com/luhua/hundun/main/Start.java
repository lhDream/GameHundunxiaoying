package com.luhua.hundun.main;

import com.luhua.hundun.code.Code;
import com.luhua.hundun.frame.BrowJFrame;
import com.luhua.hundun.frame.MainJframe;
import com.luhua.hundun.frame.StartJFrame;
import com.luhua.hundun.util.DomXML;
import com.luhua.hundun.util.Propertie;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;

import com.teamdev.jxbrowser.chromium.ba;

public class Start {
	
	public static void main(String[] args) {
		String path;
		try {
			path = Propertie.get("flag");
			if(path == null || "".equals(path)) {
				path = "xml/1.xml";
			}
			Code code = DomXML.nextCode(path);
<<<<<<< HEAD
			StartJFrame main = new StartJFrame(code);
			if(code.code >0) {
				main.add(new BrowJFrame(code,main));
			}else {
				main.add(new MainJframe(code,main));
			}
			main.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static {
        try {
            Field e = ba.class.getDeclaredField("e");
            e.setAccessible(true);
            Field f = ba.class.getDeclaredField("f");
            f.setAccessible(true);
            Field modifersField = Field.class.getDeclaredField("modifiers");
            modifersField.setAccessible(true);
            modifersField.setInt(e, e.getModifiers() & ~Modifier.FINAL);
            modifersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
            e.set(null, new BigInteger("1"));
            f.set(null, new BigInteger("1"));
            modifersField.setAccessible(false);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
