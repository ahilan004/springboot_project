package com.example.hackathon.controller;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.hackathon.model.Stock;
import com.example.hackathon.model.TradeState;
import com.example.hackathon.model.TradeType;
import com.example.hackathon.repository.StockRepository;
import javax.validation.Valid;


@RestController
@RequestMapping("/stocks")
public class StockController {
	
	@Autowired
	private StockRepository stockRepository;
	
	@GetMapping("/list")
	public List<Stock> getAll(){
		return stockRepository.findAll();	
		}
	@GetMapping("/date/{date}")
	public List<Stock> getByDate(@PathVariable String date){
		return stockRepository.findByDate(date); 
	}
	@GetMapping("/ticker/{ticker}")
	public List<Stock> getByTicker(@PathVariable String ticker){
		return stockRepository.findByTicker(ticker); 
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/create")
	public Stock create(@Valid @RequestBody Stock stock) {
				stock.setDate(new Date(System.currentTimeMillis()));
		TradeState contactType = TradeState.valueOf("CREATED");
		TradeType TradType = TradeType.valueOf("BUY");
		stock.setState(contactType);
		stock.setType(TradType);
		stock.set_id(ObjectId.get());
		 stockRepository.save(stock);
		return stock;
	}
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public String update(@Valid @RequestBody Stock stock, @PathVariable ObjectId id) {
		Stock test = stockRepository.findBy_id(id);
		if(test.getState().equals("CREATED")) {
		stock.set_id(id);
		stockRepository.save(stock);
		return "Successfully updated";
		}
		else
			return "Status already updated";
	}
	/*@RequestMapping(method=RequetMethod.DELETE, value="/stocks")
	public String deleteAll() {
		stockService.deleteAll();
		return "deleted all stocks";
	}*/
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public String deleteById(@Valid @PathVariable ObjectId id) {
		stockRepository.delete(stockRepository.findBy_id(id));
		return "Deleted the " + id + "stock";
	}
}
