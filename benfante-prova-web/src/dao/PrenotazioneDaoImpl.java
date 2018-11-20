package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import domain_control.Prenotazione;
import domain_control.Viaggio;

  public class PrenotazioneDaoImpl implements PrenotazioneDao {


	  private Connection connection;
		
	  public  PrenotazioneDaoImpl(Connection connection) {
			this.connection = connection;
		}
	
	
	@Override
  public long inserisci(Prenotazione pren) {
		
	    long idPren = -1;
	    
	    
	    String query = "select id_pren_seq.nextval from dual";
	    
	    
	    try(PreparedStatement st = connection.prepareStatement(query);
	   		 ResultSet rs = st.executeQuery();){
	    	if(rs.next()) { 
				 idPren=(rs.getLong(1));}
	    }catch (SQLException se) {
			 se.printStackTrace();
		 }
	    
	    String query2 = "insert into prenotazioni values(?, ?, ?)";
	    
	    //avverra la chiamata al metodo Query 2
	    
	    try(PreparedStatement st2 = creaQuery2(query2, idPren, pren);){
	         
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
	    
	    return idPren;
	}
	
	//metodo che prepara lo Statement agganciando i valori
	   
	private PreparedStatement creaQuery2(String query2, long idPren, Prenotazione pren) throws SQLException {
		PreparedStatement st= connection.prepareStatement(query2);
		
		//aggancio i valori al posto dei '?'
		st.setLong(1, idPren);
		st.setInt(2, pren.getnGiorni());
		st.setLong(3, pren.getViaggio().getCodViaggio());

		
		return st;
	}

	@Override
	public Prenotazione leggi(long idPren) {
        
		Prenotazione p= null;
		
		String query = "select p.id_pren, p.n_giorni, p.id_viaggio from prenotazioni p " 
				+ "where p.id_pren = ?";
		
		try( PreparedStatement st= creaQueryLeggi(query, idPren);
				ResultSet rs= st.executeQuery();){
		   if(rs.next()) {
			   p = new Prenotazione();
               long codPren = rs.getLong(1);
			   int  nGiorni= rs.getInt(2);
			   long codViaggio = rs.getLong(3);
               
			   p.setCodPren(codPren);
			   p.setnGiorni(nGiorni);
			   
			   ViaggioDaoImpl vd = new ViaggioDaoImpl(connection); 
			   Viaggio v = vd.leggi(codViaggio);
			   
			   
			   p.setViaggio(v);
			   
			   
		   }
		}catch (SQLException e) {
			e.printStackTrace();
		}
				
		
		return p;
		
	}
	
	private PreparedStatement creaQueryLeggi(String query, long idPren) throws SQLException {
		PreparedStatement st= connection.prepareStatement(query);
		
		st.setLong(1, idPren);
		
		return st;
	}

	@Override
	public Prenotazione aggiorna(long idPren, Prenotazione pren) {
		
		//cerco l'oggetto da aggiornare
		Prenotazione old = leggi(idPren);
		
		//verifico che esista
		if(old==null) {
			return null;
		}
	 //update
		String call = "{call update_prenotazione(?, ?, ?)}";  // aggiungo prima ?= se ho una function
     
		try(CallableStatement st= creaQueryAgg(call, idPren, pren)){
			st.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return old;
		
	}
	
	private CallableStatement creaQueryAgg(String call, long idPren, Prenotazione nuovo) throws SQLException {
		CallableStatement st = connection.prepareCall(call);
		
		//registro il parametro risultante dalla procedura
		//st.registerOutParameter(1, Types.NUMERIC);  solo se è una function
		
		
		// registro i parametri in ingresso
		st.setLong(1, idPren);
		st.setInt(2, nuovo.getnGiorni());
		st.setLong(3, nuovo.getViaggio().getCodViaggio());

		
		return st;	
	}

	@Override
	public Prenotazione rimuovi(long idPren) {
	       Prenotazione old = leggi(idPren);
	       if(old == null) {
	    	   return null;
	       }
	       
	       String call= "{call remove_prenotazione(?)}";
	       
	       try(CallableStatement st = creaCallRimuovi(call, idPren)){
	    	   st.execute();
	       } catch (SQLException e) {

			e.printStackTrace();
		}
	       
	       return old;
		}

		private CallableStatement creaCallRimuovi(String call, long idPren) throws SQLException {
			CallableStatement st = connection.prepareCall(call);
			st.setLong(1, idPren);
			return st;
		}

	@Override
	public List<Prenotazione> leggiTutto() {

		return null;
	}
	
	 public long id() {
			
		    long idPren = -1;
		    
		    
		    String query = "select id_pren_seq.nextval from dual";
		    
		    
		    try(PreparedStatement st = connection.prepareStatement(query);
		   		 ResultSet rs = st.executeQuery();){
		    	if(rs.next()) { 
					 idPren=(rs.getLong(1));}
		    }catch (SQLException se) {
				 se.printStackTrace();
			 }
	 
		    return idPren;
	 }

}
