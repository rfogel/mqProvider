package com.imusica.mqProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MQHeader
{
	String TIMESTAMP = "timestamp";
	
	Map<String,Object> headers;
	
	public MQHeader() {
		headers = new HashMap<>();
		headers.put(TIMESTAMP, getCurrentTimestamp());
	}
	
	public MQHeader(MQHeader headerToCopy)
	{
		headers = new HashMap<>();
		if ( headerToCopy != null )
			for ( String header : headerToCopy.getValue().keySet() )
				addHeader(header, headerToCopy.getValue().get(header).toString());				
	}
	
	public MQHeader(Map<String,Object> headers) {
		this.headers = headers;			
	}
	
	public MQHeader addHeader(String key, String value) {
		headers.put(key, value);
		return this;
	}
	
	public Map<String,Object> getValue() {
		return headers;
	}
	
	protected String getCurrentTimestamp() {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
	}
}
