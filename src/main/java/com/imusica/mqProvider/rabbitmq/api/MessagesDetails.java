
package com.imusica.mqProvider.rabbitmq.api;

import java.io.Serializable;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class MessagesDetails implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2269311470188381125L;
	@Expose
    private Double rate;

    /**
     * 
     * @return
     *     The rate
     */
    public Double getRate() {
        return rate;
    }

    /**
     * 
     * @param rate
     *     The rate
     */
    public void setRate(Double rate) {
        this.rate = rate;
    }

}
