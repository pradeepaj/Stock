package com.stack.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stack.dto.PriceRequestDTO;
import com.stack.dto.PriceResDTO;
import com.stack.service.StockService;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestCase.class, StackController.class })
@WebAppConfiguration
public class StackControllerTest {

	@InjectMocks
	private StackController stackController;
	@Mock
	private StockService stockService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(stackController).build();
	}

	@Test
	public void getTotalPriceTest() throws JsonProcessingException, Exception {
		PriceResDTO resp = new PriceResDTO();
		resp.setMessage("Valid OTP");
		resp.setStatus("SUCCESS");
		resp.setStatusCode(HttpStatus.OK.value());
		resp.setTotalPrice(10000);

		PriceRequestDTO req = new PriceRequestDTO();
		req.setQuantity(4);
		req.setStockId(1);
		req.setUnitPrice(100);

		ResponseEntity<PriceResDTO> expResult = new ResponseEntity<>(resp, HttpStatus.OK);
		when(stockService.getTotalPrice(Mockito.anyObject())).thenReturn(resp);
		mockMvc.perform(
				post("/stock/api/totalPrice", req).contentType(MediaType.APPLICATION_JSON).content(asJsonString(req)))
				.andReturn();
		ResponseEntity<PriceResDTO> actResult = stackController.getTotalPrice(req);
		assertEquals(expResult, actResult);

	}

	public static String asJsonString(final Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);

	}

}
