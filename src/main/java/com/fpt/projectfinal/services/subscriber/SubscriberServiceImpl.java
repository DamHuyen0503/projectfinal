package com.fpt.projectfinal.services.subscriber;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	@SuppressWarnings({ "unused", "unused", "unchecked" })
	@Override
	public String addSubscriber(Map<String, Object> payload) {
		
		List<Subscriber> listSubscriber = subscriberDao.getAllSubscriber();
		for (Subscriber sub : listSubscriber) {
			if (sub.getEmail().equals((String)payload.get("email"))) {
				return "duplicate email";
			}
		}
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
			List<Integer> listCategoryID = (List<Integer>) payload.get("categoryID");
			Set<Category> setCategory = new  HashSet<>(); 
			for (int cateID : listCategoryID) {
				Category cate = categoryDao.getCategoryByID(cateID);
				setCategory.add(cate);
			}
			
			Subscriber sub = new Subscriber();
			sub.setCategorys(setCategory);
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
