package com.assestmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assestmanagement.dto.AssetSummary;
import com.assestmanagement.repository.AssestRepository;
import com.assestmanagement.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {
	
	@Autowired
	AssestRepository assestRepository;

	@Override
	public AssetSummary getAssestSummary() {
		AssetSummary assetSummary = assestRepository.getAssetSummary();
		return assetSummary;
	}

}
