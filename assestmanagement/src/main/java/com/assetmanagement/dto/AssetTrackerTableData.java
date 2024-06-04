package com.assetmanagement.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AssetTrackerTableData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1241447515816779839L;
	
	private Long id;
	private String assetAssignId;
	private String assetId;
	private String assetName;
	private String employeeId;
	private String employeeName;
	private String assignDate;
	private String returnDate;
	private String remark;

}
