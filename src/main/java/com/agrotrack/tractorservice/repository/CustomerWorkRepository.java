package com.agrotrack.tractorservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotrack.tractorservice.entity.CustomerDetails;
import com.agrotrack.tractorservice.entity.WorkDetails;


@Repository
public interface CustomerWorkRepository extends JpaRepository<CustomerDetails, Long>{

	CustomerDetails findByCustomerName(String customerName);

	CustomerDetails deleteByCustomerName(String customerName);

	CustomerDetails save(WorkDetails workToUpdate);

}
