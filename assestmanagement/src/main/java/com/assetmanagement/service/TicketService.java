package com.assetmanagement.service;

import com.assetmanagement.dto.TicketData;

public interface TicketService {

    TicketData createTicket(TicketData ticketData);
    TicketData getTicket(Long id);
}
