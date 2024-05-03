package com.assetmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assetmanagement.dto.AssetSummary;
import com.assetmanagement.repository.AssetRepository;
import com.assetmanagement.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {
	
	@Autowired
	AssetRepository assetRepository;

	@Override
	public AssetSummary getAssetSummary() {
		AssetSummary assetSummary = assetRepository.getAssetSummary();
		return assetSummary;
	}

}
