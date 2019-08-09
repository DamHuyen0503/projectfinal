package com.fpt.projectfinal.services.subscriber;

import java.util.Map;

public interface SubscriberService {
	public String addSubscriber(Map<String, Object> payload);
	
	public Long getNumberOfSubscriber();
	
	public String updateSubscriber(Map<String, Object> payload);
}
