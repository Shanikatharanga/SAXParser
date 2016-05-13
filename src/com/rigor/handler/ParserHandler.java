package com.rigor.handler;

public interface ParserHandler {
	
	public void startElement(String Uri,String startTag,String Attributes);
	
	public void endElement(String endTag);
	
	public void characters(String tagName, String Content);

}
