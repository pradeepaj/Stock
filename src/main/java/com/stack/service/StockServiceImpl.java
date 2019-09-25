package com.stack.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.stack.dto.PriceRequestDTO;
import com.stack.dto.PriceResDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stack.dto.StockDto;
import com.stack.dto.StockResponseDto;
import com.stack.entity.Stock;
import com.stack.respository.StockRepository;


@Service
public class StockServiceImpl implements StockService {

	@Autowired

	private StockPriceCalc stockPriceCalc;
	
	@Autowired
	StockRepository stockRepository;

	@Override
	public PriceResDTO getTotalPrice(PriceRequestDTO priceRequestDTO) {
		Double totalPrice = stockPriceCalc.calculateStock(priceRequestDTO.getQuantity(),
				priceRequestDTO.getUnitPrice());

		PriceResDTO resp = new PriceResDTO();
		resp.setStatus("SUCCESS");
		resp.setMessage("Total price of stack calculated");
		resp.setStatusCode(HttpStatus.OK.value());
		resp.setTotalPrice(totalPrice);
		return resp;

	}

	@Override
	public StockResponseDto getStockList() {
		List<Stock> stockList = stockRepository.findAll();
		List<StockDto> stockDtoList = new ArrayList<>();
		StockResponseDto stockResponseDto = new StockResponseDto();
		for (Stock list : stockList) {
			StockDto stockDto = new StockDto();
			stockDto.setStockId(list.getStockId());
			stockDto.setStockName(list.getStockName());
			stockDto.setUnitPrice(list.getUnitPrice());
			stockDto.setRating(list.getCriselRating());
			stockDtoList.add(stockDto);
		}
		stockResponseDto.setData(stockDtoList);
		stockResponseDto.setMessage("get all stock list");
		stockResponseDto.setStatus("SUCCESS");
		stockResponseDto.setStatusCode(200);
		return stockResponseDto;
	}

}
