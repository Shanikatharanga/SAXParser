package com.rigor.test;

import com.rigor.handler.ParserHandler;
import com.rigor.sax.Parser;

public class TestParser extends Parser{

	public static void main(String args[]) {
		
		TestParser tp = new TestParser();
		
		ParserHandler handler = new ParserHandler(){

			@Override
			public void startElement(String Uri,String startTag, String Attr) {
				System.out.println("End Element :" + startTag);
			}

			@Override
			public void endElement(String endTag) {
				System.out.println("End Element :" + endTag);
			}

			@Override
			public void characters(String tagName, String Content) {

				
				
			}	
			
		};
		
		tp.parse("C:\\Users\\shtharanga\\Desktop\\sample.xml", handler);
		
		
		
	}
	
}
