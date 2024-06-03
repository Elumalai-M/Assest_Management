package com.assetmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assetmanagement.model.AssetModel;
import com.assetmanagement.model.FixedAssetModel;
import com.assetmanagement.model.ITAssetModel;

public interface ITAssetRepository extends JpaRepository<ITAssetModel,Long>{

	Optional<FixedAssetModel> findByAsset(Optional<AssetModel> assetModel);


}
