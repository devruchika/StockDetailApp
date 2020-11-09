package com.stock.controller;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.stock.api.StockDetailApi;
import com.stock.api.StockDetailResponse;


@TestPropertySource("classpath:application.properties")
@SpringBootTest
public class StockControllerTest {

	@Autowired
	StockController stockController;

	@Test
	public void testGetStockDetailByStockAndDate() throws Exception {
		StockDetailApi stockDetailRequest = new StockDetailApi();
		stockDetailRequest.setStock("AA");
		stockDetailRequest.setStockName("Manulife");
		stockDetailRequest.setDate(new Date());
		stockDetailRequest.setHigh(20.4);
		stockDetailRequest.setLow(10.6);

		// Save StockDetails in Inmemory
		StockDetailResponse stockDetailRespone = stockController.addStock(stockDetailRequest);

		// test saved successfully
		Assert.assertNotNull(stockDetailRespone);
		Assert.assertEquals(stockDetailRespone.getStatusCode(), HttpStatus.OK);

	}

}
