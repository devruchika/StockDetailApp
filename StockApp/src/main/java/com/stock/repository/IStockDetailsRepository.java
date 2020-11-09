package com.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.model.Stock;
import com.stock.model.StockDetails;

@Repository
public interface IStockDetailsRepository extends JpaRepository<StockDetails, Stock>{


}
