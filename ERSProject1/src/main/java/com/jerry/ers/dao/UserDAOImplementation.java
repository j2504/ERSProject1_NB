package com.jerry.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.jerry.ers.models.UserRoles;
import com.jerry.ers.models.Users;
import com.jerry.ers.utils.ConnectionUtils;




public class UserDAOImplementation implements UsersDAO {
	
	private static Logger logger = Logger.getLogger(UserDAOImplementation.class);
	
	@Override
	public int createUser(Users user) {
		logger.info("In UserDAOImplementation - createUser() started. Adding user: " + user);
		int newUserId = 0;
		try (Connection conn = ConnectionUtils.getInstance().getConnection()) {
			String sql = "insert into ers_users(user_name, user_password, user_firstname, user_lastname, user_email, user_role) values(?,?,?,?,?,?)";
			PreparedStatement insertUser = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			insertUser.setString(1, user.getUserName());
			insertUser.setString(2, user.getPassword());
			insertUser.setString(3, user.getFirstName());
			insertUser.setString(4, user.getLastName());
			insertUser.setString(5, user.getEmail());
			insertUser.setString(6, user.getRole().getRoleType());
			boolean isSuccess = insertUser.execute();
			logger.info("Successful registration to DB: true for yes/ false for no: " + isSuccess);

			ResultSet rs = insertUser.getGeneratedKeys();
			if (rs.next()) {
				newUserId = rs.getInt(1);
				user.getRole().setRoleId(newUserId);
				String sql2 = "insert into ers_user_role(ers_user_role_id) values(?)";
				PreparedStatement ps = conn.prepareStatement(sql2);
				ps.setInt(1, user.getRole().getRoleId());
				ps.execute();
			}

		} catch (SQLException e) {
			logger.warn("Unable to add new user: " + e);
			e.printStackTrace();
		}
		logger.info("In UserDAOImplementation - createUser() ended. New user id is: " + newUserId);
		return newUserId ;
	}

	@Override
	public Users getUserByUsername(String username) {
		logger.info("In UserDAOImplementation - getUserByUserName() started. Searching username: " + username);

		Users target = new Users();
		try (Connection conn = ConnectionUtils.getInstance().getConnection()) {
			String sql = "select * from ers_users where user_name = ?";
			PreparedStatement getUser = conn.prepareStatement(sql);
			getUser.setString(1, username);
			ResultSet rs = getUser.executeQuery();

			if (rs.next()) {
				target.setUser_id(rs.getInt(1));
				target.setUserName(username);
				target.setPassword(rs.getString(3));
				target.setFirstName(rs.getString(4));
				target.setLastName(rs.getString(5));
				target.setEmail(rs.getString(6));
				target.setRole(new UserRoles(rs.getString(7)));
			}

		} catch (SQLException e) {
			logger.warn("Unable to find user: " + e);
			e.printStackTrace();
		}
		logger.info("In UserDAOImplementation - getUserByUsername() ended. Found user: " + target);
		
		return target ;
	}

	@Override
	public void updateUser(Users user) {
		logger.info("In UserDAOImplementation - updateUser() started. Updated user info: " + user);

		try (Connection conn = ConnectionUtils.getInstance().getConnection()) {
			String sql = "update ers_users set user_name=?, user_password=?, user_firstname=?, user_lastname=?, "
					+ "user_email=?, user_role=? where user_id=?";
			PreparedStatement updateUser = conn.prepareStatement(sql);
			updateUser.setString(1, user.getUserName());
			updateUser.setString(2, user.getPassword());
			updateUser.setString(3, user.getFirstName());
			updateUser.setString(4, user.getLastName());
			updateUser.setString(5, user.getEmail());
			updateUser.setString(6, user.getRole().getRoleType());
			updateUser.setInt(7, user.getUser_id());
			
			boolean isSuccessfull = updateUser.execute();
			logger.info("Successful update to DB: true for yes/ false for no: " + isSuccessfull);

		} catch (SQLException e) {
			logger.warn("Unable to Update user: " + e);
			e.printStackTrace();
		}
		logger.info("In UserDAOImplementation - updateUser() ended.");

	}

	@Override
	public void deleteUser(String username) {
		logger.info("In UserDAOImplementation - deleteUser() started. Deleted user with username: " + username);

		try (Connection conn = ConnectionUtils.getInstance().getConnection()) {
			String sql = "delete from users where username = ?";
			PreparedStatement deleteUser = conn.prepareStatement(sql);
			deleteUser.setString(1, username);
			boolean isSuccessfull = deleteUser.execute();
			logger.info("Successfully  deleted user with the username:"+username+ " from the DB: true for yes/ false for no: " + isSuccessfull);

		} catch (SQLException e) {
			logger.warn("Unable to delete user: " + e);
			e.printStackTrace();
		}
		logger.info("In UserDAOImplementation - deletedUser() ended.");

	}

}
