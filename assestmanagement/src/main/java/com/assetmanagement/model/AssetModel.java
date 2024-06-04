package com.assetmanagement.model;

import java.util.List;
import java.util.Set;

import com.assetmanagement.exception.InValidAssetCreationException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "asset")
public class AssetModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String assetId;
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
	@JoinColumn(name = "organistationId", referencedColumnName = "id")
	private OrganistationModel organistationDetail;

	@ManyToOne
	@JoinColumn(name = "vendorId", referencedColumnName = "id")
	private VendorModel vendor;

	@OneToOne
	@JoinColumn(name = "itAsset", referencedColumnName = "id")
	private ITAssetModel itAsset;

	@OneToOne
	@JoinColumn(name = "fixedAsset", referencedColumnName = "id")
	private FixedAssetModel fixedAsset;

	@OneToMany(mappedBy = "asset")
	private List<TicketModel> tickets;

	@OneToMany(mappedBy = "asset")
	private Set<AssetTrackerModel> assetTracker;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(String managedBy) {
		this.managedBy = managedBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public OperationalStatus getOperationalStatus() {
		return operationalStatus;
	}

	public void setOperationalStatus(OperationalStatus operationalStatus) {
		this.operationalStatus = operationalStatus;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public OrganistationModel getOrganistationDetail() {
		return organistationDetail;
	}

	public void setOrganistationDetail(OrganistationModel organistationDetail) {
		this.organistationDetail = organistationDetail;
	}

	public VendorModel getVendor() {
		return vendor;
	}

	public void setVendor(VendorModel vendor) {
		this.vendor = vendor;
	}

	public ITAssetModel getItAsset() {
		return itAsset;
	}

	public void setItAsset(ITAssetModel itAsset) {
		
		if (this.fixedAsset != null) {
			throw new InValidAssetCreationException("already Fixed Asset Assigend for this asset:" + this.assetId);
		}
		
		this.itAsset = itAsset;
		
	}

	public FixedAssetModel getFixedAsset() {
		return fixedAsset;
	}

	public void setFixedAsset(FixedAssetModel fixedAsset) {
		
		if (this.itAsset != null) {
			throw new InValidAssetCreationException("already It Asset Assigend for this asset:" + this.assetId);
		}
		this.fixedAsset = fixedAsset;
	}

	public List<TicketModel> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketModel> tickets) {
		this.tickets = tickets;
	}

	public Set<AssetTrackerModel> getAssetTracker() {
		return assetTracker;
	}

	public void setAssetTracker(Set<AssetTrackerModel> assetTracker) {
		this.assetTracker = assetTracker;
	}

	@Override
	public String toString() {
		return "AssetModel [id=" + id + ", assetId=" + assetId + ", assetName=" + assetName + ", managedBy=" + managedBy
				+ ", remark=" + remark + ", serialNumber=" + serialNumber + ", status=" + status
				+ ", operationalStatus=" + operationalStatus + ", assetType=" + assetType + ", category=" + category
				+ ", cost=" + cost + ", brand=" + brand + ", modelNumber=" + modelNumber + ", organistationDetail="
				+ organistationDetail + ", vendor=" + vendor + ", itAsset=" + itAsset + ", fixedAsset=" + fixedAsset
				+ ", tickets=" + tickets + ", assetTracker=" + assetTracker + "]";
	}

}
