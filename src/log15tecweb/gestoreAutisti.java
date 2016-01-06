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
		
		public String PrintGiornataAutistaByUsername(String Username,String data, String mode) {
			AutistaDao autistadbconnection = new AutistaDao();
			String Responso = "";
			ResultSet ListaAssegnamenti = autistadbconnection.GetListaAssegnamentiByUsernameAndDate(Username,data);
			AutomezziDao Automezzidbconnection = new AutomezziDao();
			ClienteDao Clientidbconnection = new ClienteDao();
			try {
				System.out.println("okkkbueno");
				Responso += "<table class='assegnamenti-giornata-autista-table table'>";
				Responso += "<thead><tr><th>Automezzo</th><th>Cliente</th><th>Data</th><th>Azioni</th></tr></thead>";
				Responso += "<tbody>";
				Boolean passed = false;
				while(ListaAssegnamenti.next()){
					passed = true;
					Responso += "<tr>";
					Responso += "<td>"+	Automezzidbconnection.GetTargaAutomezzoById(ListaAssegnamenti.getString("id_automezzo"))+"</td>";
					Responso += "<td>"+	Clientidbconnection.GetNominativoClienteById(ListaAssegnamenti.getString("id_cliente"))+"</td>";
					Responso += "<td>"+	ListaAssegnamenti.getString("data")+"</td>";
					if(mode == "r"){
						String link_switch_map = "<a href='client_autista.jsp?id_assegnamento_map=";
						link_switch_map += ListaAssegnamenti.getString("id");
						link_switch_map += "&username="+Username+"'>Visualizza Percorso</a>";
						Responso += "<td>"+	link_switch_map+"</td>";
						Responso += "</tr>";
					}
				}
				if(!passed){
					return "Nessun assegnamento per questa giornata";
				}
				Responso += "</tbody></table>";
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
		
		public String Print_Giornata_Autista(String mode,String id_autista,String data){
			String Responso = "";
			AssegnamentiDao Assegnamentidbconnection = new AssegnamentiDao();
			ResultSet ListaAssegnamenti = Assegnamentidbconnection.ReadListaAssegnamentiofAutistaInData(id_autista,data);
			AutomezziDao Automezzidbconnection = new AutomezziDao();
			ClienteDao Clientidbconnection = new ClienteDao();
			AutistaDao Autistidbconnection = new AutistaDao();
			try {
				System.out.println("okkkbueno");
				Responso += "<tbody>";
				while(ListaAssegnamenti.next()){
					Responso += "<tr>";
					Responso += "<td>"+	Automezzidbconnection.GetTargaAutomezzoById(ListaAssegnamenti.getString("id_automezzo"))+"</td>";
					Responso += "<td>"+	Clientidbconnection.GetNominativoClienteById(ListaAssegnamenti.getString("id_cliente"))+"</td>";
					Responso += "<td>"+	Autistidbconnection.GetNominativoAutistaById(ListaAssegnamenti.getString("id_autista"))+"</td>";
					Responso += "<td>"+	ListaAssegnamenti.getString("data")+"</td>";			
					String link_switch_map = "<a href='client_autista.jsp?id_assegnamento_map=";
					link_switch_map += ListaAssegnamenti.getString("id");
					link_switch_map += "'>Visualizza Percorso</a>";
					Responso += "<td>"+	link_switch_map+"</td>";
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


