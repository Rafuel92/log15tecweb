package log15tecweb;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;


public class gestoreAutomezzi {	
	public gestoreAutomezzi() {
		
	}
	
	public String getAutomezzo(String targa) {
		return targa;
	}
	
	public String getAutomezzoForEdit(String id_automezzo) {
		String Responso = "";
		AutomezziDao Automezzidbconnection = new AutomezziDao();
		ResultSet ListaAutomezzi = Automezzidbconnection.GetDatiAutomezzo(id_automezzo);
		try {
			System.out.println("okkkbueno"+id_automezzo);
			while(ListaAutomezzi.next()){
				Responso += "<input name='targa' class='form-control' placeholder='Targa' required autofocus>";
				Responso += "<input name = 'modello' class='form-control' placeholder='Modello' required>";
				Responso += "<input name = 'data_acquisto' class='form-control' placeholder='Data Acquisto' required>";	
			}
			System.out.println(Responso);
			return Responso;
		} catch (Exception e){
			System.out.println("notbueno");
			return Responso;
		}	
	}
	
	public Boolean getStatoAutomezzo(String targa){
		return false;
	}
	
	public static Boolean getLista_Automezzi() {
		return false;
	}
	
	public Boolean insertAutomezzo(String targa, String modello, String data_acquisto) {
		AutomezziDao Automezzidbconnection = new AutomezziDao();
		return Automezzidbconnection.insert_automezzo(targa, modello, data_acquisto);		
	}
	
	public Boolean modificaAutomezzo(String id_automezzo,String targa, String modello, String data_acquisto) {
		AutomezziDao Automezzidbconnection = new AutomezziDao();
		return Automezzidbconnection.modifica_automezzo(id_automezzo,targa, modello, data_acquisto);			
	}
	
	public String PrintListaAutomezzi() {
		String Responso = "";
		AutomezziDao Automezzidbconnection = new AutomezziDao();
		ResultSet ListaAutomezzi = Automezzidbconnection.ReadListaAutomezzi();
		try {
			System.out.println("okkkbueno");
			Responso += "<tbody>";
			while(ListaAutomezzi.next()){
				Responso += "<tr>";
				Responso += "<td>"+ListaAutomezzi.getString("targa")+"</td>";
				if(ListaAutomezzi.getString("stato_attuale")=="1"){
					Responso += "<td>"+"Disponibile"+"</td>";
				} else {
					Responso += "<td>"+"Non Disponibile"+"</td>";
				}
				String dataAcquistotrimmed = ListaAutomezzi.getString("data_acquisto");
				if(dataAcquistotrimmed.contains(" ")){
					dataAcquistotrimmed= dataAcquistotrimmed.substring(0, dataAcquistotrimmed.indexOf(" ")); 
				}
				Responso += "<td>"+"<a class='edit-link' href='modifica_automezzo.jsp?id_automezzo="+ListaAutomezzi.getString("id")+"&targa="+ListaAutomezzi.getString("targa")+"&modello="+ListaAutomezzi.getString("modello")+"&data_acquisto="+dataAcquistotrimmed+"'>Modifica</a>"+"</td>";
				Responso += "<td>"+"<a class='delete-link' onclick=\"return confirm('Sei sicuro di voler cancellare questo Automezzo?')\" href='cancella-automezzo-server?id_automezzo="+ListaAutomezzi.getString("id")+"'>Elimina</a>"+"</td>";
				Responso += "</tr>";
			}
			Responso += "</tbody>";
			System.out.println(Responso);
			
			return Responso;
		} catch (Exception e){
			return Responso;
		}
	}
}
