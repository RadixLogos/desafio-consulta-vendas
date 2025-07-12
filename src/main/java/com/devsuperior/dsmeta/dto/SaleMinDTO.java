package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleMinDTO {

	private Long id;
	private Double amount;
	private LocalDate date;
	private String sellersName;
	
	public SaleMinDTO(Long id, Double amount, LocalDate date, String sellersName) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.sellersName = sellersName;
	}
	
	public SaleMinDTO(Sale entity) {
		id = entity.getId();
		amount = entity.getAmount();
		date = entity.getDate();
		sellersName = entity.getSeller().getName();
		}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getSellersName() {
		return sellersName;
	}
}
