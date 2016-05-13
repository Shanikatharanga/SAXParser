package com.rigor.sax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLReader {

	public static XMLReader reader = new XMLReader();
	
	public static final String OPENNING_TAGS = "<[a-zA-Z](.*?[^?])?>";
	public static final String CLOSSING_TAGS = "<\\s*\\/\\s*\\w\\s*.*?>|<\\s*br\\s*>";
	public static final String GET_ATTRIBUTES = "(\\w+)=(\"[^<>\"]*\"|'[^<>']*'|\\w+)";

	private XMLReader() {
		//Singleton Object
	}

	public static XMLReader getInstance() {
		// Return XMLReader Object
		return reader;
	}

	public String readFile(String filePath){

		String fileContent = "";

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String cLine;

			while ((cLine = br.readLine()) != null) {
				fileContent = fileContent.concat(cLine.trim().concat(" "));
			}
			fileContent = fileContent.replaceAll("<!--[^>]*-->", "");
			fileContent = fileContent.replaceAll("<\\?xml[^>]*?>", "");
			fileContent = fileContent.replaceAll("<!DOCTYPE[^>]*>", "");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}

	public List<String> getTags(String path){
		Pattern p = Pattern.compile("<(.|\\n)*?>");
		Matcher m = p.matcher(XMLReader.getInstance().readFile(path));
		List<String> tags = new ArrayList<String>();

		while (m.find()) {
			tags.add(m.group().split(" ")[0].replaceAll("[\\<\\>]", ""));
		}
		for(int i=0;i<tags.size();i++){
			//tags.set(i, "<"+tags.get(i)+">");
		}
		return tags;
	}
	
	public List<String> getStartTagsWithoutAtt(String path){
		Pattern p1 = Pattern.compile(OPENNING_TAGS);
		Matcher m1 = p1.matcher(reader.readFile(path));
		List<String> startTags = new ArrayList<String>();

		while (m1.find()) {
			startTags.add(m1.group().split(" ")[0]);
		}
		return startTags;
	}
	
	public List<String> getStartTagsWithAtt(String path){
		Pattern p1 = Pattern.compile(OPENNING_TAGS);
		Matcher m1 = p1.matcher(reader.readFile(path));
		List<String> startTags = new ArrayList<String>();

		while (m1.find()) {
			startTags.add(m1.group());
		}
		return startTags;
	}
	
	public List<String> getEndTags(String path){
		Pattern p1 = Pattern.compile(CLOSSING_TAGS);
		Matcher m1 = p1.matcher(reader.readFile(path));
		List<String> startTags = new ArrayList<String>();

		while (m1.find()) {
			startTags.add(m1.group().replaceAll("[\\/]", ""));
		}
		return startTags;
	}
}
