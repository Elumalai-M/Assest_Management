package com.assetmanagement.repository;

import com.assetmanagement.model.AssetModel;
import com.assetmanagement.model.EmployeeModel;
import com.assetmanagement.model.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketModel,Long> {

    boolean existsByTicketNo(String ticketNo);
    boolean existsByEmployeeAndAsset(EmployeeModel employee, AssetModel asset);

}
