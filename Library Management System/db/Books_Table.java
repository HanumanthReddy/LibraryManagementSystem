package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Books_Table {

	private final String userName = "root";
	private final String password = "hanu1048@";
	private final String serverName = "localhost";
	private final int portNumber = 3306;
	private final String dbName = "LMS";
	private final String tableName = "Books";
	
	public Connection getConnection() throws SQLException {
		
            Connection conn = null;
            Properties connectionProps = new Properties();
            connectionProps.put("user", this.userName);
            connectionProps.put("password", this.password);

            conn = DriverManager.getConnection("jdbc:mysql://"+this.serverName+":"+this.portNumber+"/"+this.dbName,connectionProps);
            return conn;
	}

	public boolean executeUpdate(Connection conn, String command) throws SQLException {
		
	    Statement stmt= conn.createStatement();
	    stmt.executeUpdate(command);
	    return true;
	}
	
	public void run() throws SQLException {

		Connection conn = this.getConnection();
		String createString =
                            "CREATE TABLE " + this.tableName + " ( " +
			    "ISBN INTEGER(10) NOT NULL, " +
			    "Book_Name varchar(40) NOT NULL, " +
			    "Book_Author varchar(40) NOT NULL, " +
			    "Category varchar(20) NOT NULL, " +
			    "No_of_Books INTEGER(10) NOT NULL, " +
			    "PRIMARY KEY (ISBN))";
			this.executeUpdate(conn, createString);
		}

	public static void main(String[] args) throws SQLException {
		Books_Table B = new Books_Table();
		B.run();
	}
}