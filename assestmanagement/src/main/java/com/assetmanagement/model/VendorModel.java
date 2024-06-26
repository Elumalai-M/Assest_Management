package com.assetmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "vendor")
public class VendorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(unique = true)
    private String vendorId;
    private String vendorName;
    private String email;
    private String contactNumber;
    private String poc;
    private boolean active;
    private String gstNo;

    @OneToMany(mappedBy = "vendor")
    private List<AssetModel> assets;
}
