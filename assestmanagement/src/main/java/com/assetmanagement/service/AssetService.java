package com.assetmanagement.service;

import java.util.List;

import com.assetmanagement.dto.AssetData;
import com.assetmanagement.dto.FixedAssetDataResponse;

public interface AssetService {

	void createAsset(AssetData assetData);

	List<FixedAssetDataResponse> fetchAssetList();

	FixedAssetDataResponse getAssetById(long assetName);

	void updateAsset(AssetData assetData);

}
