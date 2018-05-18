
package com.imusica.mqProvider.rabbitmq.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class BackingQueueStatus implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3063308533120374140L;
	@SerializedName("avg_ack_egress_rate")
    @Expose
    private Double avgAckEgressRate;
    @SerializedName("avg_ack_ingress_rate")
    @Expose
    private Double avgAckIngressRate;
    @SerializedName("avg_egress_rate")
    @Expose
    private Double avgEgressRate;
    @SerializedName("avg_ingress_rate")
    @Expose
    private Double avgIngressRate;
    @Expose
    private List<String> delta = new ArrayList<String>();
    @Expose
    private long len;
    @SerializedName("next_seq_id")
    @Expose
    private long nextSeqId;
    @Expose
    private long q1;
    @Expose
    private long q2;
    @Expose
    private long q3;
    @Expose
    private long q4;
    @SerializedName("target_ram_count")
    @Expose
    private String targetRamCount;

    /**
     * 
     * @return
     *     The avgAckEgressRate
     */
    public Double getAvgAckEgressRate() {
        return avgAckEgressRate;
    }

    /**
     * 
     * @param avgAckEgressRate
     *     The avg_ack_egress_rate
     */
    public void setAvgAckEgressRate(Double avgAckEgressRate) {
        this.avgAckEgressRate = avgAckEgressRate;
    }

    /**
     * 
     * @return
     *     The avgAckIngressRate
     */
    public Double getAvgAckIngressRate() {
        return avgAckIngressRate;
    }

    /**
     * 
     * @param avgAckIngressRate
     *     The avg_ack_ingress_rate
     */
    public void setAvgAckIngressRate(Double avgAckIngressRate) {
        this.avgAckIngressRate = avgAckIngressRate;
    }

    /**
     * 
     * @return
     *     The avgEgressRate
     */
    public Double getAvgEgressRate() {
        return avgEgressRate;
    }

    /**
     * 
     * @param avgEgressRate
     *     The avg_egress_rate
     */
    public void setAvgEgressRate(Double avgEgressRate) {
        this.avgEgressRate = avgEgressRate;
    }

    /**
     * 
     * @return
     *     The avgIngressRate
     */
    public Double getAvgIngressRate() {
        return avgIngressRate;
    }

    /**
     * 
     * @param avgIngressRate
     *     The avg_ingress_rate
     */
    public void setAvgIngressRate(Double avgIngressRate) {
        this.avgIngressRate = avgIngressRate;
    }

    /**
     * 
     * @return
     *     The delta
     */
    public List<String> getDelta() {
        return delta;
    }

    /**
     * 
     * @param delta
     *     The delta
     */
    public void setDelta(List<String> delta) {
        this.delta = delta;
    }

    /**
     * 
     * @return
     *     The len
     */
    public long getLen() {
        return len;
    }

    /**
     * 
     * @param len
     *     The len
     */
    public void setLen(long len) {
        this.len = len;
    }

    /**
     * 
     * @return
     *     The nextSeqId
     */
    public long getNextSeqId() {
        return nextSeqId;
    }

    /**
     * 
     * @param nextSeqId
     *     The next_seq_id
     */
    public void setNextSeqId(long nextSeqId) {
        this.nextSeqId = nextSeqId;
    }

    /**
     * 
     * @return
     *     The q1
     */
    public long getQ1() {
        return q1;
    }

    /**
     * 
     * @param q1
     *     The q1
     */
    public void setQ1(long q1) {
        this.q1 = q1;
    }

    /**
     * 
     * @return
     *     The q2
     */
    public long getQ2() {
        return q2;
    }

    /**
     * 
     * @param q2
     *     The q2
     */
    public void setQ2(long q2) {
        this.q2 = q2;
    }

    /**
     * 
     * @return
     *     The q3
     */
    public long getQ3() {
        return q3;
    }

    /**
     * 
     * @param q3
     *     The q3
     */
    public void setQ3(long q3) {
        this.q3 = q3;
    }

    /**
     * 
     * @return
     *     The q4
     */
    public long getQ4() {
        return q4;
    }

    /**
     * 
     * @param q4
     *     The q4
     */
    public void setQ4(long q4) {
        this.q4 = q4;
    }

    /**
     * 
     * @return
     *     The targetRamCount
     */
    public String getTargetRamCount() {
        return targetRamCount;
    }

    /**
     * 
     * @param targetRamCount
     *     The target_ram_count
     */
    public void setTargetRamCount(String targetRamCount) {
        this.targetRamCount = targetRamCount;
    }

}
