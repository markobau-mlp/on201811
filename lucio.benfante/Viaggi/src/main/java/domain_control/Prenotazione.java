package domain_control;

import java.util.ArrayList;
import java.util.Objects;

public class Prenotazione {
   private long codPren;
   private int nGiorni;
   private Viaggio viaggio;
   
   private ArrayList<Utente> listaU = new ArrayList<>();
   
   public Prenotazione() {}

public long getCodPren() {
	return codPren;
}

public void setCodPren(long codPren) {
	this.codPren = codPren;
}

public int getnGiorni() {
	return nGiorni;
}

public void setnGiorni(int nGiorni) {
	this.nGiorni = nGiorni;
}

public ArrayList<Utente> getListaU() {
	return listaU;
}

public void setListaU(ArrayList<Utente> listaU) {
	this.listaU = listaU;
}


public Viaggio getViaggio() {
	return viaggio;
}

public void setViaggio(Viaggio viaggio) {
	this.viaggio = viaggio;
}

@Override
public int hashCode() {
	return Objects.hash(codPren, listaU, nGiorni, viaggio);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (!(obj instanceof Prenotazione))
		return false;
	Prenotazione other = (Prenotazione) obj;
	return codPren == other.codPren && Objects.equals(listaU, other.listaU) && nGiorni == other.nGiorni
			&& Objects.equals(viaggio, other.viaggio);
}

@Override
public String toString() {
	return "Prenotazione [codPren=" + codPren + ", nGiorni=" + nGiorni + ", "
			+ (viaggio != null ? "viaggio=" + viaggio + ", " : "") + (listaU != null ? "listaU=" + listaU : "") + "]";
}
   


   
}
