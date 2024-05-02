package com.assetmanagement.repository;

import com.assetmanagement.model.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketModel,Long> {

}
