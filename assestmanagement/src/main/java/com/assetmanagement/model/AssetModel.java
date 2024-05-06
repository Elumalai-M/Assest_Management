package com.assetmanagement.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "asset")
public class AssetModel {
        @Id
    	@GeneratedValue(strategy = GenerationType.AUTO)
	    private Long assetId;
		@Column(unique = true)
	    private String assetName;
	    private String managedBy;
	    private String remark;
	    private String serialNumber;

	    @Enumerated(EnumType.STRING)
	    private Status status;

	    @Enumerated(EnumType.STRING)
	    private OperationalStatus operationalStatus;

	    @Enumerated(EnumType.STRING)
	    private AssetType assetType;

	    @Enumerated(EnumType.STRING)
	    private Category category;

	    private double cost;
	    private String brand;
	    private String modelNumber;

		@ManyToOne
		@JoinColumn(name = "organistationId",referencedColumnName = "id")
		private OrganistationModel organistationDetail;

		@ManyToOne
		@JoinColumn(name = "employeeId",referencedColumnName = "id")
		private EmployeeModel employee;

		@ManyToOne
		@JoinColumn(name = "vendorId",referencedColumnName = "id")
		private VendorModel vendor;

		@OneToOne
		@JoinColumn(name = "itAsset" ,referencedColumnName = "id")
		private ITAssetModel itAsset;

	    @OneToOne
	    @JoinColumn(name = "fixedAsset" ,referencedColumnName = "id")
	    private FixedAssetModel fixedAsset;

		@OneToMany(mappedBy = "asset")
		private List<TicketModel> tickets;

	@Override
	public String toString() {
		return "AssetModel{" +
				"assetId=" + assetId +
				", assetName='" + assetName + '\'' +
				", managedBy='" + managedBy + '\'' +
				", remark='" + remark + '\'' +
				", serialNumber='" + serialNumber + '\'' +
				", status=" + status +
				", operationalStatus=" + operationalStatus +
				", assetType=" + assetType +
				", category=" + category +
				", cost=" + cost +
				", brand='" + brand + '\'' +
				", modelNumber='" + modelNumber + '\'' +
				", organistationId=" + (organistationDetail != null ? organistationDetail.getId() : null) +
				", employeeId=" + (employee != null ? employee.getId() : null) +
				", vendorId=" + (vendor != null ? vendor.getId() : null) +
				", itAssetId=" + (itAsset != null ? itAsset.getId() : null) +
				", fixedAssetId=" + (fixedAsset != null ? fixedAsset.getId() : null) +
				'}';
	}

}
