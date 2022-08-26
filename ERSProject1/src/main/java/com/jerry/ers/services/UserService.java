package com.jerry.ers.services;

import com.jerry.ers.models.Users;

public interface UserService {
	
	public boolean login(String username, String pass);
	
	public int newAccount(Users user);
	
	public void updateAccount(Users user);
	
	public Users getUserByUsername(String username);
	
}
