package com.assetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmanagement.model.AssetHistoryModel;

@Repository
public interface AssetHistroyRepository extends JpaRepository<AssetHistoryModel, Long> {

	

}
