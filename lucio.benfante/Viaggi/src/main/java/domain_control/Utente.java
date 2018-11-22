package domain_control;

import java.util.Objects;


public class Utente {
    private String codF;
    private String nome;
    private Prenotazione pren;
    
    public Utente() {}

	public String getCodF() {
		return codF;
	}

	public void setCodF(String codF) {
		this.codF = codF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Prenotazione getPrenotazione() {
		return pren;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.pren = prenotazione;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codF, nome, pren);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Utente))
			return false;
		Utente other = (Utente) obj;
		return Objects.equals(codF, other.codF) && Objects.equals(nome, other.nome)
				&& Objects.equals(pren, other.pren);
	}

	@Override
	public String toString() {
		return "Utente [" + (codF != null ? "codF=" + codF + ", " : "") + (nome != null ? "nome=" + nome + ", " : "")
				+ (pren != null ? "prenotazione=" + pren : "") + "]";
	}
	
	public double costoTotale() {
		
		double costoTot =pren.getnGiorni() * pren.getViaggio().getCosto();

		return costoTot;
		
	}
    
    
}
