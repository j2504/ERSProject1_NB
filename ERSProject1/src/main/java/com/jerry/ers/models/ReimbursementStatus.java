package com.jerry.ers.models;

public class ReimbursementStatus {
	private int reimbStatusId;
	private String reimbStatus;
	
	public ReimbursementStatus() {
		super();
	}

	public ReimbursementStatus(int reimbStatusId, String reimbStatus) {
		super();
		this.reimbStatusId = reimbStatusId;
		this.reimbStatus = reimbStatus;
	}

	public ReimbursementStatus(String reimbStatus) {
		super();
		this.reimbStatus = reimbStatus;
	}

	public int getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	@Override
	public String toString() {
		return "ReimbursementStatus [reimbStatusId=" + reimbStatusId + ", reimbStatus=" + reimbStatus + "]";
	}
}
