package com.platinum.pretengineers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {
	private String classType[][] = new String[9][4];
	private URL groupURL, courseURL;
	private DocumentBuilderFactory dbf; 
	private DocumentBuilder db;
	private Document groupTime, courseTime;
	
	public String[][] getClasses(){
		return classType;
	}
	
	public Parser(){
		Common.getTime();	
		getURL();
		parseXML();
		parseMainDoc();
	}
	
	private void getURL(){
		try{
			groupURL = new URL("http://www.ampelmann.co/pretengineers/eng.xml");
			courseURL = new URL(Common.courseXmlURL);
		}catch(MalformedURLException ex){
				ex.printStackTrace();
		}
	
	}
	
	private void parseXML(){
		
		dbf = DocumentBuilderFactory.newInstance();
		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			groupTime = db.parse(groupURL.openStream());


		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		groupTime.getDocumentElement().normalize();
		
		dbf = DocumentBuilderFactory.newInstance();
		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			courseTime = db.parse(courseURL.openStream());


		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		courseTime.getDocumentElement().normalize();
	}

	private void parseMainDoc(){
		
		Element mainEle = courseTime.getDocumentElement();
		NodeList nl = mainEle.getElementsByTagName("period");
		if(nl != null && nl.getLength() > 0){
			boolean areFree = false;
			for(int i = 0 ; i < nl.getLength();i++) {
				areFree = false;
				//get the employee element
				Element el = (Element)nl.item(i);
				if(el.getAttribute("type") == "lecture"){
					classType[i][0] = "lecture";
					classType[i][1] = getTextValue(el, "module");
					classType[i][2] = getTextValue(el, "name");
					classType[i][3] = getTextValue(el, "location");
				}
				if(el.getAttribute("type") == "tutorial"){
					//getGroupTut();
					areFree = true;
				}
				if(el.getAttribute("type") == "lab"){
					//getGroupLab();
					areFree = true;
				}
				if(el.getAttribute("type") == "free" || areFree){
					classType[i][0] = "Free";
					classType[i][1] = "William wallace appreciation";
					classType[i][2] = "Rebellion";
					classType[i][3] = "Not here";
				}

			}
		}

		
		
		
	}
	
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
}
