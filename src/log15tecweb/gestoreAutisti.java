package log15tecweb;


	import java.util.Calendar;
import java.util.Date;
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;
	import java.sql.ResultSet;


	public class gestoreAutisti {
		
		public gestoreAutisti() {
			
		}
		
		public String getNome(String nome) {
			return nome;
		}
		
		public String getCognome(String cognome) {
			return cognome;
		}
		
		public static Boolean getStato() {
			return false;
		}
		
		public String PrintGiornataAutistaByUsername(String Username) {
			AutistaDao autistadbconnection = new AutistaDao();
			String Responso = "";
			ResultSet ListaAssegnamenti = autistadbconnection.GetListaAssegnamentiOdierniByUsername(Username);
			AutomezziDao Automezzidbconnection = new AutomezziDao();
			ClienteDao Clientidbconnection = new ClienteDao();
			try {
				System.out.println("okkkbueno");
				Responso += "<div>";
				Boolean passed = false;
				while(ListaAssegnamenti.next()){
					passed = true;
					Responso += "<div class='giornata-autista-element'>";
					Responso += "<div>"+	Automezzidbconnection.GetTargaAutomezzoById(ListaAssegnamenti.getString("id_automezzo"))+"</div>";
					Responso += "<div>"+	Clientidbconnection.GetNominativoClienteById(ListaAssegnamenti.getString("id_cliente"))+"</div>";
					Responso += "<div>"+	ListaAssegnamenti.getString("data")+"</div>";			
					/*String link_switch_map = "<a href='dashboard_amministratore.jsp?id_assegnamento_map=";
					link_switch_map += ListaAssegnamenti.getString("id");
					link_switch_map += "'>Visualizza Percorso</a>";
					Responso += "<div>"+	link_switch_map+"</div>";*/
					Responso += "</div>";
				}
				if(!passed){
					return "Nessun assegnamento per questa giornata";
				}
				Responso += "</div>";
				System.out.println("responso_giornata_autista");
				System.out.println(Responso);			
				return Responso;
			} catch (Exception e){
				return Responso;
			}			
		}
		
		public Boolean insertAutomezzo(String targa, String modello, String data_acquisto) {
			AutomezziDao Automezzidbconnection = new AutomezziDao();
			return Automezzidbconnection.insert_automezzo(targa, modello, data_acquisto);		
		}
		
		public String PrintListaAutisti() {
			String Responso = "";
			AutistaDao autistadbconnection = new AutistaDao();
			ResultSet ListaAutisti = autistadbconnection.read_list_autisti();
			try {
				Responso += "<tbody>";
				while(ListaAutisti.next()){
					System.out.println("Autista num 1");
					Responso += "<tr>";
					Responso += "<td>"+ListaAutisti.getString("username")+"</td>";
					Responso += "<td>"+ListaAutisti.getString("nome")+"</td>";
					Responso += "<td>"+ListaAutisti.getString("cognome")+"</td>";
					/*if(ListaAutisti.getString("targa")==null){
						Responso += "<td>DISPONIBILE</td>";
					} else {
						Responso += "<td>OCCUPATO SU "+ListaAutisti.getString("targa")+"</td>";
					}*/
					Responso += "<td>"+"<a class='edit-link' href='modifica_autisti.jsp?id_user="+ListaAutisti.getString("id")+"&nome="+ListaAutisti.getString("nome")+"&cognome="+ListaAutisti.getString("cognome")+"'>Modifica</a>"+"</td>";
					Responso += "<td>"+"<a class='delete-link' onclick=\"return confirm('Sei sicuro di voler cancellare questo autista?')\" href='cancella-autista-server?id="+ListaAutisti.getString("id")+"&id_autista="+ListaAutisti.getString("id_autista")+"'>Elimina</a>"+"</td>";
					Responso += "</tr>";
				}
				Responso += "</tbody>";
				System.out.println(Responso);
				
				return Responso;
			} catch (Exception e){
				Responso = "lista autisti non disponibile "+e.getMessage();
				return Responso;
			}
		}
		
		/*private String getDateTime() {
		    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    Date date = new Date();
		    return dateFormat.format(date);
		}*/
	}


