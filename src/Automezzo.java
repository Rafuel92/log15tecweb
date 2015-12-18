
public class Automezzo {
	private String targa;
	private String data_acquisto;
	private Boolean stato_attuale;
	
	public Automezzo() {
		setTarga();
		setDataAcquisto();
		setStato();
	}
	
	protected void setTarga() {
		this.targa = "VVVVVV";
	}
	
	protected void setDataAcquisto() {
		this.data_acquisto = "10 Nov 2015";
	}
	
	protected void setStato() {
		this.stato_attuale = false;
	}
	
	public String getTarga() {
		return this.targa;
	}
	
	public String getDataAcquisto() {
		return this.data_acquisto;
	}
	
	public Boolean getStato() {
		return this.stato_attuale;
	}
	
}
