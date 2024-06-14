package com.assetmanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeData {
	private Integer id;
	private String employeeId;
	private String firstName;
	private String lastName;	
	private String emailId;
	private String password;
	private Date dateOfJoining;
	private String contactNumber;
	private Date dateOfBirth;
	private String designation;
	private boolean isDisabled;

	
}
