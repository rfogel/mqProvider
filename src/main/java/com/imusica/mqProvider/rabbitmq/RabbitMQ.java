package com.imusica.mqProvider.rabbitmq;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.imusica.mqProvider.Listener;
import com.imusica.mqProvider.MQException;
import com.imusica.mqProvider.MQExchange;
import com.imusica.mqProvider.MQHeader;
import com.imusica.mqProvider.MQMessage;
import com.imusica.mqProvider.MQProvider;
import com.imusica.mqProvider.rabbitmq.exception.RabbitMQExceptionMessage;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class RabbitMQ implements MQProvider, Serializable
{
	private static final long serialVersionUID = -5776225897167248452L;

	private Logger logger = Logger.getLogger("RabbitMQ");
	private Logger logger4cancel = Logger.getLogger("RabbitMQ-Cancel");

	private Connection connection;
	
	private Map<String,Listener> listeners;
	
	private ExecutorService executor;

	private ConnectionFactory connectionFactory;

	public RabbitMQ(ConnectionFactory connectionFactory)
	{
		this.connectionFactory = connectionFactory;
		
		listeners = new HashMap<String,Listener>();
		
		executor = Executors.newCachedThreadPool();
	}
	
	@Override
	public void setLogger(Logger logger)
	{
		this.logger = logger;
	}

	public synchronized Connection getConnectionInstance() throws MQException
	{
		if ( connection == null || !connection.isOpen() )
		{	
			connection = getConnection();
		}
		
		return connection;
	}
	
	/*
	 * Essa funcao nao deve ser chamada diretamente pois a conexao deve ser unica na maioria dos casos
	 */
	
	private Connection getConnection() throws MQException
	{		
		try {
			return connectionFactory.newConnection();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_CREATING_CONNECTION + ": " + e.getMessage());
		}
	}
	
	public void closeConnection() throws MQException
	{
		try {
			if ( connection.isOpen() )
				connection.close();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_CLOSING_CONNECTION + ": " + e.getMessage());
		} 
	}
	
	public void stopConsumers() throws MQException
	{
		listeners.clear();
		
		try {
			executor.shutdown();
			executor.awaitTermination(30,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new MQException(e);
		}
		
		executor = Executors.newCachedThreadPool();
	}
	
	@Override
	public Boolean isAlive() {
		return ( connection != null && connection.isOpen() );
	}
	
	@Override
	public Boolean try2Connect() {
		try {
			connection = connectionFactory.newConnection();
			return connection.isOpen();
		} catch (IOException e) {
			return false;
		}
	}
	
	@Override
	public void createTempQueue(String queueName) throws MQException {
		createTempQueue(queueName, NOT_EXCLUSIVE);
	}
	
	@Override
	public void createTempQueue(String queueName, Boolean exclusive) throws MQException {				
		createTempQueue(queueName, exclusive, null);
	}
	
	@Override
	public String createTempQueue() throws MQException {				
		return createTempQueue(NOT_EXCLUSIVE, null);
	}
	
	@Override
	public String createTempQueue(Boolean exclusive) throws MQException {				
		return createTempQueue(exclusive, null);
	}
	
	@Override
	public String createTempQueue(Boolean exclusive, Map<String,Object> arguments) throws MQException 
	{				
		String queueName = "";
		
		try {
			Channel channel = getConnectionInstance().createChannel();
			DeclareOk queueDeclare = channel.queueDeclare("", NOT_DURABLE, exclusive, AUTO_DELETE, arguments);
			queueName = queueDeclare.getQueue();
			channel.close();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_CREATING_CHANNEL + ": " + e.getMessage());
		}
		
		return queueName;
	}
	
	@Override
	public void createTempQueue(String queueName, Boolean exclusive, Map<String,Object> arguments) throws MQException
	{				
		try {
			Channel channel = getConnectionInstance().createChannel();
			channel.queueDeclare(queueName, NOT_DURABLE, exclusive, AUTO_DELETE, arguments);
			channel.close();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_CREATING_CHANNEL + ": " + e.getMessage());
		}
	}
	
	@Override
	public void createQueue(String queueName) throws MQException
	{		
		createQueue(queueName, NOT_DURABLE, NOT_EXCLUSIVE);
	}
	
	@Override
	public void createQueue(String queueName, Boolean durable) throws MQException
	{		
		createQueue(queueName, durable, NOT_EXCLUSIVE);
	}
	
	@Override
	public void createQueue(String queueName, Boolean durable, Boolean exclusive) throws MQException
	{		
		createQueue(queueName, durable, exclusive, MANUAL_DELETE);
	}
	
	@Override
	public void createQueue(String queueName, Boolean durable, Boolean exclusive, Boolean autoDelete) throws MQException
	{
		createQueue(queueName, durable, exclusive, autoDelete, null);
	}
	
	@Override
	public void createQueue(String queueName, Boolean durable, Boolean exclusive, Boolean autoDelete, Map<String,Object> arguments) throws MQException
	{		
		try {
			Channel channel = getConnectionInstance().createChannel();
			channel.queueDeclare(queueName, durable, exclusive, autoDelete, arguments);
			channel.close();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_CREATING_QUEUE + ": " + e.getMessage());
		}
	}
	
	@Override
	public void publishMessage(String message, String queueName) throws MQException
	{			
		publishMessage(message, queueName, RabbitMQMessage.NOT_PERSISTENT);	
	}
	
	@Override
	public void publishMessage(String message, String queueName, Boolean persistent) throws MQException
	{			
		publishMessage(message, queueName, persistent, new MQHeader());	
	}
	
	@Override
	public void publishMessage(String message, String queueName, Boolean persistent, MQHeader headers) throws MQException 
	{
		logger.debug(message + " to " + queueName);		
		
		BasicProperties.Builder properties = new BasicProperties.Builder();
		
		properties.deliveryMode((persistent) ? 2 : 1);
		properties.headers(headers.getValue());

		try {
			Channel channel = getConnectionInstance().createChannel();
			channel.basicPublish("", queueName, properties.build(), message.getBytes());
			channel.close();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_ENQUEUING_MESSAGE + ": " + e.getMessage());
		}
	}
	
	@Override
	public void publishMessage(List<String> messages, String queueName) throws MQException
	{			
		publishMessage(messages, queueName, RabbitMQMessage.NOT_PERSISTENT);	
	}
	
	@Override
	public void publishMessage(List<String> messages, String queueName, Boolean persistent) throws MQException
	{
		publishMessage(messages, queueName, persistent, new MQHeader());
	}
	
	@Override
	public void publishMessage(List<String> messages, String queueName, Boolean persistent, MQHeader headers) throws MQException
	{			
		BasicProperties.Builder properties = new BasicProperties.Builder();
		
		properties.deliveryMode((persistent) ? 2 : 1);
		properties.headers(headers.getValue());
		
		try {
			Channel channel = getConnectionInstance().createChannel();
			for ( String msg : messages ) {
				logger.debug(msg + " to " + queueName);
				channel.basicPublish("", queueName, properties.build(), msg.getBytes());
			}
			channel.close();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_ENQUEUING_MESSAGE + ": " + e.getMessage());
		}	
	}
	
	@Override
	public void publishMessageOnExchange(List<String> messages, String exchangeName, String queueName) throws MQException
	{			
		publishMessageOnExchange(messages, exchangeName, queueName, RabbitMQMessage.NOT_PERSISTENT);	
	}
	
	@Override
	public void publishMessageOnExchange(List<String> messages, String exchangeName, String queueName, Boolean persistent) throws MQException
	{
		publishMessageOnExchange(messages, exchangeName, queueName, persistent, new MQHeader());	
	}
	
	@Override
	public void publishMessageOnExchange(List<String> messages, String exchangeName, String queueName, Boolean persistent, MQHeader headers) throws MQException
	{			
		BasicProperties.Builder properties = new BasicProperties.Builder();
		
		properties.deliveryMode((persistent) ? 2 : 1);
		properties.headers(headers.getValue());
		
		try {
			Channel channel = getConnectionInstance().createChannel();
			for ( String msg : messages ) {
				logger.debug(msg + " to " + exchangeName);
				channel.basicPublish(exchangeName, queueName, properties.build(), msg.getBytes());
			}
			channel.close();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_ENQUEUING_MESSAGE + ": " + e.getMessage());
		}	
	}
	
	@Override
	public void publishMessageOnExchange(String message, String exchange, String routingKey) throws MQException 
	{
		publishMessageOnExchange(message, exchange, routingKey, RabbitMQMessage.NOT_PERSISTENT);
	}

	@Override
	public void publishMessageOnExchange(String message, String exchange, String routingKey, Boolean persistent) throws MQException 
	{
		publishMessageOnExchange(message, exchange, routingKey, persistent, new MQHeader());
	}

	@Override
	public void publishMessageOnExchange(String message, String exchange, String routingKey, Boolean persistent, MQHeader headers) throws MQException 
	{
		logger.debug(message + " to " + exchange);		

		BasicProperties.Builder properties = new BasicProperties.Builder();
		
		properties.deliveryMode((persistent) ? 2 : 1);
		properties.headers(headers.getValue());

		try {
			Channel channel = getConnectionInstance().createChannel();
			channel.basicPublish(exchange, exchange + "." + routingKey, properties.build(), message.getBytes());
			channel.close();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_ENQUEUING_MESSAGE + ": " + e.getMessage());
		}	
	}
	
	@Override
	public void startBlockingConsumer(final String queueName, final String consumerId, final boolean autoAck) {
		startBlockingConsumer(queueName, consumerId, autoAck, NO_ATTEMPT_LIMIT);
	}
	
	@Override
	public void startBlockingConsumer(final String queueName, final String consumerId, final boolean autoAck, final Integer maxAttempts) {
		startBlockingConsumer(queueName, consumerId, autoAck, maxAttempts, maxAttempts);
	}
	
	@Override
	public void startBlockingConsumer(final String queueName, final String consumerId, final boolean autoAck, final Integer maxAttempts, final Integer fetchSize) {
		new SimpleConsumer(queueName, consumerId, autoAck, maxAttempts, fetchSize).run();
	}
	
	@Override
	public void startConsumer(final String queueName, final String consumerId, final boolean autoAck) {
		startConsumer(queueName, consumerId, autoAck, NO_ATTEMPT_LIMIT);
	}
	
	@Override
	public void startConsumer(final String queueName, final String consumerId, final boolean autoAck, final Integer maxAttempts) {	
		startConsumer(queueName, consumerId, autoAck, maxAttempts, 1);
	}
	
	@Override
	public void startConsumer(final String queueName, final String consumerId, final boolean autoAck, final Integer maxAttempts, final Integer fetchSize) {	
		executor.submit(new Consumer(queueName, consumerId, autoAck, maxAttempts,fetchSize));
	}
	
	@Override
	public void stopConsumer(String consumerId) throws MQException
	{
		removeListener(consumerId);
	}
	
	@Override
	public boolean isListenerActive(String consumerId)
	{
		return listeners.containsKey(consumerId);
	}
	
	@Override
	public void registerListener(String consumerId, Listener listener)
	{
		logger.info("Registrando consumidor: " + consumerId);
		listeners.put(consumerId,listener);
	}

	@Override
	public void removeListener(String consumerId)
	{
		logger.info("Removendo consumidor: " + consumerId);
		listeners.remove(consumerId);	
	}

	@Override
	public void notifyListener(String consumerId, MQMessage message)
	{
		if ( isListenerActive(consumerId) )
			listeners.get(consumerId).onMessage(message);
	}
	
	/* Esta funcao esta uma bosta, convenhamos. Infelizmente, o processo de request/reply do rabbit é exatamente eh assim, 
	 * passando o correlationId via parametro no corpo do request. Aqui, passamos proceduralmente para simplificar. 
	 * Note que no final, republicamos a mensagem caso nao seja a desejada para que outro o consumidor nao fique sem resposta
	 * */
	
	@Override
	public String sendRequestAndGetReply(String message, String target, String targetReply, String correlationId) throws MQException {
		return sendRequestAndGetReply(message, target, null, targetReply, null, correlationId);
	}
	
	@Override
	public String sendRequestAndGetReply(String message, String target, String targetKey, String targetReply, String targetReplyKey, String correlationId) throws MQException 
	{
		String response = null;
		
		try {	
			
			Channel channel = getConnectionInstance().createChannel();
			
			if ( targetKey == null )		
				publishMessage(message, target, NOT_DURABLE); 
			else
				publishMessageOnExchange(message, target, targetKey, NOT_DURABLE); 
			
			QueueingConsumer consumer = new QueueingConsumer(channel);
			
			channel.basicConsume(targetReply, Listener.AUTO_ACK, consumer);
			
			for ( int preventLoop = 0; preventLoop < 10; preventLoop++ )
			{
				QueueingConsumer.Delivery delivery = consumer.nextDelivery(100);
					
				if ( delivery == null ) continue;
				
				String result = new String(delivery.getBody());
					
				logger.debug(result + " from " + targetReply);
				
				if ( result.contains(correlationId) ) {	
					response = result.split(";")[0];				
					break;
				}
				
				if ( targetReplyKey == null)
					publishMessage(message, targetReply, NOT_DURABLE);
				else
					publishMessageOnExchange(message, targetReply, targetReplyKey, NOT_DURABLE);
			}
				
			channel.close(); /* Importante, senao a fila continua consumindo mesmo sem listener */
			
			return response;
					
		} catch (IOException | ShutdownSignalException | ConsumerCancelledException | MQException | InterruptedException e) {
			logger.error(RabbitMQExceptionMessage.ERROR_CONSUMING_MESSAGE + ": " + e.getMessage());
		}
		
		return response;
	}
	
	@Override
	public void createExchange(String exchangeName, MQExchange exchange) throws MQException {
		createExchange(exchangeName, exchange, NOT_DURABLE);
	}

	@Override
	public void createExchange(String exchangeName, MQExchange exchange, Boolean durable) throws MQException {
		createExchange(exchangeName, exchange, NOT_DURABLE, MANUAL_DELETE, null);
	}
	
	@Override
	public void createExchange(String exchangeName, MQExchange exchange, Boolean durable, Boolean autoDelete, Map<String,Object> arguments) throws MQException
	{
		try {
			Channel channel = getConnectionInstance().createChannel();
			channel.exchangeDeclare(exchangeName, exchange.getValue(), durable, autoDelete, arguments);
			channel.close();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_CREATING_EXCHANGE + ": " + e.getMessage());
		}
	}

	@Override
	public void bindQueue2Exchange(String queueName, String exchange) throws MQException {
		bindQueue2Exchange(queueName, exchange, "#");
	}

	@Override
	public void bindQueue2Exchange(String queueName, String exchangeName, String bindingKey) throws MQException {
		try {
			Channel channel = getConnectionInstance().createChannel();
			channel.queueBind(queueName, exchangeName, bindingKey);
			channel.close();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_BINDING_QUEUE_2_EXCHANGE + ": " + e.getMessage());
		}
	}
	
	@Override
	public void unbindQueue2Exchange(String queueName, String exchangeName, String bindingKey) throws MQException {
		try {
			Channel channel = getConnectionInstance().createChannel();
			channel.exchangeUnbind(queueName, exchangeName, bindingKey);
			channel.close();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_UNBINDING_QUEUE_2_EXCHANGE + ": " + e.getMessage());
		}
	}
	
	@Override
	public void bindExchange2Exchange(String exchangeDestination, String exchangeSource) throws MQException {
		bindQueue2Exchange(exchangeDestination, exchangeSource, "#");
	}

	@Override
	public void bindExchange2Exchange(String exchangeDestination, String exchangeSource, String bindingKey) throws MQException {
		try {
			Channel channel = getConnectionInstance().createChannel();
			channel.exchangeBind(exchangeDestination, exchangeSource, bindingKey);
			channel.close();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_BINDING_EXCHANGE_2_EXCHANGE + ": " + e.getMessage());
		}
	}
	
	@Override
	public void unbindExchange2Exchange(String exchangeDestination, String exchangeSource, String bindingKey) throws MQException {
		try {
			Channel channel = getConnectionInstance().createChannel();
			channel.exchangeUnbind(exchangeDestination, exchangeSource, bindingKey);
			channel.close();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_UNBINDING_EXCHANGE_2_EXCHANGE + ": " + e.getMessage());
		}
	}
	
	@Override
	public void deleteQueue(String queueName) throws MQException {
		deleteQueue(queueName, false);
	}

	@Override
	public void deleteQueue(String queueName, boolean force) throws MQException {
		try {
			Channel channel = getConnectionInstance().createChannel();
			channel.queueDelete(queueName, true, !force);
			channel.close();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_DELETE_QUEUE + ": " + e.getMessage());
		}
	}
	
	@Override
	public boolean queueExists(String queueName) throws MQException {
		Channel channel = null;
		try {
			channel = getConnectionInstance().createChannel();
			channel.queueDeclarePassive(queueName);
			channel.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public void deleteExchange(String exchangeName) throws MQException {
		try {
			Channel channel = getConnectionInstance().createChannel();
			channel.exchangeDelete(exchangeName, true);
			channel.close();
		} catch (IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_DELETE_QUEUE + ": " + e.getMessage());
		}	
	}

	@Override
	public Integer getMessageCount(String queueName) throws MQException 
	{
		Integer messageCount = 0;
		
		try {
			Channel channel = getConnectionInstance().createChannel();
			messageCount = channel.queueDeclarePassive(queueName).getMessageCount();
			channel.close();		
		} catch (MQException | IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_CREATING_QUEUE + ": " + e.getMessage());
		}
		
		return messageCount;
	}
	
	@Override
	public Integer getConsumersCount(String queueName) throws MQException 
	{
		Integer messageCount = 0;
		
		try {
			Channel channel = getConnectionInstance().createChannel();
			messageCount = channel.queueDeclarePassive(queueName).getConsumerCount();
			channel.close();		
		} catch (MQException | IOException e) {
			throw new MQException(RabbitMQExceptionMessage.ERROR_CREATING_QUEUE + ": " + e.getMessage());
		}
		
		return messageCount;
	}
	
	public abstract class GenericConsumer implements Runnable
	{
		protected final String queueName;
		protected final String consumerId; 
		protected final Boolean autoAck; 
		protected final Integer maxAttempts;
		protected final Integer fetchSize;
		
		public GenericConsumer(String queueName, String consumerId, boolean autoAck, Integer maxAttempts, Integer fetchSize) 
		{
			this.queueName = queueName;
			this.consumerId = consumerId;
			this.autoAck = autoAck;
			this.maxAttempts = maxAttempts;
			this.fetchSize = fetchSize;
		}	
	}
	
	public class Consumer extends GenericConsumer
	{		
		public Consumer(String queueName, String consumerId, boolean autoAck, Integer maxAttempts, Integer fetchSize) {
			super(queueName, consumerId, autoAck, maxAttempts,fetchSize);
		}

		@Override
		public void run()
		{		
			Channel channel = null;
			
			try {
				
				channel = getConnectionInstance().createChannel();
				
				QueueingConsumer consumer = new QueueingConsumer(channel) 
				{
					@Override
				    public void handleCancel(String consumerTag) throws IOException 
					{
						logger4cancel.info(String.format("%s: %s", RabbitMQExceptionMessage.CONSUMER_CANCELLED_UNEXPECTEDLY, consumerTag));
				    }
				};
						
				channel.basicQos(fetchSize);

				channel.basicConsume(queueName, autoAck, consumerId, consumer);
				
				Integer attempts = maxAttempts;
				
				boolean mustKeepAlive = (attempts == 0); 
				
				if ( !isListenerActive(consumerId) ) {
					logger.warn(consumerId + " nao esta ativo.");
				}
				
				while ( isListenerActive(consumerId) && (mustKeepAlive || attempts > 0) )
				{
					final QueueingConsumer.Delivery delivery = consumer.nextDelivery(1000);
					
					if ( delivery == null ) continue;
					
					final String message = new String(delivery.getBody());
					
					logger.debug(message + " from " + queueName + " on consumer " + consumerId);
					
					notifyListener(consumerId, new RabbitMQMessage(channel) {
						
						@Override
						public void doAck() throws MQException {
							try {
								getChannel().basicAck(getMessageId(), false);
							} catch (IOException ex) {
								throw new MQException(RabbitMQExceptionMessage.ERROR_ON_ACK + " : " + ex.getMessage());
							}
						}
						
						@Override
						public void doNack() throws MQException {
							try {
								getChannel().basicNack(getMessageId(), false, true);
							} catch (IOException ex) {
								throw new MQException(RabbitMQExceptionMessage.ERROR_ON_NACK + " : " + ex.getMessage());
							}
						}
						
						@Override
						public String getMessage() {
							return message;
						}
						@Override
						public Long getMessageId() {
							return delivery.getEnvelope().getDeliveryTag();
						}
						@Override
						public String getQueue() {
							return queueName;
						}
						@Override
						public String getConsumerId() {
							return consumerId;
						}
						@Override
						public MQHeader getHeaders() {
							return new MQHeader(delivery.getProperties().getHeaders());
						}
					});
					
					if ( attempts > 0 ) 
						attempts--;
				}
						
			} catch (IOException | ConsumerCancelledException ex) {
				logger.error(RabbitMQExceptionMessage.ERROR_CONSUMING_MESSAGE + ": " + consumerId);
				logger.error(MQException.getStackTrace(ex));
			} catch (ShutdownSignalException ex) {
				logger.error(RabbitMQExceptionMessage.ERROR_ESTABLISHING_CONNECTION + ": " + consumerId);
				logger.error(MQException.getStackTrace(ex));
			} catch (InterruptedException ex) {
				logger.error(RabbitMQExceptionMessage.CONSUMER_INTERRUPTED + ": " + consumerId);
				logger.error(MQException.getStackTrace(ex));
			} catch (Exception ex) {
				logger.error(MQException.getStackTrace(ex));
			} finally {				
				try {
					channel.close(); /* Importante, senao a fila continua consumindo mesmo sem listener */
				} catch (IOException e) {
					logger.error(MQException.getStackTrace(e));
				} 				
				if ( isListenerActive(consumerId) )
					removeListener(consumerId);
			}
		}
	}
	
	public class SimpleConsumer extends GenericConsumer
	{	
		public SimpleConsumer(String queueName, String consumerId, boolean autoAck, Integer maxAttempts, Integer fetchSize) {
			super(queueName, consumerId, autoAck, maxAttempts,fetchSize);
		}

		@Override
		public void run()
		{		
			Channel channel = null;
			
			try {
				
				channel = getConnectionInstance().createChannel();
				
				QueueingConsumer consumer = new QueueingConsumer(channel)
				{
					@Override
				    public void handleCancel(String consumerTag) throws IOException 
					{
						logger4cancel.info(String.format("%s: %s", RabbitMQExceptionMessage.CONSUMER_CANCELLED_UNEXPECTEDLY, consumerTag));
				    }
				};
						
				channel.basicQos(fetchSize);
				
				Integer attempts = maxAttempts;
				
				boolean mustKeepAlive = (attempts == 0);

				channel.basicConsume(queueName, autoAck, consumerId, consumer);

				while ( mustKeepAlive || attempts-- > 0 )
				{
					final QueueingConsumer.Delivery delivery = consumer.nextDelivery(1000);
					
					if ( delivery == null ) break;
					
					final String message = new String(delivery.getBody());
					
					logger.debug(message + " from " + queueName + " on consumer " + consumerId);
					
					notifyListener(consumerId, new RabbitMQMessage(channel) {
						
						@Override
						public void doAck() throws MQException {
							try {
								getChannel().basicAck(getMessageId(), false);
							} catch (IOException ex) {
								throw new MQException(RabbitMQExceptionMessage.ERROR_ON_ACK + " : " + ex.getMessage());
							}
						}
						
						@Override
						public void doNack() throws MQException {
							try {
								getChannel().basicNack(getMessageId(), false, true);
							} catch (IOException ex) {
								throw new MQException(RabbitMQExceptionMessage.ERROR_ON_NACK + " : " + ex.getMessage());
							}
						}
						
						@Override
						public String getMessage() {
							return message;
						}
						@Override
						public Long getMessageId() {
							return delivery.getEnvelope().getDeliveryTag();
						}
						@Override
						public String getQueue() {
							return queueName;
						}
						@Override
						public String getConsumerId() {
							return consumerId;
						}
						@Override
						public MQHeader getHeaders() {
							return new MQHeader(delivery.getProperties().getHeaders());
						}
					});
				}					
			} catch (Exception ex) {
				logger.error(MQException.getStackTrace(ex));
			} finally {				
				try {
					channel.close(); /* Importante, senao fila continua consumindo mesmo sem listener */
				} catch (IOException e) {
					logger.error(MQException.getStackTrace(e));
				} 				
				if ( isListenerActive(consumerId) )
					removeListener(consumerId);
			}
		}
	}
}
