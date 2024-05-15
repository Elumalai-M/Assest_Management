package com.assetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset {

	    private String category;
	    private String assetCode;
	    private String assetName;
	    private String brand;
	    private String modelNumber;
	    private String serialNumber;
	    private String poNumber;
	    private String dcNumber;
	    private double cost;
	    private String remark;
	    private String assetType;
	    private String asset;
	    private String vendor;
	    private double rent;
	    private String serviceTag;

    
}
