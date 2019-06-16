package com.luhua.hundun.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Propertie {
	/**
	 * �������ļ� ��ȡ����ļ�ֵ����
	 * @param name
	 * @return Map<String,String>
	 * @throws IOException
	 */
	public static String get(String key) throws IOException{
		String path = System.getProperty("user.dir")+"/static/conf/flag.properties";
		Properties pro = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(path);
			pro.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return pro.getProperty(key);
	}
	
}
