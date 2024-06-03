package com.assetmanagement.model;

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

    @OneToOne(mappedBy = "itAsset")
    private AssetModel asset;


}