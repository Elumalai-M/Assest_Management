package com.assetmanagement.mapper.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.assetmanagement.dto.VendorData;
import com.assetmanagement.mapper.VendorReversePopulator;
import com.assetmanagement.model.VendorModel;

@Component
public class VendorReversePopulatorImpl  implements VendorReversePopulator {
	
	public VendorData reversepopulateVendor(VendorModel vendorModels) {
		
		VendorData vendorData = new VendorData();
		BeanUtils.copyProperties(vendorModels, vendorData);
		return vendorData;
	}


}
