package com.imusica.mqProvider;

import java.util.List;
import com.imusica.mqProvider.rabbitmq.api.Queue;

public interface MQProviderAPI
{
	List<Queue> getQueues() throws MQException;	
	List<Queue> getQueuesByVhost(String vhost) throws MQException;
	Queue getQueueByName(String name, String vhost) throws MQException;
}
