package com.assetmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assetmanagement.dto.AssetSummary;
import com.assetmanagement.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	DashboardService dashboardService;

	@RequestMapping(value = "/summary", method = RequestMethod.GET)
	public ResponseEntity<AssetSummary> getAssetSummary() {
		AssetSummary assetSummary = dashboardService.getAssetSummary();
		return ResponseEntity.ok().body(assetSummary);
	}
}
