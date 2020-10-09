package com.example.hackathon.repository;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.hackathon.model.Stock;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {

	
	public List<Stock> findByDate(String date);
	public List<Stock> findByTicker(String ticker);
	//public List<Stock> findByPriceRange(double price1, double price2);
 	
 	public Stock findBy_id(ObjectId _id);
}
