package com.fpt.projectfinal.services.mail;

import java.net.MalformedURLException;

import org.apache.commons.mail.EmailException;
import org.springframework.scheduling.annotation.Async;

import com.fpt.projectfinal.models.Post;

public interface MailService {
	@Async
	public void notifySubscriber(Post post) throws EmailException, MalformedURLException;
}
