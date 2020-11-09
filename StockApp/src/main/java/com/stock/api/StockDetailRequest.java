package com.stock.api;

import java.util.Date;

import lombok.Data;

@Data
public class StockDetailRequest {

	private String stock;
	private String stockName;
	private Date date;
}
