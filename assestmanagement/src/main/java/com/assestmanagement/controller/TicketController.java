package com.assestmanagement.controller;

import com.assestmanagement.dto.TicketData;
import com.assestmanagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {

    @Autowired
    public TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<TicketData> getTicketById(@PathVariable Long id) {
        TicketData ticketData = ticketService.getTicket(id);
        if (ticketData != null) {
            return new ResponseEntity<>(ticketData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
