package com.assestmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset {

    private Long assetId;
    private String assetName;
    private String managedBy;
    private String modelNumber;
    private String serialNumber;
    private String brand;
    private double cost;
    private String status;
    private String operationalStatus;
    private String assetType;
    private String category;
    
}
