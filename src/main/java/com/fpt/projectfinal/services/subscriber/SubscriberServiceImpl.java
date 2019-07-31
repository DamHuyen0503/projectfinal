package com.fpt.projectfinal.services.subscriber;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.category.CategoryDao;
import com.fpt.projectfinal.daos.subscriber.SubscriberDao;
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Subscriber;
@Service
public class SubscriberServiceImpl implements SubscriberService{

	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	SubscriberDao subscriberDao;
	
	@SuppressWarnings({ "unused", "unused" })
	@Override
	public String addSubscriber(Map<String, Object> payload) {
		if (payload.get("email") == null) {
			return "email null";
		}
		if (payload.get("categoryID") == null) {
			return "categoryID null";
		}
		if (payload.get("status") == null) {
			return "status null";
		}
		try {
			Category cate = categoryDao.getCategoryByID((int)payload.get("categoryID"));
			Subscriber sub = new Subscriber();
			sub.setCategory(cate);
			sub.setStatus((int)payload.get("status"));
			sub.setSubscriberDate(new Date());
			sub.setEmail((String)payload.get("email"));
			subscriberDao.addSubscriber(sub);
			return "successful";
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return "add fail";
		}
	}

	@Override
	public Long getNumberOfSubscriber() {
		return subscriberDao.getNumberOfSubscriber();
	}

}
