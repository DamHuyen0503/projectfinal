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
		
		
		try {
			
			if (payload.get("email") == null) {
				return "email null";
			}
			if (payload.get("categoryID") == null) {
				return "categoryID null";
			}
			if (payload.get("status") == null) {
				return "status null";
			}
			if ((int) payload.get("status") <0 || (int) payload.get("status") >4 ) {
				return "status invalid";
			}
			
			List<Integer> listCategoryID = (List<Integer>) payload.get("categoryID");
			Set<Category> setCategory = new  HashSet<>(); 
			for (int cateID : listCategoryID) {
				Category cate = categoryDao.getCategoryByID(cateID);
				setCategory.add(cate);
			}
			
			List<Subscriber> listSubscriber = subscriberDao.getAllSubscriber();
			for (Subscriber sub : listSubscriber) {
				if (sub.getEmail().equals((String)payload.get("email"))) {
//					Subscriber subscriber = subscriberDao.getSubscriberByID((int)payload.get("subscriberID"));
//					List<Category> cate = categoryDao.getCategoryBySubscriber(sub.getSubscriberID());
//					for(Category c : setCategory) {
//						cate.add(c);
//					}
					Set<Category> setCate = new HashSet<>();
					sub.setCategorys(setCategory);
					sub.setStatus((int)payload.get("status"));
					sub.setSubscriberDate(new Date());
					sub.setEmail((String)payload.get("email"));
					subscriberDao.updateSubscriber(sub);
					return "successful";
				}
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
			return e.getMessage();
		}
	}

	@Override
	public Long getNumberOfSubscriber() {
		return subscriberDao.getNumberOfSubscriber();
	}


}
