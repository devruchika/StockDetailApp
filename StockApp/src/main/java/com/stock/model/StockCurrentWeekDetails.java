package com.stock.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "STOCK_CURRENT_WEEK_DETAILS")
public class StockCurrentWeekDetails {
	@EmbeddedId
	private Stock stock;
	@Column(name = "OPEN")
	private Double open;
	@Column(name = "HIGH")
	private Double high;
	@Column(name = "LOW")
	private Double low;
	@Column(name = "CLOSE")
	private Double close;
	@Column(name = "VOLUME")
	private Integer volume;
	@Column(name = "QUARTER")
	private Integer quarter;
	@Column(name = "PERCENT_CHANGE_PRICE")
	private Double percentChangePrice;
	@Column(name = "PERCENT_CHANGE_VOLUME_OVER_LAST_WEEK")
	private Double percentChangeVolumeOverLastWeek;
	@Column(name = "PREVIOUS_WEEK_VOLUME")
	private Integer previousWeekVolume;
	@Column(name = "NEXT_WEEK_OPEN")
	private Double nextWeekOpen;
	@Column(name = "NEXT_WEEK_CLOSE")
	private Double nextWeekClose;
	@Column(name = "PERCENT_CHANGE_NEXT_WEEK_PRICE")
	private Double percentChangeNextWeekPrice;
	@Column(name = "DAYS_TO_NEXT_DIVIDEND")
	private Integer daysToNextDividend;
	@Column(name = "PERCENT_RETURN_NEXT_DIVIDEND")
	private Double percentReturnNextDividend;

}
