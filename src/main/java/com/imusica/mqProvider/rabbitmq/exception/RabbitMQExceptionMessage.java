package com.imusica.mqProvider.rabbitmq.exception;

public interface RabbitMQExceptionMessage
{
	String ERROR_CREATING_CONNECTION = "Erro ao criar uma nova conexao";
	String ERROR_CREATING_CHANNEL = "Erro ao criar um novo canal";
	String ERROR_CREATING_QUEUE = "Erro ao criar uma nova fila";
	String ERROR_ENQUEUING_MESSAGE = "Erro ao enfileirar uma mensagem";
	String ERROR_CLOSING_CONNECTION = "Erro ao fechar conexao";
	String ERROR_STOPPING_CONSUMERS = "Erro ao fechar consumidores";
	String CONSUMER_INTERRUPTED = "Consumidor interrompido";
	String ERROR_CONSUMING_MESSAGE = "Erro ao consumir uma mensagem";
	String ERROR_ON_ACK = "Erro ao enviar uma mensagem de ACK";
	String ERROR_ON_NACK = "Erro ao enviar uma mensagem de NACK";
	String ERROR_CREATING_EXCHANGE = "Erro ao criar um exchange";
	String ERROR_BINDING_QUEUE_2_EXCHANGE = "Erro ao bindar uma fila a um exchange";
	String ERROR_BINDING_EXCHANGE_2_EXCHANGE = "Erro ao bindar um exchange a um exchange";
	String ERROR_DELETE_QUEUE = "Erro ao deletar uma fila";
	String ERROR_DELETE_EXCHANGE = "Erro ao deletar um exchange";
	String ERROR_UNBINDING_QUEUE_2_EXCHANGE = "Erro ao desbindar uma fila ao exchange";
	String ERROR_UNBINDING_EXCHANGE_2_EXCHANGE = "Erro ao desbindar um exchange a um exchange";
	String ERROR_ESTABLISHING_CONNECTION = "Erro ao connectar ao RabbitMQ. O servico parece estar offline. Tentando reconectar em 30 segundos";
	String CONSUMER_CANCELLED_UNEXPECTEDLY = "Consumidor cancelado inesperadamente";
}
