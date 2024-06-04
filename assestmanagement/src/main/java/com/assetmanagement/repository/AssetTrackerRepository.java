package com.assetmanagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assetmanagement.dto.AssetTrackerData;
import com.assetmanagement.jpa.dto.AssetTrackerTableDto;
import com.assetmanagement.model.AssetTrackerModel;

@Repository
public interface AssetTrackerRepository extends JpaRepository<AssetTrackerModel, Long> {

	@Query(value = "SELECT asst.asset_assign_id as assetAssignId , asset.asset_id as assetid,"
			+ " asset.asset_name as assetname, asst.id as id, "
			+ " asst.assign_date as assignedDate, asst.return_date as returnDate, asst.remark as remark, "
			+ " asset.asset_name as assetname, emp.employee_id as employeeId, emp.first_name as employeename, "
			+ "	asset.asset_type as assettype FROM assetsphere.assettracker as asst left join employee as emp "
			+ " on emp.id= asst.employee left join asset as asset on asset.id = asst.asset", nativeQuery = true)
	Page<AssetTrackerTableDto> findAllAssetTrackerlist(Pageable pageable);

	AssetTrackerModel findByAssetAssignId(String assetAssignId);

}
