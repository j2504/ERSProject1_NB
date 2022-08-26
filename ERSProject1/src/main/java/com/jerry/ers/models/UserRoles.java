package com.jerry.ers.models;

public class UserRoles {
	private int roleId;
	private String roleType;
	
	public UserRoles() {
		super();
	}

	public UserRoles(int roleId, String roleType) {
		super();
		this.roleId = roleId;
		this.roleType = roleType;
	}
	

	public UserRoles(String role) {
		super();
		this.roleType = role;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Override
	public String toString() {
		return "UserRoles [roleId=" + roleId + ", role=" + roleType + "]";
	}
}
