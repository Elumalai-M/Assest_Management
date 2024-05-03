package com.assetmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmanagement.model.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel,Integer> {
	 Optional<RoleModel> findByName(String name);
}
