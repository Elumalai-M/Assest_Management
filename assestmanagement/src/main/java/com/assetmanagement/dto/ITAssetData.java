package com.assetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ITAssetData {
	    private String color;
	    private String chargerType;
	    private String displaySize;
	    private String batteryHealth;
	    private String lanMacAddress;
	    private String wifiMacAddress;
	    private String dcNumber;
	    private String os;
	    private String osVersion;
	    private String processor;
	    private String generation;
	    private String clockSpeed;
	    private String ram;
	    private String storageType;
	    private String storageCapacity;
}
