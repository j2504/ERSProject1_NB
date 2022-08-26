
package com.jerry.ers.models;

public class ReimbursementType {
	private int reimTypeId;
	private String reimType;
	
	public ReimbursementType() {
		super();
	}

	public ReimbursementType(int reimTypeId, String reimType) {
		super();
		this.reimTypeId = reimTypeId;
		this.reimType = reimType;
	}

	public ReimbursementType(String reimType) {
		super();
		this.reimType = reimType;
	}

	public int getReimTypeId() {
		return reimTypeId;
	}

	public void setReimTypeId(int reimTypeId) {
		this.reimTypeId = reimTypeId;
	}

	public String getReimType() {
		return reimType;
	}

	public void setReimType(String reimType) {
		this.reimType = reimType;
	}

	@Override
	public String toString() {
		return "ReimbursementType [reimTypeId=" + reimTypeId + ", reimType=" + reimType + "]";
	}
}
