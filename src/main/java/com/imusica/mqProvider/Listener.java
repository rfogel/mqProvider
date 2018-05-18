package com.imusica.mqProvider;

public interface Listener
{
	boolean AUTO_ACK = true;
	boolean MANUAL_ACK = false;
	
	void onMessage(MQMessage message);
}
