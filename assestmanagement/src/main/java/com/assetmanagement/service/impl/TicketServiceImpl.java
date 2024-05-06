package com.assetmanagement.service.impl;

import com.assetmanagement.dto.TicketData;
import com.assetmanagement.exception.TicketNotFoundException;
import com.assetmanagement.mapper.TicketMapper;
import com.assetmanagement.model.AssetModel;
import com.assetmanagement.model.EmployeeModel;
import com.assetmanagement.model.TicketModel;
import com.assetmanagement.repository.AssetRepository;
import com.assetmanagement.repository.EmployeeRepository;
import com.assetmanagement.repository.TicketRepository;
import com.assetmanagement.service.TicketService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    public TicketRepository ticketRepository;

    @Autowired
    public AssetRepository assetRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public TicketMapper ticketMapper;


    @Override
    @Transactional
    public TicketData createTicket(TicketData ticketData) throws IOException {

        if (ticketRepository.existsByTicketNo(ticketData.getTicketNo())) {
            throw new IllegalStateException("A ticket with number " + ticketData.getTicketNo() + " already exists.");
        }

        EmployeeModel employee = employeeRepository.findByEmployeeId(ticketData.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + ticketData.getEmployeeId()));
        AssetModel asset = assetRepository.findByAssetName(ticketData.getAssetName())
                .orElseThrow(() -> new RuntimeException("Asset not found with name: " + ticketData.getAssetName()));

        if (ticketRepository.existsByEmployeeAndAsset(employee, asset)) {
            throw new IllegalStateException("A ticket for the specified asset by this employee already exists.");
        }

        if (!asset.getEmployee().equals(employee)) {
            throw new IllegalStateException("This employee is not authorized to create a ticket for this asset.");
        }
        TicketModel ticketModel = new TicketModel();

        ticketModel.setTicketNo(ticketData.getTicketNo());
        ticketModel.setDescription(ticketData.getDescription());
        ticketModel.setEmployee(employee);
        ticketModel.setAsset(asset);
        ticketModel.setFileData(ticketData.getFileData());

        TicketModel savedTicket = ticketRepository.save(ticketModel);

        return ticketMapper.toTicketDto(savedTicket);
    }

    @Override
    public TicketData getTicket(Long id) {
        Optional<TicketModel> optionalTicket = ticketRepository.findById(id);

        return optionalTicket.map(ticketModel -> {
            try {
                return ticketMapper.toTicketDto(ticketModel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).orElseThrow(() -> {
            log.warn("No ticket found with ticketID: {}", id);
            return new TicketNotFoundException("NO TICKET FOUND with ID: " + id);
        });
    }
}