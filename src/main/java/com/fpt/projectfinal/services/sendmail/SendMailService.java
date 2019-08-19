package com.fpt.projectfinal.services.sendmail;

import java.net.MalformedURLException;

import org.apache.commons.mail.EmailException;

public interface SendMailService {
	
	public String AutoSendMail(int categoryID) throws EmailException, MalformedURLException;
}
