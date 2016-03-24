import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHandler {
	static String ConnectURL = "jdbc:mysql://localhost:3306/stockcrawlerdb";
	static String user = "root";
	static String pw = "";
	static Connection conn = null;
	static Statement stmt = null;
	static String query = "";

	public void establishConnection() {
		// Verbindung aufbauen
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.err.println("Treiber konnte nicht geladen werden!");
			System.err.println(e);
			e.printStackTrace();
			System.exit(-1);
		}
		System.out.println("Treiber wurde geladen!");
		try {
			conn = DriverManager.getConnection(ConnectURL, user, pw);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.err.println("Keine Verbindung möglich!");
			e.printStackTrace();
			System.err.println("SQLExecption: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
			System.exit(-1);
		}
	}

	public void closeConnection() {
		// Verbindung schließen
		try {
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Keine Verbindung möglich!");
			e.printStackTrace();
			System.err.println("SQLExecption: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
			System.exit(-1);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void pushIntoDb(String table, String date, String time, BigDecimal stockprice, int sellpercent,
			int holdpercent, int buypercent) {
		// String query = "INSERT INTO `de0008232125` (`date`, `time`,
		// `stockprice`, `sellpercent`, `holdpercent`, `buypercent`) VALUES
		// ('2016-03-05', '00:06:00', '14.14', '10', '10', '80')";

		// Dateneintrag aufbereiten
		query = "INSERT INTO `" + table
				+ "` (`date`, `time`, `stockprice`, `sellpercent`, `holdpercent`, `buypercent`) VALUES ('" + date
				+ "', '" + time + "', '" + stockprice + "', '" + sellpercent + "', '" + holdpercent + "', '"
				+ buypercent + "')";

		// Dateneintrag ausführen
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet pullOutOfDb(String table) {
		// Datenabfrage aufbereiten
		query = "select * from de0008232125";
		// Datenabfrage ausführen
		ResultSet resultset = null;
		try {
			resultset = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Schritt 4: Ergebnismenge verarbeiten
		try {
			while (resultset.next()){
				System.out.println(resultset.getString("id") + "\t" + resultset.getString("date") + "\t" + resultset.getString("time") + "\t"
						+ resultset.getString("stockprice") + "\t" + resultset.getString("sellpercent") + "\t"
						+ resultset.getString("holdpercent") + "\t" + resultset.getString("buypercent"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		};
		return resultset;
	}
}
