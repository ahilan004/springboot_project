package com.example.hackathon.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import yahoofinance.Stock;

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

import com.example.hackathon.model.StockWrapper;
import com.example.hackathon.model.Trade;
import com.example.hackathon.model.TradeState;
import com.example.hackathon.model.TradeType;
import com.example.hackathon.repository.StockRepository;
import com.example.hackathon.service.FinanceService;

import javax.validation.Valid;


@RestController
@RequestMapping("/stocks")
public class StockController {
	
	@Autowired
	private StockRepository stockRepository;
	
	@GetMapping("/list")
	public List<Trade> getAll(){
		return stockRepository.findAll();	
		}
	@GetMapping("/buy")
	public List<Trade> getBuy(){
		TradeType TradType = TradeType.valueOf("BUY");
		return stockRepository.findByType(TradType);	
		}
	@GetMapping("/sell")
	public List<Trade> getSell(){
		TradeType TradType = TradeType.valueOf("SELL");
		return stockRepository.findByType(TradType);	
		}
	@RequestMapping(method=RequestMethod.POST, value="/create")
	public Trade create(@Valid @RequestBody Trade stock) {
				stock.setDate(new Date(System.currentTimeMillis()));
		TradeState contactType = TradeState.valueOf("CREATED");
		//TradeType TradType = TradeType.valueOf("BUY");
		stock.setState(contactType);
		//stock.setType(TradType);
		stock.set_id(ObjectId.get());
		 stockRepository.save(stock);
		return stock;
	}

	/*@GetMapping("/date/{date}")
	public List<Trade> getByDate(@PathVariable String date){
		return stockRepository.findByDate(date); 
	}
	@GetMapping("/ticker/{ticker}")
	public List<Trade> getByTicker(@PathVariable String ticker){
		return stockRepository.findByTicker(ticker); 
	}
	
	
	@GetMapping("/price/{symbol}")
	public BigDecimal stockPrice(@PathVariable String symbol) throws IOException {
		FinanceService service=new FinanceService();
        StockWrapper w = service.findStock(symbol);
        return service.findPrice(w);
    }

	
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public String update(@Valid @RequestBody Trade stock, @PathVariable ObjectId id) {
		Trade test = stockRepository.findBy_id(id);
		if(test.getState().equals("CREATED")) {
		stock.set_id(id);
		stockRepository.save(stock);
		return "Successfully updated";
		}
		else
			return "Status already updated";
	}
	@RequestMapping(method=RequetMethod.DELETE, value="/stocks")
	public String deleteAll() {
		stockService.deleteAll();
		return "deleted all stocks";
	}
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public String deleteById(@Valid @PathVariable ObjectId id) {
		stockRepository.delete(stockRepository.findBy_id(id));
		return "Deleted the " + id + "stock";
	}
	*/
}
