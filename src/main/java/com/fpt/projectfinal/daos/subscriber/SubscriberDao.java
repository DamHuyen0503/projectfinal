package com.fpt.projectfinal.daos.subscriber;

import java.util.List;

import com.fpt.projectfinal.models.Subscriber;

public interface SubscriberDao {
	
	/*
	 * Create a subscriber.
	 * サブスクライバーを作成します。
	 */
	public void addSubscriber(Subscriber subscriber);
	
	/*
	 * Count the total number of followers.
	 * フォロワーの総数を数えます。
	 */
	public Long getNumberOfSubscriber();
	
	/*
	 * get all information of subscriber. 
	 * 加入者のすべての情報を取得します。
	 */
	public List<Subscriber> getAllSubscriber();
	
	/*
	 * update a subscriber.
	 * サブスクライバーを更新します。
	 */
	public void updateSubscriber(Subscriber subscriber);
	
	/*
	 * get subscriber by ID.
	 * IDでサブスクライバーを取得します。
	 */
	public Subscriber getSubscriberByID (int subscriberID);
	
	/*
	 * get subscriber by category
	 *  カテゴリー別に加入者を取得する
	 */
	public List<Subscriber> listSubscriberByCategory(int categoryID);
}
