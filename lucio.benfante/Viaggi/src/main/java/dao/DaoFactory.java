package dao;

import java.sql.Connection;

public class DaoFactory implements AutoCloseable {
	
	private OracleConnection oracleConnection;
	private Connection connection;
	
	private DaoFactory() {
		oracleConnection = new OracleConnection();
		connection = oracleConnection.open();
	}

	@Override
	public void close() {
		oracleConnection.close();
	}

	public static DaoFactory getNewInstance() {
		return new DaoFactory();
	}

	public PrenotazioneDao getPrenotazioneDao() {
		return new PrenotazioneDaoImpl(connection);
	}

	public UtenteDao getUtenteDao() {
		return new UtenteDaoImpl(connection);
	}
	
	public ViaggioDao getViaggioDao() {
		return new ViaggioDaoImpl(connection);
	}
}
