package com.fpt.projectfinal.services.contact;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Contact;

public interface ContactServices {

	/*
	 * Create new contact. 
	 * 新しい連絡先を作成します。
	 */
	public String addContact(Map<String, Object> payload);
	
	/*
	 * Get all information contact. 
	 * すべての情報の連絡先を取得します。
	 */
	public List<Contact> getAllContact(Map<String, Object> payload);
	

	/*
	 * Change the status of the contact.
	 * 連絡先のステータスを変更します。
	 */
	public String updateContact(Map<String, Object> payload);
}
