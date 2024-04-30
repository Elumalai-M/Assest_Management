package com.assestmanagement.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itasset")
public class ITAssetModel extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(mappedBy = "itAsset")
    private AssetModel asset;
}