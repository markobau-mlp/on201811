package dao;

import java.util.List;

import domain_control.Utente;

public interface UtenteDao {

int inserisci (Utente utente); //C
    
    Utente leggi(String codF); //R
    
    
    
    Utente aggiorna( String codF, Utente utente); //U
     
    Utente rimuovi (String codF); //D

	//Metodi DCS
    
    List<Utente> leggiTutto();
	
}
