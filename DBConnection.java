
import java.sql.*;

public class DBConnection {

	// Make connection;

	public static Connection getConnected() throws Exception {

		try {

			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/ashish";
			String username = "root";
			String password = "ashish123";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username, password);
			// System.out.println("Connection established");
			return conn;

		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	// Method to create table. three column. 1. question number. 2. question 3.
	// answer varchar(225)

	public static void createTable() throws Exception {
		try {
			Connection con = getConnected();
			String sql = "CREATE TABLE IF NOT EXISTS examination"
					+ "(questionno int, question varchar(225), answer varchar(225))";

			PreparedStatement create = con.prepareStatement(sql);
			create.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);

		} finally {
			System.out.println("Table created");
		}
	}

	// Method to insert question and answer

	public void insert() throws SQLException {

		Statement stmt = null;
		Connection con = null;
		try {
			con = getConnected();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// con = getConnected();
			con.setAutoCommit(false);
			stmt = con.createStatement();

			stmt.addBatch("insert into examination values(1,'Your Name','Ashish')");
			stmt.addBatch("insert into examination values(2,'Your Address','Miami')");
			stmt.addBatch("insert into examination values(3,'Your Country','USA')");

			int[] updateCounts = stmt.executeBatch();
			con.commit();

		} catch (BatchUpdateException b) {
			System.out.println(b);
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			con.setAutoCommit(true);
		}

	}

	// Method to retrive question and answer

	

	// public void retrive( question number){
	// select statement

}