package com.assestmanagement.repository;

import com.assestmanagement.model.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketModel,Long> {

}
