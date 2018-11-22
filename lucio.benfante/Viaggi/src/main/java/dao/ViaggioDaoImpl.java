package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Types;
import java.util.List;

import domain_control.Viaggio;

public class ViaggioDaoImpl implements ViaggioDao {

	//variabile istanza
	private Connection connection;
	
	//costruttore
	public  ViaggioDaoImpl(Connection connection) {
		this.connection = connection;
	}
	
	
	@Override
	public long inserisci(Viaggio viaggio) {
		
	   long x = -1;  //mi serve in caso di problemi nei try
	    
	    //seleziono cod_viaggio da sequenza
 /*   String query = "select id_viaggio_seq.nextval from dual"; //auto incremento
	    
	    //eseguo la query
	    //se il blocco try serve per creare oggetti che lanciano eccezioni e devono essere chiusi:
	    
	    try(PreparedStatement st = connection.prepareStatement(query);
	   		 ResultSet rs = st.executeQuery();){
	    	if(rs.next()) { 
				 System.out.println(rs.getLong(1));}
	    }catch (SQLException se) {
			 se.printStackTrace();
		 }
	*/
	    //avverra la chiamata al metodo Query 2
	    
	   long newId = -1;
	   try (PreparedStatement seqStmt = connection.prepareStatement("select id_viaggio_seq.nextval from dual");
			ResultSet rsSeq = seqStmt.executeQuery();) {
		rsSeq.next();
		newId = rsSeq.getLong(1);
	   } catch (SQLException sqle) {}
	   viaggio.setCodViaggio(newId);
	   
	   String call= "{call new_viaggio( ?, ?, ?, ?) }";
	    
	    try(CallableStatement st = creaCall(call, viaggio);){
	         
	    	//esecuzione
	    	connection.setAutoCommit(false); //gestiamo meglio l'errore
	    	
	    	x = st.executeUpdate(); //lancia SQL Exception 
	    	
	    	connection.commit();
	    	
	    }catch (SQLException se) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	    	se.printStackTrace();
		 }finally {
			 try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 }
	    
	    return newId;
	}
	
	private CallableStatement creaCall(String call, Viaggio viaggio) throws SQLException {		
		CallableStatement st= connection.prepareCall(call);
		
		//aggancio i valori al posto dei '?'
		
		st.setLong(1, viaggio.getCodViaggio());
		st.setString(2, viaggio.getLocalita());
		st.setString(3, viaggio.getStruttura());
		st.setDouble(4, viaggio.getCosto());
		
		return st;
	}
	
	//metodo che prepara lo Statement agganciando i valori
	   
	private PreparedStatement creaQuery2(String query2, long idViaggio, Viaggio viaggio) throws SQLException {
		
		PreparedStatement st= connection.prepareStatement(query2);
		
		//aggancio i valori al posto dei '?'
		st.setLong(1, idViaggio);
		st.setString(2, viaggio.getLocalita());
		st.setString(3, viaggio.getStruttura());
		st.setDouble(4, viaggio.getCosto());
		
		return st;
	}
	

	@Override
	public Viaggio leggi(long idViaggio) {
         
		Viaggio viaggio= null; //al massimo � un'allocazione di memoria, non un'inizializzazione
		
		String query = "select v.id_viaggio, v.localita, v.struttura, v.costo_cad from viaggi v where v.id_viaggio = ?";
		
		try( PreparedStatement st= creaQueryLeggi(query, idViaggio);
				ResultSet rs= st.executeQuery();){
		   if(rs.next()) {
			   viaggio = new Viaggio();
			   String localita = rs.getString(2);
			   String struttura = rs.getString(3);
			   double prezzo = rs.getDouble(4);
			   
			   viaggio.setCodViaggio(idViaggio);
			   viaggio.setLocalita(localita);
			   viaggio.setCosto(prezzo);
			   viaggio.setStruttura(struttura);
		   }
		}catch (SQLException e) {
			e.printStackTrace();
		}
				
		
		return viaggio;
		
	}
	
	private PreparedStatement creaQueryLeggi(String query, long idViaggio) throws SQLException {
		PreparedStatement st= connection.prepareStatement(query);
		
		st.setLong(1, idViaggio);
		
		return st;
	}

	@Override
	public Viaggio aggiorna(long idViaggio, Viaggio viaggio) {
		
		//cerco l'oggetto da aggiornare
		Viaggio old = leggi(idViaggio);
		
		//verifico che esista
		if(old==null) {
			return null;
		}
	 //update
		String call = "{call update_viaggio(?, ?, ?, ?)}";  // aggiungo prima ?= 
     
		try(CallableStatement st= creaQueryAgg(call, idViaggio, viaggio)){
			st.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return old;
		
	}
	
	private CallableStatement creaQueryAgg(String call, long idViaggio, Viaggio nuovo) throws SQLException {
		CallableStatement st = connection.prepareCall(call);
		
		//registro il parametro risultante dalla procedura
		//st.registerOutParameter(1, Types.NUMERIC);  solo se � una function
		
		
		// registro i parametri in ingresso
		st.setLong(1, idViaggio);
		st.setString(2, nuovo.getLocalita());
		st.setString(3, nuovo.getStruttura());
		st.setDouble(4, nuovo.getCosto());
		
		return st;	
	}

	@Override
	public Viaggio rimuovi(long idViaggio) {
       Viaggio old = leggi(idViaggio);
       if(old == null) {
    	   return null;
       }
       
       String call= "{call remove_viaggio(?)}";
       
       try(CallableStatement st = creaCallRimuovi(call, idViaggio)){
    	   st.execute();
       } catch (SQLException e) {

		e.printStackTrace();
	}
       
       return old;
	}

	private CallableStatement creaCallRimuovi(String call, long idViaggio) throws SQLException {
		CallableStatement st = connection.prepareCall(call);
		st.setLong(1, idViaggio);
		return st;
	}
	
	@Override
	public List<Viaggio> leggiTutto() {
	    return null;
	}

}
