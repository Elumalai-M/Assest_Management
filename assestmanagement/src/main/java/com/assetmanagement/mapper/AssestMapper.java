package com.assetmanagement.mapper;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assetmanagement.dto.Asset;
import com.assetmanagement.dto.AssetData;
import com.assetmanagement.dto.FixedAsset;
import com.assetmanagement.dto.ITAssetData;
import com.assetmanagement.model.AssetModel;
import com.assetmanagement.model.AssetType;
import com.assetmanagement.model.Category;
import com.assetmanagement.model.ITAssetModel;
import com.assetmanagement.model.OperationalStatus;
import com.assetmanagement.model.Status;
import com.assetmanagement.repository.VendorRepository;

import io.micrometer.common.util.StringUtils;

@Component
public class AssestMapper {
	
	@Autowired
	VendorRepository vendorRepository;
	

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
			assetDatas.setItasset(itAssetData);
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

	public AssetModel populateAssetDataToModel(AssetData assetData,AssetModel assetModel) {
	    if(assetData.getItasset()!=null) {
	    	  BeanUtils.copyProperties(assetData.getItasset(), assetModel.getItAsset());
	        }else {
			BeanUtils.copyProperties(assetData.getFixedasset(), assetModel.getFixedAsset());
	        }
		BeanUtils.copyProperties(assetData.getAsset(), assetModel);
		if (assetData.getAsset().getStatus() != null) {
			assetModel.setStatus(Status.valueOf(assetData.getAsset().getStatus().toUpperCase()));
        }
        if (assetData.getAsset().getOperationalStatus() != null) {
        	assetModel.setOperationalStatus(OperationalStatus.valueOf(assetData.getAsset().getOperationalStatus().toUpperCase()));
        }
        if (assetData.getAsset().getAssetType() != null) {
        	assetModel.setAssetType(AssetType.valueOf(assetData.getAsset().getAssetType().toUpperCase()));
        }
        if (assetData.getAsset().getCategory() != null) {
        	assetModel.setCategory(Category.valueOf(assetData.getAsset().getCategory().toUpperCase()));
        }
		return assetModel;
		
	}
	
	public void convertAssetDataToModel(AssetData assetData,AssetModel assetModel,ITAssetModel itAsset) {
		BeanUtils.copyProperties(assetData.getAsset(), assetModel);
		
			assetModel.setStatus(Status.UNASSIGNED);
        
       
        	assetModel.setOperationalStatus(OperationalStatus.WORKING);
       
        if (assetData.getAsset().getAssetType() != null) {
        	assetModel.setAssetType(AssetType.valueOf(assetData.getAsset().getAssetType().toUpperCase()));
        }
        if (assetData.getAsset().getCategory() != null) {
        	assetModel.setCategory(Category.valueOf(assetData.getAsset().getCategory().toUpperCase()));
        }
		BeanUtils.copyProperties(assetData.getItasset(), itAsset);	
		if(Objects.nonNull(assetData.getAsset().getVendor()) && StringUtils.isNotBlank(assetData.getAsset().getVendor())) {
			assetModel.setVendor(vendorRepository.findByVendorName(assetData.getAsset().getVendor()).get().get(0));
		}
	}
}
