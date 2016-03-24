import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Parser {
	
	
	
	
	public static Document getFile() throws IOException {
		// getFile function accepts filename, file is parsed, JSOUP document is
		// returned
		File input = new File("data/DE0008232125.htm");
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		return doc;
	}

	static void parse(Stock tempStock) throws IOException {
		Document LH = getFile();
//		System.out.println(extractSellPercentage(LH));
//		System.out.println(extractHoldPercentage(LH));
//		System.out.println(extractBuyPercentage(LH));
		tempStock.setSellpercent(extractSellPercentage(LH));
		tempStock.setHoldPercent(extractHoldPercentage(LH));
		tempStock.setBuyPercent(extractBuyPercentage(LH));
		tempStock.setStockName(extractStockName(LH));
		tempStock.setStockPrice(extractStockPrice(LH));
	}
	
	private static int extractSellPercentage(Document doc) {
		// Funktion um die Zahl vor dem Prozentzeichen herauszuschneiden, also
		// zum Beispiel "15" aus "<small>Verkaufen (15%)</small>"
		// Verkaufen (Prozent%)
		Element percent_sell;
		percent_sell = doc.select(".analystenbarometer").parents().first().child(3).child(1);
		String sellString = percent_sell.toString().substring(18, percent_sell.toString().length());
		sellString = sellString.substring(0, sellString.length() - 10);
		return Integer.parseInt(sellString);
	}

	private static int extractHoldPercentage(Document doc) {
		// Funktion um die Zahl vor dem Prozentzeichen herauszuschneiden, also
		// zum Beispiel "15" aus "<small>Halten (15%)</small>"
		// Halten (Prozent%)
		Element percent_hold;
		percent_hold = doc.select(".analystenbarometer").parents().first().child(4).child(1);
		String holdString = percent_hold.toString().substring(15, percent_hold.toString().length());
		holdString = holdString.substring(0, holdString.length() - 10);
		return Integer.parseInt(holdString);
	}

	private static int extractBuyPercentage(Document doc) {
		// Funktion um die Zahl vor dem Prozentzeichen herauszuschneiden, also
		// zum Beispiel "15" aus "<small>Kaufen (15%)</small>"
		// Kaufen Prozent
		// Kaufen (Prozent%)
		Element percent_buy;
		percent_buy = doc.select(".analystenbarometer").parents().first().child(6);
		String buyString = percent_buy.toString().substring(15, percent_buy.toString().length());
		buyString = buyString.substring(0, buyString.length() - 10);
		return Integer.parseInt(buyString);
	}

	private static String extractStockName(Document doc) {
		return doc.select(".box60_left > div > h2").first().childNode(0).toString();
	}

	private static BigDecimal extractStockPrice(Document doc) {
		// Element price is string in float format
		String tempStringPrice = doc.select(".box60_left > div > table > tbody > tr > td >div").first().childNode(0).toString().trim();
		// replace , (commata) by . (dot)
		tempStringPrice = tempStringPrice.replace(',', '.');
		// convert string to float
		BigDecimal price = new BigDecimal(tempStringPrice);
		return price;

	}

	// // Uhrzeit und Datum
	// System.out.println(doc.select(".box60_left > div > table > tbody > tr > td").parents().first().child(4));
}
