package com.agrotrack.tractorservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agrotrack.tractorservice.entity.CustomerWorkDetails;

@Repository
public interface CustomerWorkDetailsRepository extends JpaRepository<CustomerWorkDetails, Long>{

	CustomerWorkDetails findBycustomerName(String customerName);


}
