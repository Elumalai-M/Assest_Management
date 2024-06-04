package com.assetmanagement.mapper;

import com.assetmanagement.dto.AssetTrackerData;
import com.assetmanagement.dto.AssetTrackerTableData;
import com.assetmanagement.jpa.dto.AssetTrackerTableDto;
import com.assetmanagement.model.AssetTrackerModel;

public interface AssetTrackerMapper {
	
	
	public AssetTrackerTableData populateAssetTrackerTableData(AssetTrackerTableDto assetTrackerTableDto);
	
	public AssetTrackerModel reversePopulateAssetTrac(AssetTrackerData assetTrackerData, AssetTrackerModel assetTrackerModel);
	
	public AssetTrackerData reversepopulateAssetTrackerData(AssetTrackerModel assetTrackerModel);
	
	

}
