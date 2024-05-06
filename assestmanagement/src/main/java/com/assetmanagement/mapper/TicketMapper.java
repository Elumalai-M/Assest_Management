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
}
