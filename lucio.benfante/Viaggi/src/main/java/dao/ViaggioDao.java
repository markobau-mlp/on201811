package dao;

import java.util.List;

import domain_control.Viaggio;

public interface ViaggioDao {
		//Metodi DAO
	    long inserisci (Viaggio viaggio); //C
	    
	    Viaggio leggi(long idViaggio);  //R
	    
	    Viaggio aggiorna( long idViaggio, Viaggio viaggio); //U
	     
	    Viaggio rimuovi (long idViaggio); //D
	
		//Metodi DCS
	    
	    List<Viaggio> leggiTutto();
}
