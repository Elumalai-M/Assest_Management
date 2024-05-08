package com.assetmanagement.service;

import java.util.List;

import com.assetmanagement.dto.AssetData;

public interface AssetService {

	void createAsset(AssetData assetData);

	List<AssetData> fetchAssetList();

	AssetData getAssetById(long assetName);

	void updateAsset(AssetData assetData);

}
