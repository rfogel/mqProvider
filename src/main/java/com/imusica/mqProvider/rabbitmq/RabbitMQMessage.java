package com.imusica.mqProvider.rabbitmq;

import com.imusica.mqProvider.MQMessage;
import com.rabbitmq.client.Channel;

public abstract class RabbitMQMessage implements MQMessage
{
	private Channel channel;

	public Channel getChannel() {
		return channel;
	}
	
	public RabbitMQMessage(Channel channel) {
		super();
		this.channel = channel;
	}
}
