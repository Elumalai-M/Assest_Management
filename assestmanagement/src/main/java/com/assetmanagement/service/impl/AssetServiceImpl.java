package com.assetmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.assetmanagement.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assetmanagement.dto.AssetData;
import com.assetmanagement.dto.Asset;
import com.assetmanagement.dto.FixedAssetDataResponse;
import com.assetmanagement.repository.AssetRepository;
import com.assetmanagement.repository.FixedAssetRepository;
import com.assetmanagement.service.AssetService;

@Service
public class AssetServiceImpl implements AssetService {

	@Autowired
	private AssetRepository assetRepository;

	@Autowired
	private FixedAssetRepository fixedAssetRepository;

	@Override
	public void createAsset(AssetData assetData) {
		AssetModel assetModel = new AssetModel();
		FixedAssetModel fixedAsset = new FixedAssetModel();
		convertAssetDataToModel(assetData,assetModel,fixedAsset);
		assetModel = assetRepository.save(assetModel);
		fixedAsset.setAsset(assetModel);
		fixedAsset = fixedAssetRepository.save(fixedAsset);
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
	public List<FixedAssetDataResponse> fetchAssetList() {
		List<FixedAssetDataResponse> fixedAssetData = new ArrayList<>();
		List<FixedAssetModel> fixedAsset = fixedAssetRepository.findAll();
		fixedAsset.stream().forEach(x -> {
			FixedAssetDataResponse populateFixedAssetResponse = populateFixedAssetResponse(x);
			fixedAssetData.add(populateFixedAssetResponse);
		});
		return fixedAssetData;
	}

	@Override
	public FixedAssetDataResponse getAssetById(long assetName) {
		Optional<FixedAssetModel> findById = fixedAssetRepository.findById(assetName);
		FixedAssetDataResponse populateFixedAssetResponse = populateFixedAssetResponse(findById.get());
		return populateFixedAssetResponse;
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
		Optional<FixedAssetModel> fixedAsset = fixedAssetRepository.findByAsset(assetModel);

		if(assetModel.isPresent() && fixedAsset.isPresent()) {
		convertAssetDataToModel(assetData,assetModel.get(),fixedAsset.get());
		 assetRepository.save(assetModel.get());
		 fixedAsset.get().setAsset(assetModel.get());
		fixedAssetRepository.save(fixedAsset.get());
	}
	}
}
