package com.assetmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assetmanagement.dto.Asset;
import com.assetmanagement.dto.AssetData;
import com.assetmanagement.dto.FixedAssetDataResponse;
import com.assetmanagement.mapper.AssestMapper;
import com.assetmanagement.model.AssetModel;
import com.assetmanagement.model.AssetType;
import com.assetmanagement.model.Category;
import com.assetmanagement.model.FixedAssetModel;
import com.assetmanagement.model.OperationalStatus;
import com.assetmanagement.model.Status;
import com.assetmanagement.repository.AssetRepository;
import com.assetmanagement.repository.FixedAssetRepository;
import com.assetmanagement.service.AssetService;

@Service
public class AssetServiceImpl implements AssetService {

	@Autowired
     AssetRepository assetRepository;

	@Autowired
	FixedAssetRepository fixedAssetRepository;
	
	@Autowired
	AssestMapper assestMapper;

	@Override
	public void createAsset(AssetData assetData) {
		AssetModel assetModel = new AssetModel();
		FixedAssetModel fixedAsset = new FixedAssetModel();
		convertAssetDataToModel(assetData,assetModel,fixedAsset);
		fixedAsset = fixedAssetRepository.save(fixedAsset);
		assetModel.setFixedAsset(fixedAsset);
		assetModel = assetRepository.save(assetModel);

		System.out.println("Data saved successfully: " + assetData);
	}

	private void convertAssetDataToModel(AssetData assetData,AssetModel assetModel,FixedAssetModel fixedAsset) {
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
		BeanUtils.copyProperties(assetData.getFixedasset(), fixedAsset);		
	}

	@Override
	public List<AssetData> fetchAssetList() {
//		List<FixedAssetDataResponse> fixedAssetData = new ArrayList<>();
//		List<FixedAssetModel> fixedAsset = fixedAssetRepository.findAll();
//		fixedAsset.stream().forEach(x -> {
//			FixedAssetDataResponse populateFixedAssetResponse = populateFixedAssetResponse(x);
//			fixedAssetData.add(populateFixedAssetResponse);
//		});
		List<AssetData> assetDatas = new ArrayList<>();
		List<AssetModel> assetModel = assetRepository.findAll();
		assetModel.stream().forEach( x-> {
			AssetData populateAssetData = assestMapper.populateAssetModelToData(x);
			assetDatas.add(populateAssetData);
		});
		return assetDatas;
	}

	@Override
	public AssetData getAssetById(String assetId) {
		Optional<AssetModel> assestModel = assetRepository.findByAssetId(assetId);
		AssetData populateAssetModelToData = assestMapper.populateAssetModelToData(assestModel.get());
		return populateAssetModelToData;
	}
	
	public FixedAssetDataResponse populateFixedAssetResponse(FixedAssetModel fixedAsset){
		FixedAssetDataResponse fixedAssetDataResponse = new FixedAssetDataResponse();
		Asset asset = new Asset();
		BeanUtils.copyProperties(fixedAsset, fixedAssetDataResponse);
		BeanUtils.copyProperties(fixedAsset.getAsset(), asset);
		if (fixedAsset.getAsset().getStatus() != null) {
			asset.setStatus(fixedAsset.getAsset().getStatus().name());
        }
        if (fixedAsset.getAsset().getOperationalStatus() != null) {
        	asset.setOperationalStatus(fixedAsset.getAsset().getOperationalStatus().name());
        }
        if (fixedAsset.getAsset().getAssetType() != null) {
        	asset.setAssetType(fixedAsset.getAsset().getAssetType().name());
        }
        if (fixedAsset.getAsset().getCategory() != null) {
        	asset.setCategory(fixedAsset.getAsset().getCategory().name());
        }
		fixedAssetDataResponse.setAsset(asset);
		return fixedAssetDataResponse;
		
	}

	@Override
	public void updateAsset(AssetData assetData) {
		Optional<AssetModel> assetModel = assetRepository.findByAssetId(assetData.getAsset().getAssetId());
		if(assetModel.isPresent()) {
		AssetModel populateAssetDataToModel = assestMapper.populateAssetDataToModel(assetData,assetModel.get());
		 assetRepository.save(populateAssetDataToModel);
	}
	}
}
