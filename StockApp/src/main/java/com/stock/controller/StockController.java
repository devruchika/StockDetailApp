package com.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.stock.StockConstants;
import com.stock.api.StockDetailApi;
import com.stock.api.StockDetailRequest;
import com.stock.api.StockDetailResponse;
import com.stock.service.StockService;

@RestController
@RequestMapping("/stockApp")
public class StockController {

	@Autowired
	StockService stockService;

	@GetMapping("/getAllStockDetails")
	public ResponseEntity<List<StockDetailApi>> getAllStockDetails() throws Exception {
		try {
			return ResponseEntity.ok(stockService.getAllStockDetails());
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,
					StockConstants.ISSUE_WHILE_FETCHING_DATA);
		}
	}

	@PostMapping("/getStockDetailByStockAndDate")
	public ResponseEntity<StockDetailApi> getStockDetailByStockAndDate(
			@RequestBody StockDetailRequest stockDetailRequest) throws Exception {
		try {
			StockDetailApi stockDetailApi = stockService.getStockDetailByStockAndDate(stockDetailRequest);
			if (null != stockDetailApi) {
				return ResponseEntity.ok(stockDetailApi);
			} else {
				throw new HttpClientErrorException(HttpStatus.NO_CONTENT, StockConstants.NO_DATA_PRESENT);
			}
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,
					StockConstants.ISSUE_WHILE_FETCHING_DATA);
		}
	}

	@PostMapping("/addStocks")
	@ResponseBody
	public StockDetailResponse addStocks(@RequestBody List<StockDetailApi> stockDetailRequests) {
		StockDetailResponse stockDetailResponse = new StockDetailResponse();
		try {
			stockService.saveStockDetails(stockDetailRequests);
			stockDetailResponse.setStatusCode(HttpStatus.OK);
			stockDetailResponse.setStatusMessage(StockConstants.SUCCESS);
		} catch (Exception e) {
			stockDetailResponse.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY);
			stockDetailResponse.setStatusMessage(e.getMessage());
		}
		return stockDetailResponse;
	}
	
	@PostMapping("/addStock")
	@ResponseBody
	public StockDetailResponse addStock(@RequestBody StockDetailApi stockDetailRequest) {
		StockDetailResponse stockDetailResponse = new StockDetailResponse();
		try {
			stockService.saveStock(stockDetailRequest);
			stockDetailResponse.setStatusCode(HttpStatus.OK);
			stockDetailResponse.setStatusMessage(StockConstants.SUCCESS);
		} catch (Exception e) {
			stockDetailResponse.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY);
			stockDetailResponse.setStatusMessage(e.getMessage());
		}
		return stockDetailResponse;
	}
}
