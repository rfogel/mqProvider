package com.imusica.mqProvider.test.consumer;

import com.imusica.mqProvider.Listener;
import com.imusica.mqProvider.MQFactory;
import com.imusica.mqProvider.MQMessage;
import com.imusica.mqProvider.rabbitmq.RabbitMQ;

public class ConsumerTest
{
	private RabbitMQ mq = MQFactory.getInstance().getRabbitMQProvider();
	
	public void startConsumer(final String queueName, final String consumerId, final boolean autoAck)
	{
		mq.registerListener(consumerId, new Listener() 
		{
			@Override
			public void onMessage(MQMessage message) {
				System.out.println(message.getMessage());
			}
		});
		
		mq.startConsumer(queueName, consumerId, autoAck);
	}
	
	public static void main(String[] args) 
	{
		ConsumerTest consumer = new ConsumerTest();
		consumer.startConsumer("error_streaming", "teste", true);		
	}
}
