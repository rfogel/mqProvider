package com.imusica.mqProvider.dto;

import java.util.ArrayList;
import java.util.List;

import com.imusica.mqProvider.rabbitmq.api.Queue;

public final class CategorizedQueues
{
	private String category;
	
	private Boolean status;
	
	private List<Queue> queues;
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Queue> getQueues() {
		return queues;
	}
	
	public Boolean getStatus() {
		return status;
	}

	public void setQueues(List<Queue> queueStatus) {
		this.queues = queueStatus;
	}

	public CategorizedQueues(String category, Boolean status) {
		this.category = category;
		this.status = status;
		queues = new ArrayList<>();
	}
}