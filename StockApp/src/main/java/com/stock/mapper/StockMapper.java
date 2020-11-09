package com.stock.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.stock.api.StockDetailApi;
import com.stock.api.StockDetailRequest;
import com.stock.model.Stock;
import com.stock.model.StockCurrentWeekDetails;
import com.stock.model.StockDetails;

@Component
public final class StockMapper {

	public List<StockDetails> setStockDetailsEntityList(List<StockDetailApi> stockDetailRequests) {
		List<StockDetails> stockDetailList = new ArrayList<>();
		for (StockDetailApi stockDetailRequest : stockDetailRequests) {
			StockDetails stockDetails = mapStockDetailRequestToEntity(stockDetailRequest);
			stockDetailList.add(stockDetails);
		}
		return stockDetailList;
	}

	public StockDetails mapStockDetailRequestToEntity(StockDetailApi stockDetailRequest) {
		StockDetails stockDetails = new StockDetails();
		BeanUtils.copyProperties(stockDetailRequest, stockDetails);
		Stock stock = new Stock();
		stockDetails.setStock(stock);
		stock.setStock(stockDetailRequest.getStock());
		stock.setStockName(stockDetailRequest.getStockName());
		stock.setDate(stockDetailRequest.getDate());
		return stockDetails;
	}

	public List<StockDetailApi> setStockEntityListToStockDetails(List<StockDetails> stockDetailList) {
		List<StockDetailApi> stockDetailRequestList = new ArrayList<>();
		for (StockDetails stockDetails : stockDetailList) {
			stockDetailRequestList.add(mapStockDetailsToStockDetailApi(stockDetails, null));
		}
		return stockDetailRequestList;
	}

	public StockDetailApi mapStockDetailsToStockDetailApi(StockDetails stockDetails,
			StockCurrentWeekDetails stockCurrentWeekDetails) {
		StockDetailApi stockDetailRequest = new StockDetailApi();
		if (null != stockDetails) {
			BeanUtils.copyProperties(stockDetails, stockDetailRequest);
			stockDetailRequest.setStock(stockDetails.getStock().getStock());
			stockDetailRequest.setStockName(stockDetails.getStock().getStockName());
			stockDetailRequest.setDate(stockDetails.getStock().getDate());
		} else {
			BeanUtils.copyProperties(stockCurrentWeekDetails, stockDetailRequest);
			stockDetailRequest.setStock(stockCurrentWeekDetails.getStock().getStock());
			stockDetailRequest.setStockName(stockCurrentWeekDetails.getStock().getStockName());
			stockDetailRequest.setDate(stockCurrentWeekDetails.getStock().getDate());
		}
		return stockDetailRequest;
	}

	public Stock setStockApiToEntity(StockDetailRequest stockDetailRequest) {
		Stock stockEntity = new Stock();
		BeanUtils.copyProperties(stockDetailRequest, stockEntity);
		return stockEntity;
	}

	public StockCurrentWeekDetails mapStockCurrentWeekDetailsToEntity(StockDetailApi stockDetailRequest) {
		StockCurrentWeekDetails stockCurrentWeekDetails = new StockCurrentWeekDetails();
		BeanUtils.copyProperties(stockDetailRequest, stockCurrentWeekDetails);
		Stock stock = new Stock();
		stockCurrentWeekDetails.setStock(stock);
		stock.setStock(stockDetailRequest.getStock());
		stock.setStockName(stockDetailRequest.getStockName());
		stock.setDate(stockDetailRequest.getDate());
		return stockCurrentWeekDetails;
	}

	public List<StockDetailApi> setStockEntityListToStockWeeklyDetails(
			List<StockCurrentWeekDetails> stockCurrentWeekDetailList) {
		List<StockDetailApi> stockDetailRequestList = new ArrayList<>();
		for (StockCurrentWeekDetails stockCurrentWeekDetails : stockCurrentWeekDetailList) {
			stockDetailRequestList.add(mapStockDetailsToStockDetailApi(null, stockCurrentWeekDetails));
		}
		return stockDetailRequestList;
	}

}
