package domain_control;

import java.util.ArrayList;
import java.util.Objects;

public class Viaggio {
   private long codViaggio;
   private String localita;
   private String struttura;
   private double costo;
   
   
   private ArrayList<Prenotazione> listaP = new ArrayList<>();
   
   public Viaggio() {}

public long getCodViaggio() {
	return codViaggio;
}

public void setCodViaggio(long idViaggio) {
	this.codViaggio = idViaggio;
}

public String getLocalita() {
	return localita;
}

public void setLocalita(String localita) {
	this.localita = localita;
}

public String getStruttura() {
	return struttura;
}

public void setStruttura(String struttura) {
	this.struttura = struttura;
}

public double getCosto() {
	return costo;
}

public void setCosto(double prezzo) {
	this.costo = prezzo;
}



public ArrayList<Prenotazione> getListaP() {
	return listaP;
}

public void setListaP(ArrayList<Prenotazione> listaP) {
	this.listaP = listaP;
}

@Override
public int hashCode() {
	return Objects.hash(codViaggio, costo, localita, struttura);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (!(obj instanceof Viaggio))
		return false;
	Viaggio other = (Viaggio) obj;
	return codViaggio == other.codViaggio && Double.doubleToLongBits(costo) == Double.doubleToLongBits(other.costo)
			&& Objects.equals(localita, other.localita) && Objects.equals(struttura, other.struttura);
}

@Override
public String toString() {
	return "Viaggio [codViaggio=" + codViaggio + ", " + (localita != null ? "localita=" + localita + ", " : "")
			+ (struttura != null ? "struttura=" + struttura + ", " : "") + "costo=" + costo + "]";
}

   
   
}
