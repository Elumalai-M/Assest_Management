package com.assetmanagement.dto;

import java.io.Serializable;

public interface AssetSummary  extends Serializable{
   
	public Long getAssignedFixedAssets(); 
	public Long getUnassignedFixedAssets(); 
	public Long getNotWorkingFixedAssets();  
	public Long getAssignedItAssets();  
	public Long getUnassignedItAssets();  
	public Long getNotWorkingItAssets(); 
	public Long getTotalAssets();  
	public Long getTotalAssignedAssets(); 
	public Long getTotalUnassignedAssets(); 
	public Long getTotalNotWorkingAssets(); 
}
