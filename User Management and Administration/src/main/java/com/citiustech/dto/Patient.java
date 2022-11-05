package com.citiustech.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Patient {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private long phone;

}