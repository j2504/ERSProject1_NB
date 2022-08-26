package com.jerry.ers.services;

import com.jerry.ers.models.Reimbursements;

public interface ReimbursementService {
	
	public int submitReimTick(Reimbursements reim);
	
	public int processReimTick(Reimbursements reim);
	
	public Reimbursements viewAllReim();
	
	public Reimbursements viewPendingReim(String reim);
}
