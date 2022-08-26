package com.jerry.ers.services;

import org.apache.log4j.Logger;

import com.jerry.ers.dao.ReimbursementDAO;
import com.jerry.ers.dao.ReimbursementDAOImplementation;
import com.jerry.ers.dao.UserDAOImplementation;
import com.jerry.ers.dao.UsersDAO;
import com.jerry.ers.models.Reimbursements;

public class ReimbursementServiceImplementation implements ReimbursementService{
	private static Logger logger = Logger.getLogger(ReimbursementServiceImplementation.class);
	
	private ReimbursementDAO reimDao;

	public ReimbursementServiceImplementation(ReimbursementDAOImplementation reimDao) {
		super();
		this.reimDao = reimDao;
	}
	@Override
	public int submitReimTick(Reimbursements reim) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int processReimTick(Reimbursements reim) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Reimbursements viewAllReim() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursements viewPendingReim(String reim) {
		// TODO Auto-generated method stub
		return null;
	}

}
