package com.assestmanagement.repository;

import java.util.Optional;

import com.assestmanagement.model.AssetModel;
import com.assestmanagement.model.FixedAssetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FixedAssestRepository extends JpaRepository<FixedAssetModel,Long>{

	Optional<FixedAssetModel> findByAsset(Optional<AssetModel> assestModel);


}
