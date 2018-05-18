package com.imusica.mqProvider;

public interface Consumer
{
	void registerListener(String identity, Listener listener);
	void removeListener(String identity);
	void notifyListener(String identity, MQMessage message); 
	boolean isListenerActive(String identity);
}
