package com.assetmanagement.service.impl;

import com.assetmanagement.dto.TicketData;
import com.assetmanagement.exception.TicketNotFoundException;
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

        return optionalTicket.map(ticketModel -> ticketMapper.toTicketDto(ticketModel)).orElseThrow(() -> {
            log.warn("No ticket found with ticketID: {}", id);
            return new TicketNotFoundException("NO TICKET FOUND with ID: " + id);
        });
    }
}