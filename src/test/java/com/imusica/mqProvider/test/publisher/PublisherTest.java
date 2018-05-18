package com.imusica.mqProvider.test.publisher;

import com.imusica.mqProvider.MQException;
import com.imusica.mqProvider.MQFactory;
import com.imusica.mqProvider.MQHeader;
import com.imusica.mqProvider.MQMessage;
import com.imusica.mqProvider.rabbitmq.RabbitMQ;

public class PublisherTest
{
	private RabbitMQ mq = MQFactory.getInstance().getRabbitMQProvider();
		
	public void publishMessage(String message, String queueName, Boolean durable, MQHeader header) throws MQException
	{
		mq.publishMessage(message, queueName, durable, header);
	}
	
	public void publishMessageOnExchange(String message, String exchange, String routingKey) throws MQException
	{
		mq.publishMessageOnExchange(message, exchange, routingKey);
	}
	
	public static void main(String[] args) throws MQException 
	{
		PublisherTest publisher = new PublisherTest();
		
		MQHeader header = new MQHeader().addHeader("error", MQException.getStackTrace(new NullPointerException()));
		
		publisher.publishMessage("{XXXXXXXXXXXXXXXXXXXXXXXXXXX}", "error_streaming", MQMessage.PERSISTENT, header);
		
		publisher.publishMessageOnExchange("streaming;123", "service_control", "status");
		
		System.exit(0);
	}
	
}
