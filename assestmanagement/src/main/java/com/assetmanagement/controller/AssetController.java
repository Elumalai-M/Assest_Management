package com.assetmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assetmanagement.constant.AssetManagementConstants;
import com.assetmanagement.dto.AssetData;
import com.assetmanagement.dto.ErrorResponseDto;
import com.assetmanagement.dto.ResponseDto;
import com.assetmanagement.service.AssetService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/asset")
public class AssetController {

	@Autowired
	private AssetService assetService;

	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<ResponseDto> createAsset(@RequestBody AssetData assetData) {
		assetService.createAsset(assetData);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AssetManagementConstants.STATUS_201, AssetManagementConstants.MESSAGE_201));
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDto> updateAsset(@RequestBody AssetData assetData) {
		assetService.updateAsset(assetData);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AssetManagementConstants.STATUS_200, AssetManagementConstants.MESSAGE_200));
	}

	@RequestMapping(value = "/getAssetList", method = RequestMethod.GET)
	public ResponseEntity<List<AssetData>> getAssetList() {
		List<AssetData> fetchAssetList = assetService.fetchAssetList();
		return ResponseEntity.ok().body(fetchAssetList);
	}

	@RequestMapping(value = "/getAssetById/{assetId}", method = RequestMethod.GET)
	public ResponseEntity<AssetData> getAssetByid(@PathVariable("assetId") String assetId) {
		AssetData assetById = assetService.getAssetById(assetId);
		return ResponseEntity.ok().body(assetById);
	}

}
