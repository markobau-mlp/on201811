package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection implements AutoCloseable {
	
	private Connection connection;
	
	public OracleConnection() {
		connection = null;}
	
	public Connection open() {
		String driverOracle = "oracle.jdbc.driver.OracleDriver";  //nome classe driver oracle
		   
		   String connOracle ="jdbc:oracle:thin:@localhost:49161:xe"; // nome driver legato al mio Db
		   
		   String nomeUtente = "viaggi"; //nome utente connesisone DB 
		   String pw = "viaggi"; 
		   
		   try {
			Class.forName(driverOracle);
			connection = DriverManager.getConnection(connOracle, nomeUtente, pw);  //configuro la connessione
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} return connection;
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	

	}
	
	   
}
