package com.assestmanagement.service;

import com.assestmanagement.dto.TicketData;

public interface TicketService {

    TicketData createTicket(TicketData ticketData);
    TicketData getTicket(Long id);
}
