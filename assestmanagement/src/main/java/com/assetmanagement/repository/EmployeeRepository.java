package com.assetmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assetmanagement.dto.EmployeeData;
import com.assetmanagement.model.EmployeeModel;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel,Integer> {
		boolean existsByEmailId(String emailId);

		Optional<EmployeeModel> findByEmailId(String emailId);

//		@Modifying
//		@Transactional
//		@Query(value = "UPDATE EmployeeModel SET isDisabled = true WHERE employeeId in (:employeeIds)")
//		void disableEmployes(@Param("employeeIds") List<String> employeeIds);
		
		@Modifying
		@Transactional
	    @Query("UPDATE EmployeeModel e SET e.isDisabled = true WHERE e.employeeId IN (:employeeIds)")
		void disableEmployes(@Param("employeeIds") List<String> employeeIds);
		
		@Query("SELECT e FROM EmployeeModel e WHERE e.isDisabled = false")
		List<EmployeeModel> findAllEmployee();

		Optional<EmployeeModel> findByEmployeeId(String employeeId);


}
