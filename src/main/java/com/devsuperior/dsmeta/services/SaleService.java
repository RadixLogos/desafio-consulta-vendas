package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> getSalesReportByPeriod(String minDate, String maxDate, String sellersName, Pageable pageable){
		LocalDate minDateConv;
		LocalDate maxDateConv;
		if(maxDate.isEmpty()) {
			maxDateConv = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}else
		{
			maxDateConv= LocalDate.parse(maxDate);
		}
		if(minDate.isEmpty()) {
			minDateConv = maxDateConv.minusYears(1L);
		}else {
			minDateConv = LocalDate.parse(minDate);
		}
		var response = repository.searchReportByPeriod(minDateConv,maxDateConv,sellersName,pageable);

		return response.map(SaleMinDTO::new);
	}

	public List<SaleSummaryDTO> getSalesSummaryByPeriod(String minDate, String maxDate){
		LocalDate minDateConv;
		LocalDate maxDateConv;
		if(maxDate.isEmpty()) {
			maxDateConv = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}else
		{
			maxDateConv= LocalDate.parse(maxDate);
		}
		if(minDate.isEmpty()) {
			minDateConv = maxDateConv.minusYears(1L);
		}else {
			minDateConv = LocalDate.parse(minDate);
		}

		return repository.searchSummaryByPeriod(minDateConv,maxDateConv);
	}

}
