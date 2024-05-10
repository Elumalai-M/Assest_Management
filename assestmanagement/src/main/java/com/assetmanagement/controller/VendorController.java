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

import com.assetmanagement.dto.VendorData;
import com.assetmanagement.service.VendorService;



@RestController
@RequestMapping("assetManagment/api/v1/vendor")
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@PostMapping("/createVendor")
	public ResponseEntity<VendorData> createVendor(@RequestBody VendorData vendorData) {
		VendorData savedvendorData = vendorService.createVendor(vendorData);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedvendorData);
	}

	@GetMapping("/vendorByName")
	public ResponseEntity<List<VendorData>> fetchByVendorName(@RequestParam String vendorName) {
		List<VendorData> vendorData = vendorService.getVendorDataByName(vendorName);
		return ResponseEntity.ok().body(vendorData);
	}

//	@GetMapping("/fetchAllVendor")
//	public ResponseEntity<VendorResponseDto> fetchAllVendor(@RequestParam(required = false) String status,
//			@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
//			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
//		
//	//	List<VendorData> vendorData = (status != null && !status.isEmpty()) ? vendorService.getAllVendorList():
//		
//		VendorResponseDto vendorResponseDto = vendorService.getAllVendorList(pageNo, pageSize);
//		return ResponseEntity.ok().body(vendorResponseDto);
//
//	}
	
	@GetMapping("/fetchAllVendor")
	public ResponseEntity<List<VendorData>> fetchAllVendor() {

	//	List<VendorData> vendorData = (status != null && !status.isEmpty()) ? vendorService.getAllVendorList():
		List<VendorData> vendorDatalist = vendorService.getAllVendorList();
		return ResponseEntity.ok().body(vendorDatalist);
	}

	@GetMapping("/vendorById")
	public ResponseEntity<VendorData> fetchVendorById(@RequestParam Long Id) {
		VendorData vendorData = vendorService.getVendorById(Id);
		return ResponseEntity.ok().body(vendorData);
	}

	@GetMapping("/vendorByVendorId")
	public ResponseEntity<VendorData> fetchVendorByVendorId(@RequestParam String vendorId) {
		VendorData vendorData = vendorService.getVendorDataByVednorId(vendorId);
		return ResponseEntity.ok().body(vendorData);
	}

	@PutMapping("/updateVendor")
	public ResponseEntity<VendorData> updateVendor(@RequestBody VendorData vendorData) {
		VendorData savedvendorData = vendorService.updateVendor(vendorData);
		return ResponseEntity.ok().body(savedvendorData);
	}

}
