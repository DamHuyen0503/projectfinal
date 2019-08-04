package com.fpt.projectfinal.daos.subscriber;

import java.util.List;

import com.fpt.projectfinal.models.Subscriber;

public interface SubscriberDao {
	public void addSubscriber(Subscriber subscriber);
	
	public Long getNumberOfSubscriber();
	
	public List<Subscriber> getAllSubscriber();
}
