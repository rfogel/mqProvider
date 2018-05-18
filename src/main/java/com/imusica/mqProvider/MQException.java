package com.imusica.mqProvider;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

public class MQException extends Exception
{
	private static final long serialVersionUID = -5618232649886278400L;
	
	static final Logger logger = Logger.getLogger(MQException.class);
	
	private String message;

    public String getErrorMsg()
    {
        return message;
    }
    
    public MQException(String message)
    {
    	super(message);
    	this.message = message;
    }
    
    public MQException(Exception ex)
    {
    	super(ex);
    }
    
    public static String getStackTrace(final Throwable throwable) 
    {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}
}
