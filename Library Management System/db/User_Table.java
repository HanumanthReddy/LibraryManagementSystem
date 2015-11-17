package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class User_Table {

	private final String userName = "root";
	private final String password = "hanu1048@";
	private final String serverName = "localhost";
	private final int portNumber = 3306;
	private final String dbName = "LMS";
	private final String tableName = "User";
	
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
			"Roll_No varchar(10) NOT NULL, " +
			"Name varchar(40) NOT NULL, " +
			"Email varchar(40) NOT NULL, " +
			"Phone_No varchar(10) NOT NULL, " +
		        "Password varchar(40) NOT NULL, " +
		        "PRIMARY KEY (Roll_No))";
			this.executeUpdate(conn, createString);
		}
        
        public static void main(String[] args) throws SQLException {
        
            User_Table U = new User_Table();
            U.run();
        }
}