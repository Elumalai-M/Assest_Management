package com.assetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AssetData {
	    private Asset asset;
	    private FixedAsset fixedasset;
		private ITAssetData itasset;

}
