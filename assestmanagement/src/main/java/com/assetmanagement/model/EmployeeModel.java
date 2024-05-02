package com.assetmanagement.model;

import java.util.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class EmployeeModel extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String employeeId;
	private String firstName;
	private String lastName;	
	private String emailId;
	private String password;
	private Date dateOfJoining;
	private String contactNumber;
	private Date dateOfBirth;
	private String designation;
	private boolean isDisabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	            name = "users_roles",
	            joinColumns = @JoinColumn(name = "id"),
	            inverseJoinColumns = @JoinColumn(name = "roleId")
	            )
	private Set<RoleModel> roles = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "organistationId",referencedColumnName = "id")
	private OrganistationModel organistation;

	@OneToMany(mappedBy = "employee")
	private List<AssetModel> assets;

	@OneToMany(mappedBy = "employee")
	private List<TicketModel> tickets;

}
