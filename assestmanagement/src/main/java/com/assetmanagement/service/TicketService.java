package com.assetmanagement.service;

import com.assetmanagement.dto.TicketData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TicketService {

    TicketData createTicket(TicketData ticketData) throws IOException;
    TicketData getTicket(Long id);

}
