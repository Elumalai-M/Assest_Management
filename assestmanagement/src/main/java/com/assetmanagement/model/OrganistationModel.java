package com.assetmanagement.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
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
