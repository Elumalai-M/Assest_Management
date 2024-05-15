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
	    private String displaysize;
	    private String batteryhealth;
	    private String lanmacaddress;
	    private String wifimacaddress;
	    private String dcnumber;
	    private String os;
	    private String osversion;
	    private String processor;
	    private String generation;
	    private String clockspeed;
	    private String ram;
	    private String storagetype;
	    private String storagecapacity;
}
