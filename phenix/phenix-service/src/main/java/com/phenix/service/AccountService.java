package com.phenix.service;

import java.util.List;

import com.phenox.model.Account;

public interface AccountService {

	public void persist(Account account);
	
	public List<Account> findAll();
	
	public Account findByName(String name);
	
	public void flush();
	
}
