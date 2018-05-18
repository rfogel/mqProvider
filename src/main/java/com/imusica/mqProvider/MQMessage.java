package com.imusica.mqProvider;

public interface MQMessage
{
	Boolean PERSISTENT = true;
	Boolean NOT_PERSISTENT = false;
	
	String getMessage();
	String getQueue();
	String getConsumerId();
	
	Long getMessageId();
	
	void doAck() throws MQException;
	void doNack() throws MQException;
	
	MQHeader getHeaders();
}
