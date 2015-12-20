package log15tecweb;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;


public class gestoreClienti {
	
	public gestoreClienti() {
		
	}
	
	public String getNome(String nome) {
		return nome;
	}
	
	public String getSedeDiPartenza(String sede_di_partenza){
		return sede_di_partenza;
	}
	
	public String getSedeDiArrivo(String sede_di_arrivo){
		return sede_di_arrivo;
	}
	
	public String PrintListaClienti() {
		String Responso = "";
		ClienteDao clientidbconnection = new ClienteDao();
		ResultSet ListaClienti = clientidbconnection.read_list_clienti();
		try {
			Responso += "<tbody>";
			while(ListaClienti.next()){
				Responso += "<tr>";
				Responso += "<td>"+ListaClienti.getString("nome")+"</td>";
				Responso += "<td>"+ListaClienti.getString("sede_di_partenza")+"</td>";
				Responso += "<td>"+ListaClienti.getString("sede_di_arrivo")+"</td>";
				Responso += "<td>"+"<a class='edit-link' href='modifica_clienti.jsp?id_user="+ListaClienti.getString("id_user")+"&id_client="+ListaClienti.getString("id_client")+"&sede_di_partenza="+ListaClienti.getString("sede_di_partenza")+"&sede_di_arrivo="+ListaClienti.getString("sede_di_arrivo")+"&nome="+ListaClienti.getString("nome")+"'>Modifica</a>"+"</td>";
				Responso += "<td>"+"<a class='delete-link' onclick=\"return confirm('Sei sicuro di voler cancellare questo Cliente?')\" href='cancella-cliente-server?id="+ListaClienti.getString("id_client")+"'>Elimina</a>"+"</td>";
				Responso += "</tr>";
			}
			Responso += "</tbody>";
			System.out.println(Responso);
			
			return Responso;
		} catch (Exception e){
			return Responso;
		}
	}
	
	/*private String getDateTime() {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date date = new Date();
	    return dateFormat.format(date);
	}*/
}
