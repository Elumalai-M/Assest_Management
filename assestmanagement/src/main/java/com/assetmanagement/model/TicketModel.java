package com.assetmanagement.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
public class TicketModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ticketNo;

    @ManyToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "id")
    private EmployeeModel employee;

    @ManyToOne
    @JoinColumn(name = "asset_id",referencedColumnName = "assetId")
    private AssetModel asset;
}
