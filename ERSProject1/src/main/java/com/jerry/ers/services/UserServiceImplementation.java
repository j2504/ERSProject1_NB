package com.jerry.ers.services;

import org.apache.log4j.Logger;

import com.jerry.ers.dao.ReimbursementDAOImplementation;
import com.jerry.ers.dao.UserDAOImplementation;
import com.jerry.ers.dao.UsersDAO;
import com.jerry.ers.models.Users;

public class UserServiceImplementation implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImplementation.class);

	private UsersDAO userDao;

	public UserServiceImplementation(UserDAOImplementation userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public boolean login(String username, String pass) {
		logger.info("In UserServiceImplementation - login() started. Credentials: UserName:" + username + "Password: "
				+ pass);

		Users target = userDao.getUserByUsername(username);

		// 3. Check if password and username match in db
		if (username.equalsIgnoreCase(target.getUserName()) && pass.equalsIgnoreCase(target.getPassword())) {
			logger.info("In UserServiceImplementation - login() ended. Credentials match!");
			return true;
		}
		logger.info(
				"In UserServiceImplementation - login() ended. UserName and/or password do not match DB information");
		return false;
	}

	@Override
	public int newAccount(Users user) {
		// 1. Log event start
		logger.info("In UserServiceImplementation - newAccount() started. New user: " + user);
		// 2. make db call
		int id = userDao.createUser(user);
		// 3. log event end
		logger.info("In UserServiceImplementation - newAccount() ended. New user id: " + id);
		// 4. return data in return statement
		return id;
	}

	@Override
	public void updateAccount(Users user) {
		// 1. Log event start
		logger.info("In UserServiceImplementation - UpdateAccount() started. Update user: " + user);
		// 2. make db call
		userDao.updateUser(user);
		// 3. log event end
		logger.info("In UserServiceImplementation - UpdateAccount() ended. Updated user : " + user);
		// 4. return data in return statement
	}
	
	@Override
	public Users getUserByUsername(String username) {
		//1. log event start
		logger.info("In UserServiceImpl - getUserByUsername() started. Username: " + username);
		
		//2. make my DB call
		Users target = userDao.getUserByUsername(username);
		
		//3. log event end
		logger.info("In UserServiceImpl - getUserByUsername() ended. Found user: " + target);
		
		//4. return data in return statement
		return target;
	}
}
