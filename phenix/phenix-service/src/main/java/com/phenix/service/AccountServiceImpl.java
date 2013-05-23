package com.phenix.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.search.Search;
import com.phenox.dao.AccountDao;
import com.phenox.model.Account;

public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDao dao;
	
	public void persist(Account account) {
		
		dao.persist(account);
		
	}

	public List<Account> findAll() {
		
		return dao.findAll();
	}

	public Account findByName(String name) {
		
		Validate.isTrue(StringUtils.isNotBlank(name),"name关键字不能为空");
		return dao.searchUnique(new Search().addFilterEqual("name", name));
	
	}

	public void flush() {
		
		dao.flush();
		
	}

}
