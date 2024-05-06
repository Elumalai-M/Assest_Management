package com.assetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketData {

    private String ticketNo;
    private String employeeId;
    private String assetName;
    private String description;
    private byte[]  fileData;
}

