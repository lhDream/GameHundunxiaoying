package com.luhua.hundun.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Propertie {
	/**
	 * 读配置文件 获取所需的键值对列
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
