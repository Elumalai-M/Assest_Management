package com.assestmanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeData {
	private String employeeId;
	private String firstName;
	private String lastName;	
	private String emailId;
	@JsonIgnore
	private String password;
	private Date dateOfJoining;
	private String contactNumber;
	private Date dateOfBirth;
	private String designation;
	@JsonIgnore
	private boolean isDisabled;

	
}
