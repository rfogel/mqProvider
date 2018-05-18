
package com.imusica.mqProvider.rabbitmq.api;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlTransient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Queue implements Serializable
{
	private static final long serialVersionUID = 889917168194434043L;
	
	@Expose
    private Arguments arguments;
    @SerializedName("auto_delete")
    @Expose
    private Boolean autoDelete;
    @SerializedName("backing_queue_status")
    @Expose
    private BackingQueueStatus backingQueueStatus;
    @SerializedName("consumer_utilisation")
    @Expose
    private String consumerUtilisation;
    @Expose
    private long consumers;
    @SerializedName("down_slave_nodes")
    @Expose
    private String downSlaveNodes;
    @Expose
    private Boolean durable;
    @SerializedName("exclusive_consumer_tag")
    @Expose
    private String exclusiveConsumerTag;
    @SerializedName("idle_since")
    @Expose
    private String idleSince;
    @Expose
    private long memory;
    
//    @SerializedName("message_bytes")
//    @Expose
    @XmlTransient
    private long messageBytes;
    
//    @SerializedName("message_bytes_persistent")
//    @Expose
    @XmlTransient
    private long messageBytesPersistent;
    
//  @SerializedName("message_bytes_persistent")
//  @Expose
    @XmlTransient
    private long messageBytesRam;
    
//  @SerializedName("message_bytes_persistent")
//  @Expose
    @XmlTransient
    private long messageBytesReady;
    
    @SerializedName("message_bytes_unacknowledged")
    @Expose
    private long messageBytesUnacknowledged;
    @SerializedName("message_stats")
    @Expose
    private MessageStats messageStats;
    @Expose
    private long messages;
    @SerializedName("messages_details")
    @Expose
    private MessagesDetails messagesDetails;
    @SerializedName("messages_persistent")
    @Expose
    private long messagesPersistent;
    @SerializedName("messages_ram")
    @Expose
    private long messagesRam;
    @SerializedName("messages_ready")
    @Expose
    private long messagesReady;
    @SerializedName("messages_ready_details")
    @Expose
    private MessagesReadyDetails messagesReadyDetails;
    @SerializedName("messages_ready_ram")
    @Expose
    private long messagesReadyRam;
    @SerializedName("messages_unacknowledged")
    @Expose
    private long messagesUnacknowledged;
    @SerializedName("messages_unacknowledged_details")
    @Expose
    private MessagesUnacknowledgedDetails messagesUnacknowledgedDetails;
    @SerializedName("messages_unacknowledged_ram")
    @Expose
    private long messagesUnacknowledgedRam;
    @Expose
    private String name;
    @Expose
    private String node;
    @Expose
    private String policy;
    @Expose
    private String state;
    @Expose
    private String vhost;
    
    private String serviceName;
    private String serviceName4Web;
    private Boolean canBeIncreased;
    private long errorMessages;
    private Boolean autoManaged;

    /**
     * 
     * @return
     *     The arguments
     */
    public Arguments getArguments() {
        return arguments;
    }

    /**
     * 
     * @param arguments
     *     The arguments
     */
    public void setArguments(Arguments arguments) {
        this.arguments = arguments;
    }

    /**
     * 
     * @return
     *     The autoDelete
     */
    public Boolean getAutoDelete() {
        return autoDelete;
    }

    /**
     * 
     * @param autoDelete
     *     The auto_delete
     */
    public void setAutoDelete(Boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    /**
     * 
     * @return
     *     The backingQueueStatus
     */
    public BackingQueueStatus getBackingQueueStatus() {
        return backingQueueStatus;
    }

    /**
     * 
     * @param backingQueueStatus
     *     The backing_queue_status
     */
    public void setBackingQueueStatus(BackingQueueStatus backingQueueStatus) {
        this.backingQueueStatus = backingQueueStatus;
    }

    /**
     * 
     * @return
     *     The consumerUtilisation
     */
    public String getConsumerUtilisation() {
        return consumerUtilisation;
    }

    /**
     * 
     * @param consumerUtilisation
     *     The consumer_utilisation
     */
    public void setConsumerUtilisation(String consumerUtilisation) {
        this.consumerUtilisation = consumerUtilisation;
    }

    /**
     * 
     * @return
     *     The consumers
     */
    public long getConsumers() {
        return consumers;
    }

    /**
     * 
     * @param consumers
     *     The consumers
     */
    public void setConsumers(Integer consumers) {
        this.consumers = consumers;
    }

    /**
     * 
     * @return
     *     The downSlaveNodes
     */
    public String getDownSlaveNodes() {
        return downSlaveNodes;
    }

    /**
     * 
     * @param downSlaveNodes
     *     The down_slave_nodes
     */
    public void setDownSlaveNodes(String downSlaveNodes) {
        this.downSlaveNodes = downSlaveNodes;
    }

    /**
     * 
     * @return
     *     The durable
     */
    public Boolean getDurable() {
        return durable;
    }

    /**
     * 
     * @param durable
     *     The durable
     */
    public void setDurable(Boolean durable) {
        this.durable = durable;
    }

    /**
     * 
     * @return
     *     The exclusiveConsumerTag
     */
    public String getExclusiveConsumerTag() {
        return exclusiveConsumerTag;
    }

    /**
     * 
     * @param exclusiveConsumerTag
     *     The exclusive_consumer_tag
     */
    public void setExclusiveConsumerTag(String exclusiveConsumerTag) {
        this.exclusiveConsumerTag = exclusiveConsumerTag;
    }

    /**
     * 
     * @return
     *     The idleSince
     */
    public String getIdleSince() {
        return idleSince;
    }

    /**
     * 
     * @param idleSince
     *     The idle_since
     */
    public void setIdleSince(String idleSince) {
        this.idleSince = idleSince;
    }

    /**
     * 
     * @return
     *     The memory
     */
    public long getMemory() {
        return memory;
    }

    /**
     * 
     * @param memory
     *     The memory
     */
    public void setMemory(long memory) {
        this.memory = memory;
    }

    /**
     * 
     * @return
     *     The messageBytes
     */
    public long getMessageBytes() {
        return messageBytes;
    }

    /**
     * 
     * @param messageBytes
     *     The message_bytes
     */
    public void setMessageBytes(long messageBytes) {
        this.messageBytes = messageBytes;
    }

    /**
     * 
     * @return
     *     The messageBytesPersistent
     */
    public long getMessageBytesPersistent() {
        return messageBytesPersistent;
    }

    /**
     * 
     * @param messageBytesPersistent
     *     The message_bytes_persistent
     */
    public void setMessageBytesPersistent(long messageBytesPersistent) {
        this.messageBytesPersistent = messageBytesPersistent;
    }

    /**
     * 
     * @return
     *     The messageBytesRam
     */
    public long getMessageBytesRam() {
        return messageBytesRam;
    }

    /**
     * 
     * @param messageBytesRam
     *     The message_bytes_ram
     */
    public void setMessageBytesRam(long messageBytesRam) {
        this.messageBytesRam = messageBytesRam;
    }

    /**
     * 
     * @return
     *     The messageBytesReady
     */
    public long getMessageBytesReady() {
        return messageBytesReady;
    }

    /**
     * 
     * @param messageBytesReady
     *     The message_bytes_ready
     */
    public void setMessageBytesReady(long messageBytesReady) {
        this.messageBytesReady = messageBytesReady;
    }

    /**
     * 
     * @return
     *     The messageBytesUnacknowledged
     */
    public long getMessageBytesUnacknowledged() {
        return messageBytesUnacknowledged;
    }

    /**
     * 
     * @param messageBytesUnacknowledged
     *     The message_bytes_unacknowledged
     */
    public void setMessageBytesUnacknowledged(long messageBytesUnacknowledged) {
        this.messageBytesUnacknowledged = messageBytesUnacknowledged;
    }

    /**
     * 
     * @return
     *     The messageStats
     */
    public MessageStats getMessageStats() {
        return messageStats;
    }

    /**
     * 
     * @param messageStats
     *     The message_stats
     */
    public void setMessageStats(MessageStats messageStats) {
        this.messageStats = messageStats;
    }

    /**
     * 
     * @return
     *     The messages
     */
    public long getMessages() {
        return messages;
    }

    /**
     * 
     * @param messages
     *     The messages
     */
    public void setMessages(long messages) {
        this.messages = messages;
    }

    /**
     * 
     * @return
     *     The messagesDetails
     */
    public MessagesDetails getMessagesDetails() {
        return messagesDetails;
    }

    /**
     * 
     * @param messagesDetails
     *     The messages_details
     */
    public void setMessagesDetails(MessagesDetails messagesDetails) {
        this.messagesDetails = messagesDetails;
    }

    /**
     * 
     * @return
     *     The messagesPersistent
     */
    public long getMessagesPersistent() {
        return messagesPersistent;
    }

    /**
     * 
     * @param messagesPersistent
     *     The messages_persistent
     */
    public void setMessagesPersistent(long messagesPersistent) {
        this.messagesPersistent = messagesPersistent;
    }

    /**
     * 
     * @return
     *     The messagesRam
     */
    public long getMessagesRam() {
        return messagesRam;
    }

    /**
     * 
     * @param messagesRam
     *     The messages_ram
     */
    public void setMessagesRam(long messagesRam) {
        this.messagesRam = messagesRam;
    }

    /**
     * 
     * @return
     *     The messagesReady
     */
    public long getMessagesReady() {
        return messagesReady;
    }

    /**
     * 
     * @param messagesReady
     *     The messages_ready
     */
    public void setMessagesReady(long messagesReady) {
        this.messagesReady = messagesReady;
    }

    /**
     * 
     * @return
     *     The messagesReadyDetails
     */
    public MessagesReadyDetails getMessagesReadyDetails() {
        return messagesReadyDetails;
    }

    /**
     * 
     * @param messagesReadyDetails
     *     The messages_ready_details
     */
    public void setMessagesReadyDetails(MessagesReadyDetails messagesReadyDetails) {
        this.messagesReadyDetails = messagesReadyDetails;
    }

    /**
     * 
     * @return
     *     The messagesReadyRam
     */
    public long getMessagesReadyRam() {
        return messagesReadyRam;
    }

    /**
     * 
     * @param messagesReadyRam
     *     The messages_ready_ram
     */
    public void setMessagesReadyRam(long messagesReadyRam) {
        this.messagesReadyRam = messagesReadyRam;
    }

    /**
     * 
     * @return
     *     The messagesUnacknowledged
     */
    public long getMessagesUnacknowledged() {
        return messagesUnacknowledged;
    }

    /**
     * 
     * @param messagesUnacknowledged
     *     The messages_unacknowledged
     */
    public void setMessagesUnacknowledged(long messagesUnacknowledged) {
        this.messagesUnacknowledged = messagesUnacknowledged;
    }

    /**
     * 
     * @return
     *     The messagesUnacknowledgedDetails
     */
    public MessagesUnacknowledgedDetails getMessagesUnacknowledgedDetails() {
        return messagesUnacknowledgedDetails;
    }

    /**
     * 
     * @param messagesUnacknowledgedDetails
     *     The messages_unacknowledged_details
     */
    public void setMessagesUnacknowledgedDetails(MessagesUnacknowledgedDetails messagesUnacknowledgedDetails) {
        this.messagesUnacknowledgedDetails = messagesUnacknowledgedDetails;
    }

    /**
     * 
     * @return
     *     The messagesUnacknowledgedRam
     */
    public long getMessagesUnacknowledgedRam() {
        return messagesUnacknowledgedRam;
    }

    /**
     * 
     * @param messagesUnacknowledgedRam
     *     The messages_unacknowledged_ram
     */
    public void setMessagesUnacknowledgedRam(long messagesUnacknowledgedRam) {
        this.messagesUnacknowledgedRam = messagesUnacknowledgedRam;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public String getQueueName() {
    	return name.split("_")[1];
    }

    /**
     * 
     * @return
     *     The node
     */
    public String getNode() {
        return node;
    }

    /**
     * 
     * @param node
     *     The node
     */
    public void setNode(String node) {
        this.node = node;
    }

    /**
     * 
     * @return
     *     The policy
     */
    public String getPolicy() {
        return policy;
    }

    /**
     * 
     * @param policy
     *     The policy
     */
    public void setPolicy(String policy) {
        this.policy = policy;
    }

    /**
     * 
     * @return
     *     The state
     */
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 
     * @return
     *     The vhost
     */
    public String getVhost() {
        return vhost;
    }

    /**
     * 
     * @param vhost
     *     The vhost
     */
    public void setVhost(String vhost) {
        this.vhost = vhost;
    }

	public Boolean getCanBeIncreased()
	{
		return canBeIncreased;
	}

	public void setCanBeIncreased(Boolean canBeIncreased)
	{
		this.canBeIncreased = canBeIncreased;
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	public String getServiceName4Web()
	{
		return serviceName4Web;
	}

	public void setServiceName4Web(String serviceName4Web)
	{
		this.serviceName4Web = serviceName4Web;
	}

	public long getErrorMessages() 
	{
		return errorMessages;
	}

	public void setErrorMessages(long errorMessages)
	{
		this.errorMessages = errorMessages;
	}

	public Boolean getAutoManaged() {
		return autoManaged;
	}

	public void setAutoManaged(Boolean autoManaged) {
		this.autoManaged = autoManaged;
	}

}
