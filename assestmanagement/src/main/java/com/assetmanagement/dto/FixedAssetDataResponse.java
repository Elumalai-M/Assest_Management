package com.assetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FixedAssetDataResponse {
	 	private Long id;
	    private String color;
	    private String graphicsCard;
	    private String ram;
	    private String rom;
	    private String processor;
	    private String material;
	    private String os;
	    private String osVersion;
	    private String battery;
	    private String chargerType;
	    private boolean wireless;
	    private double weight;
	    private String dimension;
	    private String ipAddress;
	    private String connectorType;
	    private String bluetoothVersion;
	    private int chargingTime;
	    private int capacity;
	    private String size;
	    private int watts;
	    private String volt;
	    private double length;
	    private String simNumber;
	    private String imeiNumber;
	    private String generation;
	    private com.assetmanagement.dto.Asset asset;

		
}
