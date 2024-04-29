package com.assestmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assestmanagement.model.AssestModel;
import com.assestmanagement.model.EmployeeModel;
import com.assestmanagement.model.FixedAssest;

@Repository
public interface AssestRepository extends JpaRepository<AssestModel,Long> {

	Optional<AssestModel> findByAssetId(Long assetId);

}
