package com.assetmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmanagement.model.VendorModel;

@Repository
public interface VendorRepository extends JpaRepository<VendorModel, Long> {

	Optional<List<VendorModel>> findByVendorName(String vendorName);

	Optional<List<VendorModel>> findByActive(boolean b);

	Optional<VendorModel> findByVendorId(String vendorId);
	
	

}