package dao;

import java.util.List;

import domain_control.Prenotazione;


public interface PrenotazioneDao {
	
	//Metodi DAO
    long inserisci (Prenotazione pren); //C
    
    Prenotazione leggi(long idPren);  //R
    
    Prenotazione aggiorna( long idPren, Prenotazione pren); //U
     
    Prenotazione rimuovi (long idPren); //D

	//Metodi DCS
    
    List<Prenotazione> leggiTutto();

}
