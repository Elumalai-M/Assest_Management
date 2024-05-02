package com.assetmanagement.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assetmanagement.dto.OrgCreationData;
import com.assetmanagement.model.OrganistationModel;
import com.assetmanagement.repository.OrgRepository;
import com.assetmanagement.service.OrgService;

@Service
public class OrgServiceImpl implements OrgService{
	
	@Autowired
	private OrgRepository orgRepostory;

	@Override
	public OrgCreationData createOrg(OrgCreationData orgCreationData) {
		OrganistationModel organistationModel = new OrganistationModel();
		BeanUtils.copyProperties(orgCreationData, organistationModel);
		orgRepostory.save(organistationModel);
		return orgCreationData;

	}

}
