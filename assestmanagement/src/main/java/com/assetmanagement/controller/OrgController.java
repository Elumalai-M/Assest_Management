package com.assetmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assetmanagement.constant.AssetManagementConstants;
import com.assetmanagement.dto.OrgCreationData;
import com.assetmanagement.dto.ResponseDto;
import com.assetmanagement.service.OrgService;

@RestController
@RequestMapping("/org")
public class OrgController {

	@Autowired
	private OrgService orgService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto> createOrg(@RequestBody OrgCreationData orgCreationData) {
		orgService.createOrg(orgCreationData);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AssetManagementConstants.STATUS_201, AssetManagementConstants.MESSAGE_201));
	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String dataTest() {
		return "hello";
	}

}
