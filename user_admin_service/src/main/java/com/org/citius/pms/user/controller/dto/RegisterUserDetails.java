package com.org.citius.pms.user.controller.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterUserDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "title", required = true)
	@NotNull(message = "Title cannot be null/empty")
	@Pattern(regexp = "^(Mr.|Mrs.|Dr.|Miss.)", message = "Invalid title, supported title are Mr. or Mrs. or Dr. or Miss.")
	private String title;

	@JsonProperty(value = "countryCode", required = true)
	@NotNull(message = "Country code cannot be null/empty")
	@Pattern(regexp = "^[0-9]+$", message = "Invalid country code, entry does not meet criteria.")
	private String countryCode;

	@JsonProperty(value = "firstName", required = true)
	@NotNull(message = "First name cannot be null/empty")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid First name, entry does not meet criteria")
	@Size(max = 45, message = "First name should be less than 45")
	private String firstName;

	@JsonProperty(value = "lastName", required = true)
	@NotNull(message = "Last name cannot be null/empty")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid Last name, entry does not meet criteria")
	@Size(max = 45, message = "Last name should be less than 45")
	private String lastName;

	@JsonProperty(value = "emailId", required = true)
	@NotNull(message = "Email Id cannot be null/empty")
	@Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", message = "Email Id entry does not meet criteria")
	@Size(max = 50, message = "Email Id should be less than 50")
	private String emailId;

	@JsonProperty(value = "dob", required = true)
	@NotNull(message = "Date-of-Birth cannot be null/empty")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid Date-of-Birth, supported criteria is yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	@JsonProperty(value = "contactNumber", required = true)
	@NotNull(message = "Contact number cannot be null/empty")
	@Pattern(regexp = "^[0-9]+$", message = "Invalid Contact number, entry does not meet criteria")
	@Size(max = 10, message = "Contact number should be equal to or less than 10")
	private Long contactNumber;

	@JsonProperty(value = "userRole", required = true)
	@NotNull(message = "User role cannot be null/empty")
	@Pattern(regexp = "^(ADMIN|PHYSICIAN|NURSE|PATIENT)", message = "Invalid User Role, supported role are ADMIN or PHYSICIAN or NURSE or PATIENT")
	@Size(max = 20, message = "User role should be less than 20")
	private String userRole;

	@JsonProperty(value = "employeeID")
	@Pattern(regexp = "^[0-9a-zA-Z]+$", message = "Invalid employee ID, entry does not meet criteria")
	@Size(max = 20, message = "Employee ID should be less than 20")
	private String employeeID;
	
	//  TODO

	@JsonProperty(value = "password", required = true)
	@NotNull(message = "Password cannot be null/empty")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$", message = "Password entry does not meet criteria, Must contain atlease one Capital, lowercase charcter")
	@Size(min = 8, message = "Password should be greater than 8")
	private String passwrd;

	@JsonProperty(value = "confirmPassword", required = true)
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$", message = "Confirm Password entry does not meet criteria")
	private String confirmPwd;

	@JsonProperty(value = "createdDateTime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDateTime createdDateTime;

	@JsonProperty(value = "createdById")
	private Long createdBy;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public String getPasswrd() {
		return passwrd;
	}

	public void setPasswrd(String passwrd) {
		this.passwrd = passwrd;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

}
