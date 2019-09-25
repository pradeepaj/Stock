package com.stack.service;

import org.springframework.stereotype.Service;

import com.stack.dto.PriceRequestDTO;
import com.stack.dto.PriceResDTO;
import com.stack.dto.StockResponseDto;


@Service
public interface StockService {

	StockResponseDto getStockList();

	PriceResDTO getTotalPrice(PriceRequestDTO priceRequestDTO);

}
