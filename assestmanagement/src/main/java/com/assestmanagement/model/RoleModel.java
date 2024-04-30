package com.assestmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class RoleModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	private String name;

}
