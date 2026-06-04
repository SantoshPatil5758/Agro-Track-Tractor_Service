package com.agrotrack.tractorservice.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date date;

	private Set<WorkType> worktype;

	private String location;

	private BigDecimal rate;

	private BigDecimal area;
	
	private BigDecimal reciveWorkAmount;

	private BigDecimal totalWorkAmount;

	private BigDecimal pendingWorkAmount;

	private Set<WorkStatus> workStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonBackReference(value = "customer-work") // <-- Use the exact same name here
	private CustomerDetails customerDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<WorkType> getWorktype() {
		return worktype;
	}

	public void setWorktype(Set<WorkType> worktype) {
		this.worktype = worktype;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public Set<WorkStatus> getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(Set<WorkStatus> workStatus) {
		this.workStatus = workStatus;
	}

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public BigDecimal getReciveWorkAmount() {
		return reciveWorkAmount;
	}

	public void setReciveWorkAmount(BigDecimal reciveWorkAmount) {
		this.reciveWorkAmount = reciveWorkAmount;
	}

	public BigDecimal getTotalWorkAmount() {
		return totalWorkAmount;
	}

	public void setTotalWorkAmount(BigDecimal totalWorkAmount) {
		this.totalWorkAmount = totalWorkAmount;
	}

	public BigDecimal getPendingWorkAmount() {
		return pendingWorkAmount;
	}

	public void setPendingWorkAmount(BigDecimal pendingWorkAmount) {
		this.pendingWorkAmount = pendingWorkAmount;
	}
	
	
	

}
