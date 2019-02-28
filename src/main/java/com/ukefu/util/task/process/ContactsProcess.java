package com.ukefu.util.task.process;

import com.ukefu.webim.service.es.ContactsRepository;
import com.ukefu.webim.web.model.Contacts;

public class ContactsProcess implements JPAProcess{
	
	private ContactsRepository contactsRes ;
	
	public ContactsProcess(ContactsRepository contactsRes){
		this.contactsRes = contactsRes ;
	}

	@Override
	public void process(Object data) {
		contactsRes.save((Contacts)data) ;
	}

	@Override
	public void end() {
		
	}

}
