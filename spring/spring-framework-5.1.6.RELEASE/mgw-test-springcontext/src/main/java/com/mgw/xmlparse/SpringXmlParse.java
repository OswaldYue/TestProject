package com.mgw.xmlparse;

import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 模拟spring的DOM解析xml文件
 * */
public class SpringXmlParse {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

		// 解析xml文件
		// 1、获取InputStream输入流
		InputStream in = new ClassPathResource("xxx.xml").getInputStream();
		// 2、获取DocumentBuilderFactory实例
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 3、获取DocumentBuilder实例
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		// 4、将docBuilder转换为Document
		Document doc = docBuilder.parse(in);
		// 5、获取节点并循环输出节点值
		Element element = doc.getDocumentElement();
		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			//System.out.println(node.getNodeName());
			NamedNodeMap attributes = node.getAttributes();
			if (null != attributes) {
				System.out.println(attributes.getNamedItem("id"));
				System.out.println(attributes.getNamedItem("class"));
			}
		}
	}
}
