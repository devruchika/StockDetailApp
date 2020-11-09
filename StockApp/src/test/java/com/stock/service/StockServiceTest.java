package com.stock.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.stock.api.StockDetailApi;
import com.stock.model.StockDetails;
import com.stock.repository.IStockDetailsRepository;

@SpringBootTest
public class StockServiceTest {

	@Mock
	StockService stockService;

	@Mock
	IStockDetailsRepository stockDetailsRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveStockDetailsTest() throws Exception {
		StockDetailApi stockDetailRequest = new StockDetailApi();
		stockDetailRequest.setStock("AA");
		stockDetailRequest.setStockName("Manulife");
		stockDetailRequest.setDate(new Date());
		stockDetailRequest.setHigh(20.4);
		stockDetailRequest.setLow(10.6);
		List<StockDetails> StockDetails = new ArrayList<>();
		List<StockDetailApi> StockDetailApi = new ArrayList<>();
		StockDetailApi.add(stockDetailRequest);
		when(stockDetailsRepository.saveAll(Mockito.any())).thenReturn(StockDetails);
		stockService.saveStockDetails(StockDetailApi);
		verify(stockService, times(1)).saveStockDetails(StockDetailApi);
	}
}
