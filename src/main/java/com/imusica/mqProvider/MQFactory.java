package com.imusica.mqProvider;

import java.util.HashMap;
import java.util.Map;

import com.imusica.mqProvider.rabbitmq.RabbitMQ;
import com.rabbitmq.client.ConnectionFactory;

public class MQFactory
{	
	private static Map<String,Map<String,ConnectionFactory>> connectionFactory;
	
	private static MQFactory instance = null;
		
	private MQFactory() {
		connectionFactory = new HashMap<>();
	}
	
	public synchronized static MQFactory getInstance() 
	{
		if ( instance == null )
			instance = new MQFactory();
		
		return instance;
	}
	
	public synchronized RabbitMQ getRabbitMQProvider()
	{
		return getRabbitMQProvider(MQProvider.DEFAULT_HOST);
	}
	
	public synchronized RabbitMQ getRabbitMQProvider(String host)
	{
		return getRabbitMQProvider(host, MQProvider.DEFAULT_USER, MQProvider.DEFAULT_PASSWORD);
	}
	
	public synchronized RabbitMQ getRabbitMQProvider(String host, String username, String password)
	{
		return getRabbitMQProvider(host, username, password, MQProvider.DEFAULT_VHOST);
	}
	
	public synchronized RabbitMQ getRabbitMQProvider(String host, String username, String password, String vhost)
	{
		if ( !connectionFactory.containsKey(host) ) 
		{				
			connectionFactory.put(host, new HashMap<String,ConnectionFactory>());		
		}
		
		if ( !connectionFactory.get(host).containsKey(vhost) )
		{
			ConnectionFactory cf = new ConnectionFactory();
			
			cf.setRequestedHeartbeat(10);
			
			cf.setHost(host);	
			
			cf.setUsername(username);
			
			cf.setPassword(password);
			
			cf.setVirtualHost(vhost);
			
			connectionFactory.get(host).put(vhost, cf);
		}
		
		return new RabbitMQ(connectionFactory.get(host).get(vhost));
	}
}
