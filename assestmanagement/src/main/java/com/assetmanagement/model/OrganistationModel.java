package com.assetmanagement.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organization")
public class OrganistationModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String organizationCode;
	private String name;
	private String emailId;
	private String location;
	private Date establishedDate;

	@OneToMany(mappedBy = "organistation")
	private List<EmployeeModel> employee;

	@OneToMany(mappedBy = "organistationDetail")
	private List<AssetModel> assets;
}
