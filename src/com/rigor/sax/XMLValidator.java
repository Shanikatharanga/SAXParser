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
		//Singleton Object
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

	public boolean xmlValidate(String path) throws NotValidXMLException {

		List<String> allTags = reader.getTags(path);	//Get all tags from file
		List<String> sTags = new ArrayList<>();   
		
		for(int i=0;i<allTags.size();i++){
			String tag = String.valueOf(allTags.get(i).charAt(0)); 
			if(!tag.matches("[\\/]")){
				sTags.add(allTags.get(i));
			}
			else if(tag.matches("[\\/]")){
				String tag2 = allTags.get(i).replaceAll("[\\/]", "");  //Closing tag 
				if(sTags.get(sTags.size()-1).equals(tag2)){
					sTags.remove(sTags.size()-1);
				}else{
					System.out.println("Not Properly close :"+sTags.get(sTags.size()-1));
					break;
				}
			}else{
				System.out.println("Nothing........");
			}
		}

		if (sTags.size() == 0) {
			return true;
		} else {
			throw new NotValidXMLException();
		}
	}
}
