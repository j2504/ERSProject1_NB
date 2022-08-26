package com.jerry.ers.models;

import java.sql.Timestamp;


public class Reimbursements {
	private int reimId;
	private double amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String desc;
	private Byte reciept;
	private Users author;
	private Users resolver;
	private ReimbursementStatus status_id;
	private ReimbursementType type_id;
	
	public Reimbursements() {
		super();
	}

	public Reimbursements(int reimId, double amount, Timestamp submitted, Timestamp resolved, String desc, Byte reciept,
			Users author, Users resolver, ReimbursementStatus status_id, ReimbursementType type_id) {
		super();
		this.reimId = reimId;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.desc = desc;
		this.reciept = reciept;
		this.author = author;
		this.resolver = resolver;
		this.status_id = status_id;
		this.type_id = type_id;
	}

	public Reimbursements(double amount, Timestamp submitted, Timestamp resolved, String desc, Byte reciept,
			Users author, Users resolver, ReimbursementStatus status_id, ReimbursementType type_id) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.desc = desc;
		this.reciept = reciept;
		this.author = author;
		this.resolver = resolver;
		this.status_id = status_id;
		this.type_id = type_id;
	}

	public int getReimId() {
		return reimId;
	}

	public void setReimId(int reimId) {
		this.reimId = reimId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Byte getReciept() {
		return reciept;
	}

	public void setReciept(Byte reciept) {
		this.reciept = reciept;
	}

	public Users getAuthor() {
		return author;
	}

	public void setAuthor(Users author) {
		this.author = author;
	}

	public Users getResolver() {
		return resolver;
	}

	public void setResolver(Users resolver) {
		this.resolver = resolver;
	}

	public ReimbursementStatus getStatus_id() {
		return status_id;
	}

	public void setStatus_id(ReimbursementStatus status_id) {
		this.status_id = status_id;
	}

	public ReimbursementType getType_id() {
		return type_id;
	}

	public void setType_id(ReimbursementType type_id) {
		this.type_id = type_id;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimId=" + reimId + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", desc=" + desc + ", reciept=" + reciept + ", author=" + author + ", resolver=" + resolver
				+ ", status_id=" + status_id + ", type_id=" + type_id + "]";
	}
}
