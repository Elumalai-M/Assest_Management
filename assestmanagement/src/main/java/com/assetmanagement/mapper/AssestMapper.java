package com.assetmanagement.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.assetmanagement.dto.Asset;
import com.assetmanagement.dto.AssetData;
import com.assetmanagement.dto.FixedAsset;
import com.assetmanagement.dto.FixedAssetData;
import com.assetmanagement.dto.ITAssetData;
import com.assetmanagement.model.AssetModel;
import com.assetmanagement.model.FixedAssetModel;

@Component
public class AssestMapper {

	public AssetData populateAssetModelToData(AssetModel assetModel) {
		Asset assetData = new Asset();
		AssetData assetDatas = new AssetData();
		if (assetModel.getFixedAsset() != null) {
			FixedAsset fixedAssetData = new FixedAsset();
			BeanUtils.copyProperties(assetModel.getFixedAsset(), fixedAssetData);
			BeanUtils.copyProperties(assetModel, assetData);
			assetDatas.setFixedasset(fixedAssetData);
		} else {
			ITAssetData itAssetData = new ITAssetData();
			BeanUtils.copyProperties(assetModel.getItAsset(), itAssetData);
			BeanUtils.copyProperties(assetModel, assetData);
			assetDatas.setItAsset(itAssetData);
		}
		convertAssetEnum(assetModel, assetData);
		assetDatas.setAsset(assetData);
		return assetDatas;
	}

	private void convertAssetEnum(AssetModel assetModel, Asset assetData) {
		if (assetModel.getStatus() != null) {
			assetData.setStatus(assetModel.getStatus().name());
		}
		if (assetModel.getOperationalStatus() != null) {
			assetData.setOperationalStatus(assetModel.getOperationalStatus().name());
		}
		if (assetModel.getAssetType() != null) {
			assetData.setAssetType(assetModel.getAssetType().name());
		}
		if (assetModel.getCategory() != null) {
			assetData.setCategory(assetModel.getCategory().name());
		}

	}
}
