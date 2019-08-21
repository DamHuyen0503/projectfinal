package com.fpt.projectfinal.daos.contact;

import java.util.List;
import java.util.Map;

import com.fpt.projectfinal.models.Contact;

public interface ContactDao {

	/*
	 * Create new contact. 
	 * 新しい連絡先を作成します。
	 */
	public void addContact(Contact contact);
	
	/*
	 * Get all information contact. 
	 * すべての情報の連絡先を取得します。
	 */
	public Map<String, Object> getAllContact(String sort, String order, int page, int status, String searchString);
	

	/*
	 * Change the status of the contact.
	 * 連絡先のステータスを変更します。
	 */
	public void updateContact(Contact contact);
	
	/*
	 * Get all the information of contact by contactID.
	 * contactIDで連絡先のすべての情報を取得します。
	 */
	public Contact getContactByID(int contactID);
	
	/*
	 * Get all the information of contact by status.
	 * ステータスごとに連絡先のすべての情報を取得します。
	 */
	public List<Contact> getContactByStatus(int status);
	
	
	public List<Contact> getAll();
}
