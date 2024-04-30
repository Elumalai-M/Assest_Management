package com.assestmanagement.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fixedAsset")
public class FixedAssetModel {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne(mappedBy = "fixedAsset")
    private AssetModel asset;


}
