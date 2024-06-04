package com.assetmanagement.jpa.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public interface AssetTrackerTableDto extends Serializable {

	public Long getId();
	public String getAssetAssignId();
	public String getAssetId();
	public String getAssetname();
	public String getEmployeeId();
	public String getEmployeename();
	public String getAssettype();
	public LocalDate getAssignedDate();
	public LocalDate getReturnDate();
	public String getRemark();
}
