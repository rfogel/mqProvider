package com.imusica.mqProvider;

public enum MQExchange
{
	DIRECT("direct"),
	FANOUT("fanout"),
	TOPIC("topic");
	
	String value;
	
	private MQExchange(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
