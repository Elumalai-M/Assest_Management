package com.assestmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketData {
    private String ticketNo;
    private EmployeeData employee;
    private AssestData asset;
}
