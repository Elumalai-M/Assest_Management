package com.assestmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.assestmanagement.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assestmanagement.dto.AssestData;
import com.assestmanagement.dto.Asset;
import com.assestmanagement.dto.FixedAssestDataResponse;
import com.assestmanagement.repository.AssestRepository;
import com.assestmanagement.repository.FixedAssestRepository;
import com.assestmanagement.service.AssestService;

@Service
public class AssestServiceImpl implements AssestService {

	@Autowired
	private AssestRepository assestRepository;

	@Autowired
	private FixedAssestRepository fixedAssestRepository;

	@Override
	public void createAssest(AssestData assestData) {
		AssetModel assestModel = new AssetModel();
		FixedAssetModel fixedAssest = new FixedAssetModel();
		convertAssestDataToModel(assestData,assestModel,fixedAssest);
		assestModel = assestRepository.save(assestModel);
		fixedAssest.setAsset(assestModel);
		fixedAssest = fixedAssestRepository.save(fixedAssest);
		System.out.println("Data saved successfully: " + assestData);
	}

	private void convertAssestDataToModel(AssestData assestData,AssetModel assestModel,FixedAssetModel fixedAssest) {
		BeanUtils.copyProperties(assestData.getAssest(), assestModel);
		if (assestData.getAssest().getStatus() != null) {
			assestModel.setStatus(Status.valueOf(assestData.getAssest().getStatus().toUpperCase()));
        }
        if (assestData.getAssest().getOperationalStatus() != null) {
        	assestModel.setOperationalStatus(OperationalStatus.valueOf(assestData.getAssest().getOperationalStatus().toUpperCase()));
        }
        if (assestData.getAssest().getAssetType() != null) {
        	assestModel.setAssetType(AssetType.valueOf(assestData.getAssest().getAssetType().toUpperCase()));
        }
        if (assestData.getAssest().getCategory() != null) {
        	assestModel.setCategory(Category.valueOf(assestData.getAssest().getCategory().toUpperCase()));
        }
		BeanUtils.copyProperties(assestData.getFixedassest(), fixedAssest);		
	}

	@Override
	public List<FixedAssestDataResponse> fetchAssestList() {
		List<FixedAssestDataResponse> fixedAssestData = new ArrayList<>();
		List<FixedAssetModel> fixedAssest = fixedAssestRepository.findAll();
		fixedAssest.stream().forEach(x -> {
			FixedAssestDataResponse populateFixedAssestResponse = populateFixedAssestResponse(x);
			fixedAssestData.add(populateFixedAssestResponse);
		});
		return fixedAssestData;
	}

	@Override
	public FixedAssestDataResponse getAssestById(long assestName) {
		Optional<FixedAssetModel> findById = fixedAssestRepository.findById(assestName);
		FixedAssestDataResponse populateFixedAssestResponse = populateFixedAssestResponse(findById.get());
		return populateFixedAssestResponse;
	}
	
	public FixedAssestDataResponse populateFixedAssestResponse(FixedAssetModel fixedAssest){
		FixedAssestDataResponse fixedAssestDataResponse = new FixedAssestDataResponse();
		Asset assest = new Asset();
		BeanUtils.copyProperties(fixedAssest, fixedAssestDataResponse);
		BeanUtils.copyProperties(fixedAssest.getAsset(), assest);
		if (fixedAssest.getAsset().getStatus() != null) {
			assest.setStatus(fixedAssest.getAsset().getStatus().name());
        }
        if (fixedAssest.getAsset().getOperationalStatus() != null) {
        	assest.setOperationalStatus(fixedAssest.getAsset().getOperationalStatus().name());
        }
        if (fixedAssest.getAsset().getAssetType() != null) {
        	assest.setAssetType(fixedAssest.getAsset().getAssetType().name());
        }
        if (fixedAssest.getAsset().getCategory() != null) {
        	assest.setCategory(fixedAssest.getAsset().getCategory().name());
        }
		fixedAssestDataResponse.setAssest(assest);
		return fixedAssestDataResponse;
		
	}

	@Override
	public void updateAssest(AssestData assestData) {
		Optional<AssetModel> assestModel = assestRepository.findByAssetId(Long.parseLong(assestData.getAssest().getAssetId()));
		Optional<FixedAssetModel> fixedAssest = fixedAssestRepository.findByAsset(assestModel);
		if(assestModel.isPresent() && fixedAssest.isPresent()) {
		convertAssestDataToModel(assestData,assestModel.get(),fixedAssest.get());
		 assestRepository.save(assestModel.get());
		 fixedAssest.get().setAsset(assestModel.get());
		fixedAssestRepository.save(fixedAssest.get());
	}
	}
}
