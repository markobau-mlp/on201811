package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import domain_control.Prenotazione;
import domain_control.Utente;



public class UtenteDaoImpl implements UtenteDao {

	private Connection connection;
	
	 public  UtenteDaoImpl(Connection connection) {
			this.connection = connection;
		}
	
	@Override
public int inserisci(Utente utente) {
		
	    
	    String query = "insert into utenti values(?, ?, ?)";
	    
	    //avverra la chiamata al metodo Query
	    int queryOk = -1;
	    
	    
	    try(PreparedStatement st2 = creaQuery(query, utente);){
	         
	    	//esecuzione
	    	connection.setAutoCommit(false); //gestiamo meglio l'errore
	    	
	    	queryOk =  st2.executeUpdate();
	    	
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
	    
	    return queryOk;
	}
	
	//metodo che prepara lo Statement agganciando i valori
	   
	private PreparedStatement creaQuery(String query, Utente utente) throws SQLException {
		PreparedStatement st= connection.prepareStatement(query);
		
		//aggancio i valori al posto dei '?'
		st.setString(1, utente.getCodF());
		st.setString(2, utente.getNome());
		st.setLong(3, utente.getPrenotazione().getCodPren());

		
		return st;
	}

	@Override
    public Utente leggi(String codF) {
        
		Utente utente = null;
		
		String query = "select u.cod_f, u.nome, u.id_pren from utenti u " 
				+ " where u.cod_f = ?";
		
		try( PreparedStatement st= creaQueryLeggi(query, codF);
				ResultSet rs= st.executeQuery();){
		   if(rs.next()) {
			   String nome= rs.getString(2);
			   long codPren = rs.getLong(3);
               
			   PrenotazioneDaoImpl p = new PrenotazioneDaoImpl(connection); 
			   Prenotazione pren = p.leggi(codPren);
			   
			   utente = new Utente();
			   utente.setNome(nome);
			   utente.setPrenotazione(pren);
			   
			   
		   }
		}catch (SQLException e) {
			e.printStackTrace();
		}
				
		
		return utente;
		
	}
	
	private PreparedStatement creaQueryLeggi(String query, String codF) throws SQLException {
		PreparedStatement st= connection.prepareStatement(query);
		
		st.setString(1, codF);
		
		return st;
	}

	@Override
   public Utente aggiorna(String codF, Utente utente) {
		
		//cerco l'oggetto da aggiornare
		Utente old = leggi(codF);
		
		//verifico che esista
		if(old==null) {
			return null;
		}
	 //update
		String call = "{call update_utente(?, ?, ?)}";  // aggiungo prima ?= se ho una function
     
		try(CallableStatement st= creaQueryAgg(call, codF, utente)){
			st.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return old;
		
	}
	
	private CallableStatement creaQueryAgg(String call, String codF, Utente nuovo) throws SQLException {
		CallableStatement st = connection.prepareCall(call);
		
		//registro il parametro risultante dalla procedura
		//st.registerOutParameter(1, Types.NUMERIC);  solo se è una function
		
		
		// registro i parametri in ingresso
		st.setString(1, codF);
		st.setString(2, nuovo.getNome());
		st.setLong(3, nuovo.getPrenotazione().getCodPren());

		
		return st;	
	}

	@Override
	public Utente rimuovi(String codF) {
	       Utente old = leggi(codF);
	       if(old == null) {
	    	   return null;
	       }
	       
	       String query= "delete from utenti where codF = ?";
	       
	       try(PreparedStatement st2 = creaQueryRimuovi(query, codF);){
		         
		    	//esecuzione
		    	connection.setAutoCommit(false); //gestiamo meglio l'errore
		    	
		    	st2.executeUpdate();
		    	
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
	       
	       return old;
		}

	private PreparedStatement creaQueryRimuovi(String query, String codF) throws SQLException {
		PreparedStatement st= connection.prepareStatement(query);
		
		st.setString(1, codF);
		
		return st;
	}

	@Override
	public List<Utente> leggiTutto() {

		return null;
	}
	
	  
			

}
