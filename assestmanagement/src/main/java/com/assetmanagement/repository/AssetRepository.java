package com.assetmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assetmanagement.dto.AssetSummary;
import com.assetmanagement.model.AssetModel;


@Repository
public interface AssetRepository extends JpaRepository<AssetModel,Long> {

	Optional<AssetModel> findByAssetId(Long assetId);

	 // @Query("SELECT " +
	 //           "   SUM(CASE WHEN a.status = 'ASSIGNED' AND a.fixedAsset IS NOT NULL THEN 1 ELSE 0 END) AS assignedFixedAssets, " +
	 //           "   SUM(CASE WHEN a.status != 'ASSIGNED' AND a.fixedAsset IS NOT NULL AND a.operationalStatus = 'WORKING' THEN 1 ELSE 0 END) AS unassignedFixedAssets, " +
	 //           "   SUM(CASE WHEN a.operationalStatus = 'NOT_WORKING' AND a.fixedAsset IS NOT NULL THEN 1 ELSE 0 END) AS notWorkingFixedAssets, " +
	 //           "   SUM(CASE WHEN a.status = 'ASSIGNED' AND a.itAsset IS NOT NULL THEN 1 ELSE 0 END) AS assignedItAssets, " +
	 //           "   SUM(CASE WHEN a.status != 'ASSIGNED' AND a.itAsset IS NOT NULL AND a.operationalStatus = 'WORKING' THEN 1 ELSE 0 END) AS unassignedItAssets, " +
	 //           "   SUM(CASE WHEN a.operationalStatus = 'NOT_WORKING' AND a.itAsset IS NOT NULL THEN 1 ELSE 0 END) AS notWorkingItAssets, " +
	 //           "   COUNT(a.id) AS totalAssets, " +
	 //           "   SUM(CASE WHEN a.status = 'ASSIGNED' THEN 1 ELSE 0 END) AS totalAssignedAssets, " +
	 //           "   SUM(CASE WHEN a.status != 'ASSIGNED' AND a.operationalStatus = 'WORKING' THEN 1 ELSE 0 END) AS totalUnassignedAssets, " +
	 //           "   SUM(CASE WHEN a.operationalStatus = 'NOT_WORKING' THEN 1 ELSE 0 END) AS totalNotWorkingAssets " +
	 //           "FROM AssetModel a")
	@Query("""
		        SELECT 
		            SUM(CASE WHEN a.status = 'ASSIGNED' AND a.fixedAsset IS NOT NULL THEN 1 ELSE 0 END) AS assignedFixedAssets,
		            SUM(CASE WHEN a.status != 'ASSIGNED' AND a.fixedAsset IS NOT NULL AND a.operationalStatus = 'WORKING' THEN 1 ELSE 0 END) AS unassignedFixedAssets,
		            SUM(CASE WHEN a.operationalStatus = 'NOT_WORKING' AND a.fixedAsset IS NOT NULL THEN 1 ELSE 0 END) AS notWorkingFixedAssets,
		            SUM(CASE WHEN a.status = 'ASSIGNED' AND a.itAsset IS NOT NULL THEN 1 ELSE 0 END) AS assignedItAssets,
		            SUM(CASE WHEN a.status != 'ASSIGNED' AND a.itAsset IS NOT NULL AND a.operationalStatus = 'WORKING' THEN 1 ELSE 0 END) AS unassignedItAssets,
		            SUM(CASE WHEN a.operationalStatus = 'NOT_WORKING' AND a.itAsset IS NOT NULL THEN 1 ELSE 0 END) AS notWorkingItAssets,
		            COUNT(a.id) AS totalAssets,
		            SUM(CASE WHEN a.status = 'ASSIGNED' THEN 1 ELSE 0 END) AS totalAssignedAssets,
		            SUM(CASE WHEN a.status != 'ASSIGNED' AND a.operationalStatus = 'WORKING' THEN 1 ELSE 0 END) AS totalUnassignedAssets,
		            SUM(CASE WHEN a.operationalStatus = 'NOT_WORKING' THEN 1 ELSE 0 END) AS totalNotWorkingAssets 
		        FROM AssetModel a
		    """)
	AssetSummary getAssetSummary();

}
