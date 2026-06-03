package com.agrotrack.tractorservice.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWorkDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date date;
	
	private Set<WorkType> worktype;
	
	private String customerName;
	
	@NotNull(message = "Mobile number cannot be null")
    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "Mobile number must be exactly 10 digits"
    )
    @Column(nullable = false, unique = true, length = 10)
	private Long mobilenumber;
	
	@Column(unique = true)
	private String email;
	
	private String location;
	
	private BigDecimal rate;
	
	private BigDecimal area;
	
	private BigDecimal reciveAmount;
	
	private BigDecimal totalAmount;
	
	private BigDecimal pendingAmount;
	
	private String description;

	public CustomerWorkDetails() {
	}

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(Long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public BigDecimal getReciveAmount() {
		return reciveAmount;
	}

	public void setReciveAmount(BigDecimal reciveAmount) {
		this.reciveAmount = reciveAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(BigDecimal pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CustomerWorkDetails(Long id, Date date, Set<WorkType> worktype, String customerName,
			@NotNull(message = "Mobile number cannot be null") @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits") Long mobilenumber,
			String email, String location, BigDecimal rate, BigDecimal area, BigDecimal reciveAmount,
			BigDecimal totalAmount, BigDecimal pendingAmount, String description) {
		super();
		this.id = id;
		this.date = date;
		this.worktype = worktype;
		this.customerName = customerName;
		this.mobilenumber = mobilenumber;
		this.email = email;
		this.location = location;
		this.rate = rate;
		this.area = area;
		this.reciveAmount = reciveAmount;
		this.totalAmount = totalAmount;
		this.pendingAmount = pendingAmount;
		this.description = description;
	}

	@Override
	public String toString() {
		return "CustomerWorkDetails [id=" + id + ", date=" + date + ", worktype=" + worktype + ", customerName="
				+ customerName + ", mobilenumber=" + mobilenumber + ", email=" + email + ", location=" + location
				+ ", rate=" + rate + ", area=" + area + ", reciveAmount=" + reciveAmount + ", totalAmount="
				+ totalAmount + ", pendingAmount=" + pendingAmount + ", description=" + description + "]";
	}

	
}
