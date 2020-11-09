package com.stock.api;

import java.util.Date;

import lombok.Data;

@Data
public class StockDetailApi {
	
	private String stock;
	private String stockName;
	private Date date;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Integer volume;
	private Integer quarter;
	private Double percentChangePrice;
	private Double percentChangeVolumeOverLastWeek;
	private Integer previousWeekVolume;
	private Double nextWeekOpen;
	private Double nextWeekClose;
	private Double percentChangeNextWeekPrice;
	private Integer daysToNextDividend;
	private Double percentReturnNextDividend;

}
