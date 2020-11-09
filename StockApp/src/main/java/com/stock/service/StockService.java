package com.stock.service;

import java.util.List;

import com.stock.api.StockDetailApi;
import com.stock.api.StockDetailRequest;

public interface StockService {
	
	public void saveStock(StockDetailApi stockDetailRequest) throws Exception;
	
	public void saveStockDetails(List<StockDetailApi> stockDetailRequests) throws Exception;
	
	public List<StockDetailApi> getAllStockDetails() throws Exception;
	
	public StockDetailApi getStockDetailByStockAndDate(StockDetailRequest stockDetailRequest) throws Exception;

}
