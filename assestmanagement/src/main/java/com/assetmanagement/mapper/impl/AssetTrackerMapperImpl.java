package com.assetmanagement.mapper.impl;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.assetmanagement.dto.AssetTrackerData;
import com.assetmanagement.dto.AssetTrackerTableData;
import com.assetmanagement.jpa.dto.AssetTrackerTableDto;
import com.assetmanagement.mapper.AssetTrackerMapper;
import com.assetmanagement.model.AssetTrackerModel;
import com.assetmanagement.model.Status;
import com.assetmanagement.utils.CodeGenerator;
import com.assetmanagement.utils.DateUtlis;

@Component
public class AssetTrackerMapperImpl implements AssetTrackerMapper {

	@Override
	public AssetTrackerTableData populateAssetTrackerTableData(AssetTrackerTableDto assetTrackerTableDto) {

		AssetTrackerTableData assetTrackerTableData = new AssetTrackerTableData();
		assetTrackerTableData.setId(assetTrackerTableDto.getId());
		assetTrackerTableData.setAssetAssignId(assetTrackerTableDto.getAssetAssignId());
		assetTrackerTableData.setAssetId(assetTrackerTableDto.getAssetId());
		assetTrackerTableData.setAssetName(assetTrackerTableDto.getAssetname());
		assetTrackerTableData.setEmployeeId(assetTrackerTableDto.getEmployeeId());
		assetTrackerTableData.setEmployeeName(assetTrackerTableDto.getEmployeename());
		if (Objects.nonNull(assetTrackerTableDto.getAssignedDate())) {
			assetTrackerTableData.setAssignDate(DateUtlis.localDateToString(assetTrackerTableDto.getAssignedDate()));
		}
		if (Objects.nonNull(assetTrackerTableDto.getReturnDate())) {
			assetTrackerTableData.setReturnDate(DateUtlis.localDateToString(assetTrackerTableDto.getReturnDate()));
		}
		assetTrackerTableData.setRemark(assetTrackerTableDto.getRemark());
		return assetTrackerTableData;
	}

	@Override
	public AssetTrackerModel reversePopulateAssetTrac(AssetTrackerData assetTrackerData,
			AssetTrackerModel assetTrackerModel) {
		if (Objects.nonNull(assetTrackerData.getId())) {
			assetTrackerModel.setId(assetTrackerData.getId());
		}
		if (Objects.nonNull(assetTrackerData.getAssetAssignId())) {
			assetTrackerModel.setAssetAssignId(assetTrackerData.getAssetAssignId());
		} else {
			assetTrackerModel.setAssetAssignId(CodeGenerator.customCodeGenerator(
					assetTrackerModel.getEmployee().getEmployeeId() + assetTrackerModel.getAsset().getAssetId()));
		}

		if (Objects.nonNull(assetTrackerData.getAssignDate())) {
			assetTrackerModel.setAssignDate(DateUtlis.stringToLocalDate(assetTrackerData.getAssignDate()));
		}
		if (Objects.nonNull(assetTrackerData.getReturnDate())
				&& Status.ASSIGNED.equals(assetTrackerModel.getAsset().getStatus())) {
			assetTrackerModel.setReturnDate(DateUtlis.stringToLocalDate(assetTrackerData.getReturnDate()));
			assetTrackerModel.getAsset().setStatus(Status.UNASSIGNED);

		}
		assetTrackerModel.setRemark(assetTrackerData.getRemark());
		return assetTrackerModel;
	}

	@Override
	public AssetTrackerData reversepopulateAssetTrackerData(AssetTrackerModel assetTrackerModel) {
		AssetTrackerData assetTrackerData = new AssetTrackerData();
		assetTrackerData.setId(assetTrackerModel.getId());
		assetTrackerData.setAssetAssignId(assetTrackerModel.getAssetAssignId());
		assetTrackerData.setAssetId(assetTrackerModel.getAsset().getAssetId());
		assetTrackerData.setAssetName(assetTrackerModel.getAsset().getAssetName());
		assetTrackerData.setEmployeeId(assetTrackerModel.getEmployee().getEmployeeId());
		assetTrackerData.setEmployeeName(
				assetTrackerModel.getEmployee().getFirstName() + " " + assetTrackerModel.getEmployee().getLastName());
		assetTrackerData.setRemark(assetTrackerModel.getRemark());
		if (Objects.nonNull(assetTrackerModel.getAssignDate())) {
			assetTrackerData.setAssignDate(DateUtlis.localDateToString(assetTrackerModel.getAssignDate()));
		}
		if (Objects.nonNull(assetTrackerModel.getReturnDate())) {
			assetTrackerData.setReturnDate(DateUtlis.localDateToString(assetTrackerModel.getReturnDate()));
		}
		return assetTrackerData;
	}

}
