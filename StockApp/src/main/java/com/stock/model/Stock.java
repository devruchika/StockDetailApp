package com.stock.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Embeddable
public class Stock implements Serializable{
	
	@Column(name = "STOCK", nullable = false)
	private String stock;
	@Column(name = "STOCK_NAME", nullable = false)
	private String stockName;
	@Column(name = "DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

}
