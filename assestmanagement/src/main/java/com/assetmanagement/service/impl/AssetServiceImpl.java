package com.assetmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.assetmanagement.dto.Asset;
import com.assetmanagement.dto.AssetData;
import com.assetmanagement.dto.FixedAssetDataResponse;
import com.assetmanagement.mapper.AssestMapper;
import com.assetmanagement.model.AssetModel;
import com.assetmanagement.model.AssetType;
import com.assetmanagement.model.Category;
import com.assetmanagement.model.FixedAssetModel;
import com.assetmanagement.model.ITAssetModel;
import com.assetmanagement.model.OperationalStatus;
import com.assetmanagement.model.Status;
import com.assetmanagement.repository.AssetRepository;
import com.assetmanagement.repository.FixedAssetRepository;
import com.assetmanagement.repository.ITAssetRepository;
import com.assetmanagement.repository.VendorRepository;
import com.assetmanagement.service.AssetService;
import com.assetmanagement.utils.CodeGenerator;

import io.micrometer.common.util.StringUtils;

@Service
public class AssetServiceImpl implements AssetService {

	@Autowired
     AssetRepository assetRepository;

	@Autowired
	FixedAssetRepository fixedAssetRepository;
	
	@Autowired
	ITAssetRepository itAssetRepository;
	
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	AssestMapper assestMapper;
	
	@Value("${asset.code.generator.perfix}")
	private String codeGeneratorPrefix;

	@Override
	public void createAsset(AssetData assetData) {
		AssetModel assetModel = new AssetModel();
		if(assetData.getItasset()!=null) {
			ITAssetModel itAsset = new ITAssetModel();
		//	convertAssetDataToModel(assetData,assetModel,itAsset);
			assestMapper.convertAssetDataToModel(assetData, assetModel, itAsset);
			itAssetRepository.save(itAsset);
			assetModel.setItAsset(itAsset);
//		}else {
//		FixedAssetModel fixedAsset = new FixedAssetModel();
//		convertAssetDataToModel(assetData,assetModel,fixedAsset);
//		 fixedAssetRepository.save(fixedAsset);
//			assetModel.setFixedAsset(fixedAsset);
		}
		assetModel.setAssetId(CodeGenerator.customCodeGenerator(codeGeneratorPrefix));
		assetModel = assetRepository.save(assetModel);

		System.out.println("Data saved successfully: " + assetData);
	}

	private void convertAssetDataToModel(AssetData assetData,AssetModel assetModel,ITAssetModel itAsset) {
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
//		if (fixedAsset.getAsset().getStatus() != null) {
//			asset.setStatus(fixedAsset.getAsset().getStatus().name());
//        }
//        if (fixedAsset.getAsset().getOperationalStatus() != null) {
//        	asset.setOperationalStatus(fixedAsset.getAsset().getOperationalStatus().name());
//        }
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
