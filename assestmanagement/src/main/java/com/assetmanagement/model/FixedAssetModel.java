package com.assetmanagement.model;

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
    @Override
    public String toString() {
        return "FixedAssetModel{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", graphicsCard='" + graphicsCard + '\'' +
                ", ram='" + ram + '\'' +
                ", rom='" + rom + '\'' +
                ", processor='" + processor + '\'' +
                ", material='" + material + '\'' +
                ", os='" + os + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", battery='" + battery + '\'' +
                ", chargerType='" + chargerType + '\'' +
                ", wireless=" + wireless +
                ", weight=" + weight +
                ", dimension='" + dimension + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", connectorType='" + connectorType + '\'' +
                ", bluetoothVersion='" + bluetoothVersion + '\'' +
                ", chargingTime=" + chargingTime +
                ", capacity=" + capacity +
                ", size='" + size + '\'' +
                ", watts=" + watts +
                ", volt='" + volt + '\'' +
                ", length=" + length +
                ", simNumber='" + simNumber + '\'' +
                ", imeiNumber='" + imeiNumber + '\'' +
                ", generation='" + generation + '\'' +
                '}';
    }

}
