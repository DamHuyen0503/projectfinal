package com.fpt.projectfinal.services.subscriber;

import java.util.Map;

public interface SubscriberService {
	public String addSubscriber(Map<String, Object> payload);
	
	public Long getNumberOfSubscriber();
}
