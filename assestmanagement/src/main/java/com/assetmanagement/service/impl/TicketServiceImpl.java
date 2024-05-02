package com.assetmanagement.service.impl;

import com.assetmanagement.dto.TicketData;
import com.assetmanagement.mapper.TicketMapper;
import com.assetmanagement.model.TicketModel;
import com.assetmanagement.repository.TicketRepository;
import com.assetmanagement.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

    @Autowired
    public TicketRepository ticketRepository;

    @Autowired
    public TicketMapper ticketMapper;


    @Override
    public TicketData createTicket(TicketData ticketData) {
        return null;
    }

    @Override
    public TicketData getTicket(Long id) {
        Optional<TicketModel> optionalTicket = ticketRepository.findById(id);

        return optionalTicket.map(ticketModel -> {
            log.info("Ticket found with ID: {}", id);

            if (ticketModel.getAsset() != null) {
                log.info("Asset Details: {}", ticketModel.getAsset());
            } else {
                log.info("No Asset linked with Ticket ID: {}", id);
            }

            if (ticketModel.getAsset() != null && ticketModel.getAsset().getFixedAsset() != null) {
                log.info("Fixed Asset Details: {}", ticketModel.getAsset().getFixedAsset());
            } else {
                log.info("No Fixed Asset linked with Ticket ID: {}", id);
            }

            return ticketMapper.toTicketDto(ticketModel);
        }).orElseGet(() -> {
            log.warn("No ticket found with ticketID: {}", id);
            return null;
        });
    }
}