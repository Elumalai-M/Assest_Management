package com.assetmanagement.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assethistroy")
public class AssetHistoryModel extends BaseModel {
	
	 @Id
 	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String assetAssignId;
	
	@ManyToOne
	@JoinColumn(name="employee", referencedColumnName = "id")
	private EmployeeModel employee;
	
	//private EmployeeModel assignedBy;
	
	@ManyToOne
	@JoinColumn(name="asset", referencedColumnName = "assetId")
	private AssetModel asset;
	
	private LocalDate assignDate;
	private LocalDate returnDate;
	private String remark;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAssetAssignId() {
		return assetAssignId;
	}
	public void setAssetAssignId(String assetAssignId) {
		this.assetAssignId = assetAssignId;
	}
	public EmployeeModel getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeModel employee) {
		this.employee = employee;
	}


	public LocalDate getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(LocalDate assignDate) {
		this.assignDate = assignDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public AssetModel getAsset() {
		return asset;
	}
	public void setAsset(AssetModel asset) {
		this.asset = asset;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(asset, assetAssignId, assignDate, employee, id, remark, returnDate);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssetHistoryModel other = (AssetHistoryModel) obj;
		return Objects.equals(asset, other.asset) && Objects.equals(assetAssignId, other.assetAssignId)
				&& Objects.equals(assignDate, other.assignDate) && Objects.equals(employee, other.employee)
				&& Objects.equals(id, other.id) && Objects.equals(remark, other.remark)
				&& Objects.equals(returnDate, other.returnDate);
	}

	

}
