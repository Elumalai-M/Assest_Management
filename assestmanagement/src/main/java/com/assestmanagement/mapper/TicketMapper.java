package com.assestmanagement.mapper;

import com.assestmanagement.dto.*;
import com.assestmanagement.model.*;
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

    private AssetModel convertToAssetModel(AssestData data) {
        if (data == null) return null;
        AssetModel model = new AssetModel();
        BeanUtils.copyProperties(data, model);

        if (data.getFixedassest() != null) {
            FixedAssetModel fixedAssest = new FixedAssetModel();
            BeanUtils.copyProperties(data.getFixedassest(), fixedAssest);
            model.setFixedAsset(fixedAssest);
        }
        return model;
    }

    private AssestData convertToAssetData(AssetModel model) {
        if (model == null) return null;
        AssestData data = new AssestData();
        Asset assetDto = new Asset();

        BeanUtils.copyProperties(model, assetDto);
        data.setAssest(assetDto);

        if (model.getFixedAsset() != null) {
            FixedAssest fixedAssestDto = new FixedAssest();
            BeanUtils.copyProperties(model.getFixedAsset(), fixedAssestDto);
            data.setFixedassest(fixedAssestDto);
        }

        if (model.getItAsset() !=null){
            ITAssetData itAssetData = new ITAssetData();
            BeanUtils.copyProperties(model.getItAsset(), itAssetData);
            data.setItAsset(itAssetData);
        }

        return data;
    }

}
