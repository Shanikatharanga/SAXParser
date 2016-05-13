package com.rigor.sax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.rigor.exceptions.NotValidXMLException;
import com.rigor.exceptions.NotXMLFileException;
import com.rigor.handler.ParserHandler;

public class Parser{

	XMLReader reader;
	XMLValidator validater;
	ArrayList<String> startTags = new ArrayList<String>();
	ArrayList<String> endTags = new ArrayList<String>();

	public void parse(String path, ParserHandler handler) {
		reader = XMLReader.getInstance();
		validater = XMLValidator.getInstance();
		
		try {

			if (validater.pathValidate(path) && validater.xmlValidate(path)) {
				
				for(int i=0;i<reader.getStartTagsWithoutAtt(path).size();i++){
					String tag = reader.getStartTagsWithoutAtt(path).get(i).replaceAll("[\\<\\>]", "");
					String tag1 = reader.getStartTagsWithAtt(path).get(i).replaceAll("[\\<\\>]", "");
					
					handler.startElement(path,tag,(String)Tag_Lable_Name(tag1).get(1));
					
				}
				
				for(int i=0;i<reader.getEndTags(path).size();i++){
					String tag = reader.getEndTags(path).get(i).replaceAll("[\\<\\>]", "");
					handler.endElement(tag);
				}

			}
		} catch (NotXMLFileException e) {
			e.printStackTrace();
			System.out.println("Give the valid XML file path...");
		} catch (NotValidXMLException e) {
			e.printStackTrace();
			System.out.println("XML File is not valid...");
		}

	}

	public Map<String, String> AttributeList(String atts) {

		Map<String, String> hash = new HashMap<String, String>();
		for (int k = 0; k < atts.split("\"").length - 1; k++) {
			if ((k == 0 || (k % 2) == 0)) {
				String Key = atts.split("\"")[k].substring(0, atts.split("\"")[k].length() - 1);
				String Value = atts.split("\"")[k + 1];
				hash.put(Key, Value);
			}
		}
		return hash;
	}

	public ArrayList<String> Tag_Lable_Name(String Tag) {
		ArrayList<String> list = new ArrayList<String>();
		String Label = null;
		String atts = null;
		String AttbList = null;

		Label = Tag.split(" ")[0];

		if (Tag.split(" ").length > 1) {
			atts = Tag.substring(Tag.indexOf(" ")).replaceAll(" ", "");
			AttbList = AttributeList(atts).toString();
		} else if (Tag.split(" ").length == 1) {
			// System.out.println("No attributes found!!!");
		}
		list.add(Label);
		list.add(AttbList);

		return list;
	}

}
