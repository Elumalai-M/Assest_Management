package com.assetmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assetmanagement.dto.AssetTrackerData;
import com.assetmanagement.dto.AssetTrackerTableData;
import com.assetmanagement.service.AssetTrackerService;



@RestController
@RequestMapping("assetManagment/api/v1/assetTracker")
public class AssetTrackerController {
	
	
	@Autowired
	private AssetTrackerService assetTrackerService;
	
	
	
	
	@PostMapping("/assignAsset")
	public ResponseEntity<AssetTrackerData> assignAsset(@RequestBody AssetTrackerData assetTrackerData)
	{
		AssetTrackerData assignAsset = assetTrackerService.assignAsset(assetTrackerData);	
		return ResponseEntity.status(HttpStatus.CREATED).body(assignAsset);		
	}
	
	@PutMapping("/updateAssignAsset")
	public ResponseEntity<AssetTrackerData> updateAssignAsset(@RequestBody AssetTrackerData assetTrackerData)
	{
		AssetTrackerData assignAsset = assetTrackerService.updateassignAsset(assetTrackerData);	
		return ResponseEntity.status(HttpStatus.OK).body(assignAsset);		
	}
	
	
	@GetMapping("/assettrackerlist")
	public ResponseEntity<List<AssetTrackerTableData>> fetchAssetTracklist(@RequestParam(required = false) String sortby,
		@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
		@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {		
		List<AssetTrackerTableData> assetTrackerTableDatas = assetTrackerService.fetchAllAssetTrackerList(sortby, pageNo, pageSize);
		return ResponseEntity.ok(assetTrackerTableDatas);
	}
	

	

	

}
