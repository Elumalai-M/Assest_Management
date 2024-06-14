package com.assetmanagement.mapper;

import com.assetmanagement.dto.*;
import com.assetmanagement.model.*;
import com.assetmanagement.repository.AssetRepository;
import com.assetmanagement.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TicketMapper {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AssetRepository assetRepository;


    public TicketModel toTicketModel(TicketData ticketData) {
        TicketModel ticketModel = new TicketModel();
        BeanUtils.copyProperties(ticketData, ticketModel, "employee", "asset");

        EmployeeModel employee = employeeRepository.findByEmployeeId(ticketData.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        AssetModel asset = assetRepository.findByAssetName(ticketData.getAssetName())
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        ticketModel.setEmployee(employee);
        ticketModel.setAsset(asset);
        return ticketModel;
    }

    public TicketData toTicketDto(TicketModel ticketModel) throws IOException {
        if (ticketModel == null) {
            return null;
        }

        TicketData ticketData = new TicketData();
        BeanUtils.copyProperties(ticketModel, ticketData, "employee", "asset");

        ticketData.setFileData(ticketModel.getFileData());

        if (ticketModel.getEmployee() != null) {
            ticketData.setEmployeeId(ticketModel.getEmployee().getId().toString());
        }
        if (ticketModel.getAsset() != null) {
            ticketData.setAssetName(ticketModel.getAsset().getAssetName());
        }

        return ticketData;
    }

    private EmployeeModel convertToEmployeeModel(EmployeeData data) {
        if (data == null) return null;
        EmployeeModel model = new EmployeeModel();
        BeanUtils.copyProperties(data, model);
        return model;
    }

    private EmployeeData convertToEmployeeData(EmployeeModel model) {
        if (model == null) return null;
        EmployeeData data = new EmployeeData();
        BeanUtils.copyProperties(model, data);
        return data;
    }

    private AssetModel convertToAssetModel(AssetData data) {
        if (data == null) return null;
        AssetModel model = new AssetModel();
        BeanUtils.copyProperties(data, model);

        if (data.getFixedasset() != null) {
            FixedAssetModel fixedAsset = new FixedAssetModel();
            BeanUtils.copyProperties(data.getFixedasset(), fixedAsset);
            model.setFixedAsset(fixedAsset);
        }
        return model;
    }

    private AssetData convertToAssetData(AssetModel model) {
        if (model == null) return null;
        AssetData data = new AssetData();
        Asset assetDto = new Asset();

        BeanUtils.copyProperties(model, assetDto);
        data.setAsset(assetDto);

        if (model.getFixedAsset() != null) {
            FixedAsset fixedAssetDto = new FixedAsset();
            BeanUtils.copyProperties(model.getFixedAsset(), fixedAssetDto);
            data.setFixedasset(fixedAssetDto);
        }

        if (model.getItAsset() !=null){
            ITAssetData itAssetData = new ITAssetData();
            BeanUtils.copyProperties(model.getItAsset(), itAssetData);
            //data.setItAsset(itAssetData);
        }

        return data;
    }

}
