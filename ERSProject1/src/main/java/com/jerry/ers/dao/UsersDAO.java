package com.jerry.ers.dao;

import com.jerry.ers.models.Users;

public interface UsersDAO {
	
	public int createUser(Users user);
	
	public Users getUserByUsername(String username);
	
	public void updateUser(Users user);
	
	public void deleteUser(String username);
}
