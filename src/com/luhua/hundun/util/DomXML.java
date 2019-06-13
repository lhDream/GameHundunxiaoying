package com.luhua.hundun.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.luhua.hundun.code.Code;

/**
 * dom解析
 * @author 	luhua
 * @date	2019年6月13日
 */
public class DomXML {
	
	public static void main(String[] args) throws Exception {
		nextCode("1.xml");
	}
	
	public static Code nextCode(String path) throws Exception{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		String path1 = System.getProperty("user.dir")+"/static"+path;
		Document document = db.parse(path1);
		/**
		 * 标题部分
		 */
		String title = document.getElementsByTagName("title").item(0).getTextContent();
		Code code = new Code();
		code.title = title;
		/**
		 * 文章部分
		 */
		String value = document.getElementsByTagName("text").item(0).getTextContent();
		code.value = value.toCharArray();
		/**
		 * 按钮部分（选项部分）
		 */
		NodeList optionList = document.getElementsByTagName("option");
		Map<String,String> list = new HashMap<String,String>();
		for(int i=0;i<optionList.getLength();i++) {
			Node item = optionList.item(i);
			String content = item.getTextContent();
			String attribute = item.getAttributes().getNamedItem("src").getTextContent();
			list.put(content,attribute);
		}
		code.button = list;
		NodeList problemList = document.getElementsByTagName("problem");
		/**
		 * 问题部分
		 */
		if(problemList != null) {
			ArrayList<String> pList = new ArrayList<String>();
			for(int i=0;i<problemList.getLength();i++) {
				Node item = problemList.item(i);
				pList.add(item.getTextContent());
			}
			code.ask = pList;
		}
		
		String image = document.getElementsByTagName("image").item(0).getTextContent();
		code.backGroundImage = image;
		return code;
	}
    
}
