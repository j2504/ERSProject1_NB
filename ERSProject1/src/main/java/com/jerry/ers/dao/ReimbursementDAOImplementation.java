package com.jerry.ers.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.jerry.ers.models.Reimbursements;
import com.jerry.ers.utils.ConnectionUtils;

public class ReimbursementDAOImplementation implements ReimbursementDAO{
	private static Logger logger = Logger.getLogger(ReimbursementDAOImplementation.class);
	
	@Override
	public int createReim(Reimbursements reim, int user_id) {
		
		int newReimId = 0;
		logger.info("In ReimbursementDAOImplementation - createReim() started. Adding reimbursement: " + reim);

		try (Connection conn = ConnectionUtils.getInstance().getConnection()) {
			
			String sql = "insert into ers_reimbursement( reimb_amount, reimb_submitted, reimb_description,"
					+ "reimb_status_id, reimb_type_id) values(?,?,?,?,?)";
			PreparedStatement cr = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				cr.setDouble(1, reim.getAmount());
				cr.setTimestamp(2, reim.getSubmitted());
				cr.setString(3, reim.getDesc());
				cr.setInt(4, reim.getStatus_id().getReimbStatusId());
				cr.setInt(5, reim.getType_id().getReimTypeId());
			
				cr.execute();

			ResultSet rs = cr.getGeneratedKeys();
			if (rs.next()) {
				newReimId = rs.getInt(1);
			}
			
			String sql2 = "update ers_reimbursment set reimb_author = ? where reimb_id = ?";
			PreparedStatement ua = conn.prepareStatement(sql);
				ua.setInt(1, user_id);
				ua.setInt(2, newReimId);
			boolean isSuccesfullUpdateReim = ua.execute();
			logger.info("Successfull update owner of account: " +newReimId+" to DB: true for yes/ false for no: " + isSuccesfullUpdateReim);
		
		} catch (SQLException e) {
			logger.warn("Unable to add new reimbursement: " + e);
			e.printStackTrace();
		}
		logger.info("In ReimbursementDAOImplementation - createReim() ended. New reimbursement id is: " + newReimId);
		return newReimId;
	}

	@Override
	public int updateReim(Reimbursements reim, int user_id) {
		int newReimId = 0;
		logger.info("In ReimbursementDAOImplementation - updateReim() started. Updating reimbursement: " + reim);

		try (Connection conn = ConnectionUtils.getInstance().getConnection()) {
			
			String sql = "update ers_reimbursment set reimb_resolver = ?, reimb_resolved = ?,  where reimb_id = ?";
			PreparedStatement ua = conn.prepareStatement(sql);
				ua.setInt(1, user_id);
				ua.setInt(2, newReimId);
			boolean isSuccesfullUpdateReim = ua.execute();
			logger.info("Successfull update owner of account: " +newReimId+" to DB: true for yes/ false for no: " + isSuccesfullUpdateReim);
		
		} catch (SQLException e) {
			logger.warn("Unable to add new reimbursement: " + e);
			e.printStackTrace();
		}
		logger.info("In ReimbursementDAOImplementation - createReim() ended. New reimbursement id is: " + newReimId);
		return newReimId;
	}

	@Override
	public Reimbursements getReimByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursements getAllReim() {
		// TODO Auto-generated method stub
		return null;
	}

}
