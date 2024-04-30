package com.assestmanagement.repository;

import com.assestmanagement.model.AssetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AssestRepository extends JpaRepository<AssetModel,Long> {

	Optional<AssetModel> findByAssetId(Long assetId);

}
