package com.citiustech.diagnosis.util;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
@ToString
public class ResponseError {
	
	private String message;
	private int code;
	private LocalDateTime timestamp;

}
