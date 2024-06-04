package com.assetmanagement.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.assetmanagement.model.AssetModel;
import com.assetmanagement.model.EmployeeModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AssetTrackerData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3894077376419482394L;
	
	private Long id;
	private String assetAssignId;
	private String employeeId;
	private String assetId;
	private String assetName;
	private String employeeName;
	private String assignDate;
	private String returnDate;
	private String remark;
}
