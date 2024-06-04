package com.assetmanagement.service;

import java.util.List;

import com.assetmanagement.dto.AssetTrackerData;
import com.assetmanagement.dto.AssetTrackerTableData;

public interface AssetTrackerService {
	
	List<AssetTrackerTableData> fetchAllAssetTrackerList(String sortby, Integer pageNo, Integer pageSize);
	
	AssetTrackerData assignAsset(AssetTrackerData assetTrackerData);
	
	AssetTrackerData updateassignAsset(AssetTrackerData assetTrackerData);
	
	

}
