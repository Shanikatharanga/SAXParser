package com.rigor.handler;

public interface ParserHandler {
	
	public void startElement(String startTag);
	
	public void endElement(String endTag);
	
	public void characters(String tagName, String Content);

}
