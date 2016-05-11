package com.rigor.sax;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rigor.exceptions.NotValidXMLException;
import com.rigor.exceptions.NotXMLFileException;

public class XMLValidator {
	
	private XMLReader reader = XMLReader.getInstance();
	private static XMLValidator validater = new XMLValidator();
	int val=0;

	private XMLValidator() {

	}

	public static XMLValidator getInstance() {
		// Return XMLReader Object
		return validater;
	}

	public boolean pathValidate(String path) throws NotXMLFileException {

		Matcher matcher = Pattern.compile("([^\\s]+(\\.(?i)(xml))$)").matcher(path);

		if (!matcher.matches()) {
			throw new NotXMLFileException();
		} else {
			return matcher.matches();
		}

	}

	public boolean xmlValidate(String path,ArrayList<String> startTags,ArrayList<String> endTags) throws NotValidXMLException {

		ArrayList<String> startTags1 = new ArrayList<>();
		ArrayList<String> endTags1 = new ArrayList<>();
		
		Pattern p = Pattern.compile("<(.|\\n)*?>");

		for(int i=0;i<startTags.size();i++){
			Matcher m2 = p.matcher(startTags.get(i).toString());
			//System.out.println(m2.group());
			while (m2.find()) {				
				startTags1.add(m2.group().split(" ")[0].replaceAll("[\\<\\>]", ""));
			}
		}
		for(int i=0;i<endTags.size();i++){
			Matcher m2 = p.matcher(endTags.get(i).toString());
			//System.out.println(m2.group());
			while (m2.find()) {				
				endTags1.add(m2.group().split(" ")[0].replaceAll("[\\<\\/>]", ""));
			}
		}
		
		for(int i=1;i<startTags1.size()-1;i++){
				if(endTags1.get(i-1).equalsIgnoreCase(startTags1.get(i))){
					startTags1.remove(i);
					endTags1.remove(i-1);
					//System.out.println(startTags);
				}else{
					//System.out.println(startTags);
				}
		}
		
		List<String> a = reader.getTags(path);
		System.out.println(reader.getTags(path));
		System.out.println(startTags1.size() + " "+ startTags);
		System.out.println(endTags1.size() + " "+ endTags1);
		
		for(int i=0;i<a.size();i++){
			String tag = String.valueOf(a.get(i).charAt(0));
			if(tag.matches("[\\/]")){
				String tag2 = a.get(i).replaceAll("[\\/]", "");
				if(tag2.equalsIgnoreCase(a.get(i - 1))){
					System.out.println("Hureeeeeeeeeee");
				}
			}
		}
//		while (m.find()) {
//			tags.add(m.group().split(" ")[0].replaceAll("[\\<\\>]", ""));
//		}
//
//		do {
//			for (int i = 0; i < tags.size(); i++) {
//				String close_tag = String.valueOf(tags.get(i).charAt(0));
//
//				if (close_tag.matches("[\\/]")) {
//					String tag2 = tags.get(i).replaceAll("[\\/]", "");
//					if (tag2.equalsIgnoreCase(tags.get(i - 1))) {
//						tags.remove(i);
//						tags.remove(i - 1);
//						//System.out.println(tags);
//					}
//				}
//			}
//			//System.out.println(tags);
//			//size--;
//		} while (tags.size()!=0);

		if (0 == 0) {
			return true;
		} else {
			throw new NotValidXMLException();
		}
	}
}
