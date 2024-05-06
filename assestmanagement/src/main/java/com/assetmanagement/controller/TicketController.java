package com.assetmanagement.controller;

import com.assetmanagement.dto.TicketData;
import com.assetmanagement.service.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {

    @Autowired
    public TicketService ticketService;

    @PostMapping(value = "/complaint", consumes = "multipart/form-data")
    public ResponseEntity<?> createTicket(
            @RequestParam("ticketData") String ticketJson,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        TicketData ticketData = convertJsonToTicketData(ticketJson);
        ticketData.setFileData(file.getBytes());

        try {
            TicketData createdTicket = ticketService.createTicket(ticketData);
            return ResponseEntity.ok(createdTicket);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the ticket: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<TicketData> getTicketById(@PathVariable Long id) {
        TicketData ticketData = ticketService.getTicket(id);
        if (ticketData != null) {
            return new ResponseEntity<>(ticketData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private TicketData convertJsonToTicketData(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, TicketData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing JSON", e);
        }
    }
}
