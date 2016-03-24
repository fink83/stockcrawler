import java.io.IOException;

public class StockCrawler {
	// variables
	static Stock tempStock = new Stock();
	static DbHandler db = null;

	// functions
	public static void main(String[] args) throws IOException {
		connectToDb();
		Parser.parse(tempStock);
		db.establishConnection();
//		db.pushIntoDb(tempStock.date, time, stockprice, sellpercent, holdpercent, buypercent);
//		System.out.println(db.pullOutOfDb("de0008232125"));
		db.closeConnection();
	}

	public static void connectToDb() {
		db = new DbHandler();
	}

}
