package com.assetmanagement.service;

import java.util.List;

import com.assetmanagement.dto.VendorData;
import com.assetmanagement.dto.VendorResponseDto;


public interface VendorService {
	
	VendorData createVendor(VendorData vendorData);
		List<VendorData> getAllVendorList();
		List<VendorData> getVendorDataByName (String vendorName);
		VendorData getVendorDataByVednorId(String vendorId);
		VendorData getVendorById(Long Id);
		VendorData updateVendor(VendorData vendorData);
		VendorResponseDto getAllVendorList(Integer pageNo, Integer pageSize);
}
