package com.jerry.ers.dao;

import com.jerry.ers.models.Reimbursements;

public interface ReimbursementDAO {
	public int createReim(Reimbursements reim,int user_id);
	public int updateReim(Reimbursements reim,int user_id);
	public Reimbursements getReimByStatus(String status);
	public Reimbursements getAllReim();
}
