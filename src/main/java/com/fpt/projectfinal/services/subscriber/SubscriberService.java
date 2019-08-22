package com.fpt.projectfinal.services.subscriber;

import java.util.Map;

public interface SubscriberService {
	
	/*
	 * Create a subscriber.
	 * サブスクライバーを作成します。
	 */
	public String addSubscriber(Map<String, Object> payload);
	
	/*
	 * Count the total number of followers.
	 * フォロワーの総数を数えます。
	 */
	public Long getNumberOfSubscriber();
	
}
