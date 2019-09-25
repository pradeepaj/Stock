package com.stack.service;

import org.springframework.stereotype.Service;

@Service
public class StockPriceCalc {

	private final static double brockarage = 10;

	public Double calculateStock(int quantity, double unitPrice) {

		return unitPrice * quantity + (unitPrice * quantity) / brockarage;

	}

}
