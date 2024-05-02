package com.assetmanagement.configuration;

import org.springframework.batch.item.ItemProcessor;

import com.assetmanagement.model.EmployeeModel;

public class EmployeeProcessor implements ItemProcessor<EmployeeModel,EmployeeModel>{

	@Override
	public EmployeeModel process(EmployeeModel employee) throws Exception {
	            return employee;
	}

}
