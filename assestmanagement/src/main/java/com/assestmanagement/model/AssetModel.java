package com.assestmanagement.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "asset")
public class AssetModel {
        @Id
    	@GeneratedValue(strategy = GenerationType.AUTO)
	    private Long assetId;

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
	    
}
