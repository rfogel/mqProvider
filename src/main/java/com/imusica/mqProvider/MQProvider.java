package com.imusica.mqProvider;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public interface MQProvider extends Consumer
{
	Boolean DURABLE = true;
	Boolean NOT_DURABLE = false;
	
	Boolean EXCLUSIVE = true;
	Boolean NOT_EXCLUSIVE = false;
	
	Boolean AUTO_DELETE = true;
	Boolean MANUAL_DELETE = false;
	
	String DEFAULT_HOST = "localhost";
	String DEFAULT_VHOST = "/";
	String DEFAULT_USER = "guest";
	String DEFAULT_PASSWORD = "guest";
	
	Integer NO_ATTEMPT_LIMIT = 0;
	
	void createTempQueue(String queueName) throws MQException;
	void createTempQueue(String queueName, Boolean exclusive) throws MQException;
	void createTempQueue(String queueName, Boolean exclusive, Map<String,Object> arguments) throws MQException;
	String createTempQueue() throws MQException;
	String createTempQueue(Boolean exclusive) throws MQException;
	String createTempQueue(Boolean exclusive, Map<String,Object> arguments) throws MQException;
	void createQueue(String queueName) throws MQException;
	void createQueue(String queueName, Boolean durable) throws MQException;
	void createQueue(String queueName, Boolean durable, Boolean exclusive) throws MQException;
	void createQueue(String queueName, Boolean durable, Boolean exclusive, Boolean autoDelete) throws MQException;
	void createQueue(String queueName, Boolean durable, Boolean exclusive, Boolean autoDelete, Map<String,Object> arguments) throws MQException;
	
	void deleteQueue(String queueName) throws MQException;
	void deleteQueue(String queueName, boolean force) throws MQException;
	
	boolean queueExists(String queueName) throws MQException;
	
	void createExchange(String exchangeName, MQExchange exchange) throws MQException;
	void createExchange(String exchangeName, MQExchange exchange, Boolean durable) throws MQException;
	void createExchange(String exchangeName, MQExchange exchange, Boolean durable, Boolean autoDelete, Map<String,Object> arguments) throws MQException;
	
	void deleteExchange(String exchangeName) throws MQException;
	
	void bindQueue2Exchange(String queueName, String exchangeName) throws MQException;
	void bindQueue2Exchange(String queueName, String exchangeName, String bindingKey) throws MQException;
	
	void unbindQueue2Exchange(String queueName, String exchangeName, String bindingKey) throws MQException;
	
	void bindExchange2Exchange(String exchangeDestination, String exchangeSource) throws MQException;
	void bindExchange2Exchange(String exchangeDestination, String exchangeSource, String bindingKey) throws MQException;
	
	void unbindExchange2Exchange(String exchangeDestination, String exchangeSource, String bindingKey) throws MQException;
	
	void publishMessage(String message, String queueName) throws MQException;
	void publishMessage(String message, String queueName, Boolean durable) throws MQException;
	void publishMessage(String message, String queueName, Boolean durable, MQHeader header) throws MQException;
	void publishMessage(List<String> message, String queueName) throws MQException;
	void publishMessage(List<String> message, String queueName, Boolean durable) throws MQException;
	void publishMessage(List<String> message, String queueName, Boolean durable, MQHeader header) throws MQException;
	
	void publishMessageOnExchange(String message, String exchangeName, String queueName) throws MQException;
	void publishMessageOnExchange(String message, String exchangeName, String queueName, Boolean durable) throws MQException;
	void publishMessageOnExchange(String message, String exchangeName, String queueName, Boolean durable, MQHeader header) throws MQException;
	void publishMessageOnExchange(List<String> message, String exchangeName, String queueName) throws MQException;
	void publishMessageOnExchange(List<String> message, String exchangeName, String queueName, Boolean durable) throws MQException;
	void publishMessageOnExchange(List<String> message, String exchangeName, String queueName, Boolean durable, MQHeader header) throws MQException;
	
	void startBlockingConsumer(String queueName, String consumerId, boolean autoAck) throws MQException;
	void startBlockingConsumer(String queueName, String consumerId, boolean autoAck, Integer maxAttempts) throws MQException;
	void startBlockingConsumer(String queueName, String consumerId, boolean autoAck, Integer maxAttempts, Integer fetchSize) throws MQException;
	
	void startConsumer(String queueName, String consumerId, boolean autoAck) throws MQException;
	void startConsumer(String queueName, String consumerId, boolean autoAck, Integer maxAttempts) throws MQException;
	void startConsumer(String queueName, String consumerId, boolean autoAck, Integer maxAttempts, Integer fetchSize) throws MQException;
	void stopConsumer(String consumerId) throws MQException;
	void stopConsumers() throws MQException;
	
	void closeConnection() throws MQException;
	
	Integer getMessageCount(String queueName) throws MQException;  
	Integer getConsumersCount(String queueName) throws MQException;
	
	Boolean isAlive();
	Boolean try2Connect();
	
	String sendRequestAndGetReply(String message, String target, String targetReply, String correlationId) throws MQException;
	String sendRequestAndGetReply(String message, String target, String targetKey, String targetReply, String targetReplyKey, String correlationId) throws MQException;
	
	void setLogger(Logger logger);
}
