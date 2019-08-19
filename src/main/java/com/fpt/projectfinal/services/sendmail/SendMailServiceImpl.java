package com.fpt.projectfinal.services.sendmail;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.category.CategoryDao;
import com.fpt.projectfinal.daos.subscriber.SubscriberDao;
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Subscriber;
@Service
public class SendMailServiceImpl implements SendMailService {

	@Autowired
	SubscriberDao subscriberDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@SuppressWarnings("unused")
	@Override
	public String AutoSendMail(int categoryID) throws EmailException, MalformedURLException {
		List<Subscriber> listSubscriber = subscriberDao.listSubscriberByCategory(categoryID);
		String email_from = "huyendt0503@gmail.com";
		String password = "huyen@0503";
		  for (Subscriber sub : listSubscriber) {
	           String htmlEmailTemplate = "<h2>Hello!</h2>"
	                   +"This is Apache Logo <br/>"
	                   +"<img src='proper/commons-email/images/commons-logo.png'/>";            
	           ImageHtmlEmail email = new ImageHtmlEmail();
	           email.setHostName("smtp.googlemail.com");
	           email.setSmtpPort(465);
	           email.setAuthenticator(new DefaultAuthenticator(email_from, password));
	           email.setSSLOnConnect(true);
	           email.setFrom(email_from, "TamLyHocHanhPhuc");
	           email.addTo(sub.getEmail());
	           email.setSubject("TamLyHocHanhPhuc upload new post");
	           URL url = new URL("http://commons.apache.org");
	           email.setDataSourceResolver(new DataSourceUrlResolver(url) );
	           email.setHtmlMsg(htmlEmailTemplate);
	           email.setTextMsg("Your email client does not support HTML messages");
	           email.send();
	           System.out.println("Sent!!");
          }
		  return "successful";
	           
	     
	}

}
