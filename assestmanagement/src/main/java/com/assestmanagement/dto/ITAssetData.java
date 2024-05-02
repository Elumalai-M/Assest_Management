package com.assestmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ITAssetData {
    private long id;
    private String hostName;
    private String totalPort;
    private String managementPortInfo;
    private String defaultGateWay;
    private String firewallType;
    private String firewallIpAddress;
    private String macAddress;
    private String serviceTag;
    private String os;
    private String processor;
    private String raidCard;
    private String harddisk;
    private String networkCard;
    private String smps;
    private String vmtype;
    private String diskDetails;
    private String graphicsCard;
    private Boolean isPrinterLinked;
    private Boolean active;
}
