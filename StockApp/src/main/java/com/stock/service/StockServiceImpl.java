package com.stock.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.api.StockDetailApi;
import com.stock.api.StockDetailRequest;
import com.stock.mapper.StockMapper;
import com.stock.model.StockCurrentWeekDetails;
import com.stock.model.StockDetails;
import com.stock.repository.IStockCurrentWeekDetailsRepository;
import com.stock.repository.IStockDetailsRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockMapper stockMapper;

	@Autowired
	IStockDetailsRepository stockDetailsRepository;

	@Autowired
	IStockCurrentWeekDetailsRepository stockCurrentWeekDetailsRepository;

	@Override
	public void saveStockDetails(List<StockDetailApi> stockDetailRequests) throws Exception {
		stockDetailsRepository.saveAll(stockMapper.setStockDetailsEntityList(stockDetailRequests));
	}

	@Override
	public List<StockDetailApi> getAllStockDetails() throws Exception {
		List<StockDetailApi> stockDetailApiList = new ArrayList<>();
		stockDetailApiList.addAll(stockMapper.setStockEntityListToStockDetails(stockDetailsRepository.findAll()));
		stockDetailApiList.addAll(
				stockMapper.setStockEntityListToStockWeeklyDetails(stockCurrentWeekDetailsRepository.findAll()));
		return stockDetailApiList;
	}

	@Override
	public StockDetailApi getStockDetailByStockAndDate(StockDetailRequest stockDetailRequest) throws Exception {
		boolean isCurrentWeek = isDateInCurrentWeek(stockDetailRequest.getDate());
		if (isCurrentWeek) {
			Optional<StockCurrentWeekDetails> stockCurrentWeekDetails = stockCurrentWeekDetailsRepository
					.findById(stockMapper.setStockApiToEntity(stockDetailRequest));
			return stockCurrentWeekDetails.isPresent()
					? stockMapper.mapStockDetailsToStockDetailApi(null, stockCurrentWeekDetails.get())
					: null;
		} else {
			Optional<StockDetails> stockDetail = stockDetailsRepository
					.findById(stockMapper.setStockApiToEntity(stockDetailRequest));
			return stockDetail.isPresent() ? stockMapper.mapStockDetailsToStockDetailApi(stockDetail.get(), null)
					: null;
		}
	}

	@Override
	public void saveStock(StockDetailApi stockDetailRequest) throws Exception {
		boolean isCurrentWeek = isDateInCurrentWeek(stockDetailRequest.getDate());
		if (isCurrentWeek) {
			stockCurrentWeekDetailsRepository.save(stockMapper.mapStockCurrentWeekDetailsToEntity(stockDetailRequest));
		} else {
			stockDetailsRepository.save(stockMapper.mapStockDetailRequestToEntity(stockDetailRequest));
		}
	}

	public static boolean isDateInCurrentWeek(Date date) {
		Calendar currentCalendar = Calendar.getInstance();
		int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
		int year = currentCalendar.get(Calendar.YEAR);
		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setTime(date);
		int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
		int targetYear = targetCalendar.get(Calendar.YEAR);
		return week == targetWeek && year == targetYear;
	}

}
