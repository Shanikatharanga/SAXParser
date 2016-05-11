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
	List<String> tags = new ArrayList<String>();

	private XMLReader() {

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
				fileContent = fileContent.concat(cLine.trim());
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

		while (m.find()) {
			tags.add(m.group().split(" ")[0].replaceAll("[\\<\\>]", ""));
		}
		for(int i=0;i<tags.size();i++){
			//tags.set(i, "<"+tags.get(i)+">");
		}
		return tags;
	}
}
