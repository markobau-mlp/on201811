package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class Esempio {
   public static void main (String[]  args) {
	   
	   Scanner in = new Scanner(System.in);
	   
	   //creare la connessione (una a tantum)
	   String driverOracle = "oracle.jdbc.driver.OracleDriver";  //nome classe driver oracle
	   
	   String connOracle ="jdbc:oracle:thin:@127.0.0.1:1522:oracle"; // nome driver legato al mio Db
	   
	   String nomeUtente = "Progetto"; //nome utente connesisone DB 
	   String pw = "Progetto"; //password connessione DB
	   
	   Connection connection= null;
	   
	   try {
		Class.forName(driverOracle);
		connection = DriverManager.getConnection(connOracle, nomeUtente, pw);  //configuro la connessione
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {

		e.printStackTrace();
	}
	
	   
	  //es 1 
	 //String query = "select id_viaggio_seq.nextval from dual";
	 //String query = "select costo_cad from viaggi where id_viaggio =1";
	 String query = "select n_giorni from prenotazioni where id_viaggio =1";
	 
	 PreparedStatement st = null;
	 ResultSet rs = null;
	 
	 try {
		 st = connection.prepareStatement(query); //preparo la query
		 rs = st.executeQuery(); //eseguo la query --> ottengo il result
	 
		 //leggo resultSet
		 if(rs.next()) { // while per scorrere righe
			 System.out.println(rs.getString(1)); // indice colonna
		 }
		 
	 } catch(SQLException se) {
		 se.printStackTrace();
	 }finally {
		 try {
		 rs.close();
		 st.close();
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 
	   
	 //chiude la connessione  
	
	try {
		connection.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   
	 }}}


