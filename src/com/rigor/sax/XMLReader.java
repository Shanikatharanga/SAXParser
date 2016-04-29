package com.rigor.sax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.rigor.exceptions.FileNotFoundException;

public class XMLReader {
	
	public static XMLReader reader = new XMLReader();
	
	private XMLReader(){
		
	}
	
	public static XMLReader getInstance(){
		return reader;
	}
	
	public String readFile(String filePath) throws FileNotFoundException{
		
		String fileContent = "";
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
		{
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				fileContent = fileContent.concat(sCurrentLine.trim());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		return fileContent;
	}

}
