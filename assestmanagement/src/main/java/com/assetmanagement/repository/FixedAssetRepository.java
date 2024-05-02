package com.assetmanagement.repository;

import java.util.Optional;

import com.assetmanagement.model.AssetModel;
import com.assetmanagement.model.FixedAssetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FixedAssetRepository extends JpaRepository<FixedAssetModel,Long>{

	Optional<FixedAssetModel> findByAsset(Optional<AssetModel> assetModel);


}
