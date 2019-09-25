package com.stack.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;


import com.stack.dto.PriceRequestDTO;
import com.stack.dto.PriceResDTO;
@RunWith(MockitoJUnitRunner.class)
public class StockServiceImplTest {
	@InjectMocks
	private StockServiceImpl stockService;
	
	@Mock
	private StockPriceCalc stockPriceCalc;
	
	@Test
	public void getTotalPriceTest() {
		PriceResDTO resp = new PriceResDTO();
		resp.setMessage("Valid OTP");
		resp.setStatus("SUCCESS");
		resp.setStatusCode(HttpStatus.OK.value());
		resp.setTotalPrice(10000);

		PriceRequestDTO req = new PriceRequestDTO();
		req.setQuantity(4);
		req.setStockId(1);
		req.setUnitPrice(100);
		Mockito.when(stockPriceCalc.calculateStock(req.getQuantity(), req.getUnitPrice())).thenReturn(1000.0);
		PriceResDTO actRes=stockService.getTotalPrice(req);
	}

}
