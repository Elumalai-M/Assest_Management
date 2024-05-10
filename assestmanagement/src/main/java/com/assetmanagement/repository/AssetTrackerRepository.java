package com.assetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmanagement.model.AssetTrackerModel;

@Repository
public interface AssetTrackerRepository extends JpaRepository<AssetTrackerModel, Long> {

	

}
