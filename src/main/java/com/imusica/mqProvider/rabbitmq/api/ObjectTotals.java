
package com.imusica.mqProvider.rabbitmq.api;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ObjectTotals {

    @SerializedName("consumers")
    @Expose
    private Long consumers;
    @SerializedName("queues")
    @Expose
    private Long queues;
    @SerializedName("exchanges")
    @Expose
    private Long exchanges;
    @SerializedName("connections")
    @Expose
    private Long connections;
    @SerializedName("channels")
    @Expose
    private Long channels;

    /**
     * 
     * @return
     *     The consumers
     */
    public Long getConsumers() {
        return consumers;
    }

    /**
     * 
     * @param consumers
     *     The consumers
     */
    public void setConsumers(Long consumers) {
        this.consumers = consumers;
    }

    /**
     * 
     * @return
     *     The queues
     */
    public Long getQueues() {
        return queues;
    }

    /**
     * 
     * @param queues
     *     The queues
     */
    public void setQueues(Long queues) {
        this.queues = queues;
    }

    /**
     * 
     * @return
     *     The exchanges
     */
    public Long getExchanges() {
        return exchanges;
    }

    /**
     * 
     * @param exchanges
     *     The exchanges
     */
    public void setExchanges(Long exchanges) {
        this.exchanges = exchanges;
    }

    /**
     * 
     * @return
     *     The connections
     */
    public Long getConnections() {
        return connections;
    }

    /**
     * 
     * @param connections
     *     The connections
     */
    public void setConnections(Long connections) {
        this.connections = connections;
    }

    /**
     * 
     * @return
     *     The channels
     */
    public Long getChannels() {
        return channels;
    }

    /**
     * 
     * @param channels
     *     The channels
     */
    public void setChannels(Long channels) {
        this.channels = channels;
    }

	@Override
	public String toString() {
		return "Overview [consumers=" + consumers + ", queues=" + queues + ", exchanges=" + exchanges + ", connections=" + connections + ", channels=" + channels + "]";
	}

}
