
package com.imusica.mqProvider.rabbitmq.api;

import java.io.Serializable;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class MessageStats implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7317202601503774691L;
	@SerializedName("deliver_get")
    @Expose
    private long deliverGet;
    @SerializedName("deliver_get_details")
    @Expose
    private DeliverGetDetails deliverGetDetails;
    @SerializedName("deliver_no_ack")
    @Expose
    private long deliverNoAck;
    @SerializedName("deliver_no_ack_details")
    @Expose
    private DeliverNoAckDetails deliverNoAckDetails;
    @Expose
    private long publish;
    @SerializedName("publish_details")
    @Expose
    private PublishDetails publishDetails;

    /**
     * 
     * @return
     *     The deliverGet
     */
    public long getDeliverGet() {
        return deliverGet;
    }

    /**
     * 
     * @param deliverGet
     *     The deliver_get
     */
    public void setDeliverGet(long deliverGet) {
        this.deliverGet = deliverGet;
    }

    /**
     * 
     * @return
     *     The deliverGetDetails
     */
    public DeliverGetDetails getDeliverGetDetails() {
        return deliverGetDetails;
    }

    /**
     * 
     * @param deliverGetDetails
     *     The deliver_get_details
     */
    public void setDeliverGetDetails(DeliverGetDetails deliverGetDetails) {
        this.deliverGetDetails = deliverGetDetails;
    }

    /**
     * 
     * @return
     *     The deliverNoAck
     */
    public long getDeliverNoAck() {
        return deliverNoAck;
    }

    /**
     * 
     * @param deliverNoAck
     *     The deliver_no_ack
     */
    public void setDeliverNoAck(long deliverNoAck) {
        this.deliverNoAck = deliverNoAck;
    }

    /**
     * 
     * @return
     *     The deliverNoAckDetails
     */
    public DeliverNoAckDetails getDeliverNoAckDetails() {
        return deliverNoAckDetails;
    }

    /**
     * 
     * @param deliverNoAckDetails
     *     The deliver_no_ack_details
     */
    public void setDeliverNoAckDetails(DeliverNoAckDetails deliverNoAckDetails) {
        this.deliverNoAckDetails = deliverNoAckDetails;
    }

    /**
     * 
     * @return
     *     The publish
     */
    public long getPublish() {
        return publish;
    }

    /**
     * 
     * @param publish
     *     The publish
     */
    public void setPublish(long publish) {
        this.publish = publish;
    }

    /**
     * 
     * @return
     *     The publishDetails
     */
    public PublishDetails getPublishDetails() {
        return publishDetails;
    }

    /**
     * 
     * @param publishDetails
     *     The publish_details
     */
    public void setPublishDetails(PublishDetails publishDetails) {
        this.publishDetails = publishDetails;
    }

}
