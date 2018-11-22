package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleConnection implements AutoCloseable {

	private Connection connection;

	public OracleConnection() {
		connection = null;
	}

	public Connection open() {
		try {
			Properties properties = new Properties();
			properties.load(this.getClass().getClassLoader().getResourceAsStream("/db.properties"));
			String driverOracle = properties.getProperty("jdbc.driver");
			String connOracle = properties.getProperty("jdbc.url"); // nome driver legato al mio Db

			String nomeUtente = properties.getProperty("jdbc.user"); // nome utente connesisone DB
			String pw = properties.getProperty("jdbc.password");;

			Class.forName(driverOracle);
			connection = DriverManager.getConnection(connOracle, nomeUtente, pw); // configuro la connessione
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return connection;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
