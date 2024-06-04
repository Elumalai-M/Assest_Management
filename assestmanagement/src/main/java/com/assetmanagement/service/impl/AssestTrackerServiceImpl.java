package com.assetmanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.assetmanagement.dto.AssetTrackerData;
import com.assetmanagement.dto.AssetTrackerTableData;
import com.assetmanagement.exception.AssetNotAbleToAssignException;
import com.assetmanagement.jpa.dto.AssetTrackerTableDto;
import com.assetmanagement.mapper.AssetTrackerMapper;
import com.assetmanagement.model.AssetModel;
import com.assetmanagement.model.AssetTrackerModel;
import com.assetmanagement.model.EmployeeModel;
import com.assetmanagement.model.OperationalStatus;
import com.assetmanagement.model.Status;
import com.assetmanagement.repository.AssetRepository;
import com.assetmanagement.repository.AssetTrackerRepository;
import com.assetmanagement.repository.EmployeeRepository;
import com.assetmanagement.service.AssetTrackerService;

@Service
public class AssestTrackerServiceImpl implements AssetTrackerService {

	@Autowired
	private AssetTrackerRepository assetTrackerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AssetRepository assetRepository;

	@Autowired
	private AssetTrackerMapper assetTrackerMapper;

	@Override
	public List<AssetTrackerTableData> fetchAllAssetTrackerList(String sortby, Integer pageNo, Integer pageSize) {
		Sort sort = null;
		 PageRequest pageable = null;
		if(Objects.nonNull(sort))
		{
		Sort.Order order = new Sort.Order(Sort.Direction.ASC, sortby);
		 sort = Sort.by(order);
		 pageable = PageRequest.of(pageNo, pageSize, sort);
		}
		else
		{
			pageable = PageRequest.of(pageNo, pageSize);
		}
		Page<AssetTrackerTableDto> page = assetTrackerRepository.findAllAssetTrackerlist(pageable);
		List<AssetTrackerTableData> assetTrackerTableDatas = page.getContent().stream()
				.map(AssetTrackerTable -> assetTrackerMapper.populateAssetTrackerTableData(AssetTrackerTable)).toList();
		return assetTrackerTableDatas;
	}

	@Override
	public AssetTrackerData assignAsset(AssetTrackerData assetTrackerData) {
		AssetTrackerModel assetTrackerModel = new AssetTrackerModel();
		Optional<AssetModel> asset = assetRepository.findByAssetId(assetTrackerData.getAssetId());
		Optional<EmployeeModel> employee = employeeRepository.findByEmployeeId(assetTrackerData.getEmployeeId());
		if (asset.isPresent() && OperationalStatus.WORKING.equals(asset.get().getOperationalStatus())
				&& Status.UNASSIGNED.equals(asset.get().getStatus())) {
			assetTrackerModel.setAsset(asset.get());
			assetTrackerModel.getAsset().setStatus(Status.ASSIGNED);
			
		} else
			throw new AssetNotAbleToAssignException("asset Not able to assign:" + assetTrackerData.getAssetId());
		if (employee.isPresent()) {
			assetTrackerModel.setEmployee(employee.get());
		} else {
			throw new AssetNotAbleToAssignException("Employee not found: " + assetTrackerData.getEmployeeId());
		}
		assetTrackerModel = assetTrackerMapper.reversePopulateAssetTrac(assetTrackerData, assetTrackerModel);
		//assetRepository.save(assetTrackerModel.getAsset());
		AssetTrackerModel savedAssetTrackerModel = assetTrackerRepository.save(assetTrackerModel);
		AssetTrackerData savedAssetTrackerData = assetTrackerMapper
				.reversepopulateAssetTrackerData(savedAssetTrackerModel);
		return savedAssetTrackerData;
	}

	@Override
	public AssetTrackerData updateassignAsset(AssetTrackerData assetTrackerData) {
		
		AssetTrackerModel assetTrackerModel = assetTrackerRepository.findByAssetAssignId(assetTrackerData.getAssetAssignId());
		assetTrackerModel = assetTrackerMapper.reversePopulateAssetTrac(assetTrackerData, assetTrackerModel);
		AssetTrackerModel updatedAssetTrackerModel = assetTrackerRepository.save(assetTrackerModel);
		AssetTrackerData updatedAssetTrackerData = assetTrackerMapper
				.reversepopulateAssetTrackerData(updatedAssetTrackerModel);
		return updatedAssetTrackerData;
	}
}
