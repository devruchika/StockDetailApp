package com.stock.api;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class StockDetailResponse {
	
	private HttpStatus statusCode;
	private String statusMessage;

}
