package com.fpt.projectfinal.daos.subscriber;

import com.fpt.projectfinal.models.Subscriber;

public interface SubscriberDao {
	public void addSubscriber(Subscriber subscriber);
	
	public Long getNumberOfSubscriber();
}
