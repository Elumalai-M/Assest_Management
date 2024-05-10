package com.assetmanagement.service.impl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.assetmanagement.dto.VendorData;
import com.assetmanagement.dto.VendorResponseDto;
import com.assetmanagement.exception.VendorNotFoundException;
import com.assetmanagement.mapper.VendorReversePopulator;
import com.assetmanagement.model.VendorModel;
import com.assetmanagement.repository.VendorRepository;
import com.assetmanagement.service.VendorService;
import com.assetmanagement.utils.CodeGenerator;




@Service
@Transactional
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private VendorReversePopulator vendorReversePopulator;

	private static final Logger logger = LoggerFactory.getLogger(VendorServiceImpl.class);
	
	@Value("${vendor.code.generator.perfix}")
	private String codeGeneratorPrefix;
	@Override
	public VendorData createVendor(VendorData vendorData) {
		VendorModel vendorModel = new VendorModel();
		BeanUtils.copyProperties(vendorData, vendorModel);
		vendorModel.setVendorId(CodeGenerator.customCodeGenerator(codeGeneratorPrefix));
		vendorModel.setActive(true);
		VendorModel savedVendor = vendorRepository.save(vendorModel);
		VendorData savedVenodrdata = new VendorData();
		BeanUtils.copyProperties(savedVendor, savedVenodrdata);
		return savedVenodrdata;
	}

	@Override
	public VendorResponseDto getAllVendorList(Integer pageNo, Integer pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo, pageSize);

		Page<VendorModel> vednors= vendorRepository.findAll(pageable);
		
		List<VendorModel> vendorcontent = vednors.getContent();

		if (CollectionUtils.isEmpty(vendorcontent)) {
			logger.info("Vendor Not founded");
			throw new VendorNotFoundException("vendor not found");
		}
	  VendorResponseDto vendorResponseDto = convertPageResposeDto(vednors);
	  return vendorResponseDto;
	}

	@Override
	public List<VendorData> getVendorDataByName(String vendorName) {
		Optional<List<VendorModel>> vendorLists = vendorRepository.findByVendorName(vendorName);
		if (CollectionUtils.isEmpty(vendorLists.get())) {
			logger.info("Vendor Not founded for VendorName: {}", vendorName);
			throw new VendorNotFoundException(MessageFormat.format("vendor not found For VendorName:{}", vendorName));
		}
		return vendorLists.get().stream().map(vendor -> vendorReversePopulator.reversepopulateVendor(vendor)).toList();
	}

	@Override
	public VendorData getVendorDataByVednorId(String vendorId) {
		Optional<VendorModel> vendor = vendorRepository.findByVendorId(vendorId);
		if (vendor.isEmpty()) {
			logger.info("vendor Not Found For VendorId:{}", vendorId);
			throw new VendorNotFoundException("Vendor Not Found for Id:" + vendorId);
		}
		VendorData vendorData = vendorReversePopulator.reversepopulateVendor(vendor.get());
		return vendorData;
	}

	@Override
	public VendorData getVendorById(Long Id) {
		Optional<VendorModel> vendor = vendorRepository.findById(Id);
		if (vendor.isEmpty()) {
			logger.info("vendor Not Found For id:{}", Id);
			throw new VendorNotFoundException("Vendor Not Found for Id:" + Id);
		}
		VendorData vendorData = vendorReversePopulator.reversepopulateVendor(vendor.get());
		return vendorData;
	}

	@Override
	public VendorData updateVendor(VendorData vendorData) {
		Optional<VendorModel> vendormodel = vendorRepository.findById(vendorData.getId());
		if (vendormodel.isEmpty()) {
			logger.info("vendor Not Found For id:{}", vendorData.getId());
			throw new VendorNotFoundException("Vendor Not Found for Id:" + vendorData.getId());
		}
		VendorModel updateVenorModel = vendormodel.get();
		BeanUtils.copyProperties(vendorData, updateVenorModel);
		VendorModel updatedVendorModel = vendorRepository.save(updateVenorModel);
		VendorData updateVendorData = vendorReversePopulator.reversepopulateVendor(updatedVendorModel);
		return updateVendorData;
	}

	@Override
	public List<VendorData> getAllVendorList() {
		
		Optional<List<VendorModel>> vendorLists = vendorRepository.findByActive(true);
		if (vendorLists.get().isEmpty()) {
			logger.info("Vendor Not founded");
			throw new VendorNotFoundException("vendor not found");
		}
		return vendorLists.get().stream().map(vendor -> vendorReversePopulator.reversepopulateVendor(vendor)).toList();
	}
	
	private VendorResponseDto convertPageResposeDto (Page<VendorModel> page)
	{	
	     VendorResponseDto vendorResponseDto = new VendorResponseDto();
	     List<VendorData> vendorDatalist = page.getContent().stream().map(vendor -> vendorReversePopulator.reversepopulateVendor(vendor)).toList();
	     vendorResponseDto.setVendordatalist(vendorDatalist);
	     vendorResponseDto.setPageNo(page.getNumber());
	     vendorResponseDto.setPageSize(page.getSize());
	     vendorResponseDto.setTotalElements(page.getTotalElements());
	     vendorResponseDto.setTotalPages(page.getTotalPages());
	     vendorResponseDto.setLast(page.isLast());
		return vendorResponseDto;
		
	}
}

