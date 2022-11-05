package com.org.citius.pms.user.controller.dto;

public enum PasswordResetType {

	CHANGE("CHANGE"), RESET("RESET");

	String value;

	PasswordResetType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
