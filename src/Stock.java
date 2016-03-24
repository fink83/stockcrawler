import java.math.BigDecimal;

public class Stock {
	// variables
	String stockName, url;
	BigDecimal stockPrice;
	int sellPercent, holdPercent, buyPercent;
	Long id;
	
	// functions
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getId() {
	    return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}

	public int getSellPercent() {
		return sellPercent;
	}

	public void setSellpercent(int sellPercent) {
		this.sellPercent = sellPercent;
	}

	public int getHoldPercent() {
		return holdPercent;
	}

	public void setHoldPercent(int holdPercent) {
		this.holdPercent = holdPercent;
	}

	public int getBuyPercent() {
		return buyPercent;
	}

	public void setBuyPercent(int buyPercent) {
		this.buyPercent = buyPercent;
	}
	
}
