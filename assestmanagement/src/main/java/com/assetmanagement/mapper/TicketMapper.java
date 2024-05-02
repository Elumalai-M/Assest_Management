package com.assetmanagement.mapper;

import com.assetmanagement.dto.*;
import com.assetmanagement.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketModel toTicketModel(TicketData ticketData) {
        TicketModel ticketModel = new TicketModel();
        BeanUtils.copyProperties(ticketData, ticketModel);
        ticketModel.setEmployee(convertToEmployeeModel(ticketData.getEmployee()));
        ticketModel.setAsset(convertToAssetModel(ticketData.getAsset()));
        return ticketModel;
    }

    public TicketData toTicketDto(TicketModel ticketModel) {
        TicketData ticketData = new TicketData();
        BeanUtils.copyProperties(ticketModel, ticketData);
        ticketData.setEmployee(convertToEmployeeData(ticketModel.getEmployee()));
        ticketData.setAsset(convertToAssetData(ticketModel.getAsset()));
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
            data.setItAsset(itAssetData);
        }

        return data;
    }

}
