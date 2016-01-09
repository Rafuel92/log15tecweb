package log15tecweb;


	import java.util.Calendar;
import java.util.Date;
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;
	import java.sql.ResultSet;
import java.sql.SQLException;


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

		public String Stampa_lista_autisti_per_assegnamenti() {
			AutistaDao autista=new AutistaDao();
			ResultSet rs=autista.get_lista_autisti();
			System.out.println("prova");
			//Calcolo la data
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 7);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(cal.getTime());
			String data = format1.format(cal.getTime());
			System.out.println(data);
			//
			int results_counter=0;
			String markup_lista_autisti="";
			markup_lista_autisti += "<table class='amministratore-table table'>";
			markup_lista_autisti += "<thead><tr><th>Nome</th><th>Cognome</th><th>Azione</th></tr></thead>";
			markup_lista_autisti += "<tbody>";
			boolean pagination_needed=false;
			try {
				while(rs.next()){
					System.out.println("PAGINAZIONE N: "+results_counter);
					markup_lista_autisti += "<tr class='paginatore-enabled result-counter-"+(results_counter/5)+"'>";  //DA CANCELLARE PER LA PAGINAZIONE
					if(results_counter > 5){
						System.out.println("PAGINAZIONE SI");
					   	pagination_needed = true;
				    	//markup_lista_autisti += "<tr style='display:none;' class='paginatore-enabled result-counter-"+(results_counter/5)+"'>";
				   } else {
					   	pagination_needed = false;
					   //	markup_lista_autisti += "<tr class='paginatore-enabled result-counter-"+(results_counter/5)+"'>";
				   }
					results_counter++;
					markup_lista_autisti+="<td>"+ rs.getString("nome")+"</td>";
					markup_lista_autisti+="<td>"+ rs.getString("cognome")+"</td>";
					markup_lista_autisti+="<td><a class='approve-link' href='Schedula_Assegnamenti_Giornata_Autista?id_autista="+rs.getString("id")+"&data="+data+"'>Seleziona autista</a></td>";
					markup_lista_autisti+="</tr>";		
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			markup_lista_autisti += "</tbody></table>";
            /*if(pagination_needed){
    			markup_lista_autisti += "<div class='paginatore'><ul>";
    			int pages_number = (results_counter/5)+1;
    			System.out.println("numero_di_pagine"+pages_number);
    			System.out.println("numero_di_elementi"+results_counter);
    			for(int index=1;index<pages_number;index++){
    				markup_lista_autisti += "<li>";
    				int index_to_print = index-1;
    				System.out.println("index_to_print"+index_to_print);
    				System.out.println("index_good"+index);
    				if(index==1){
    				 
    				  markup_lista_autisti += "<span onclick='javascript:pager("+index_to_print+",\"pager-trigger-assegnamenti-generati\")' class='pager-trigger pager-trigger-assegnamenti-generati active'>";
    				  System.out.println("after_first_mk_ass"+markup_lista_autisti);
    				} else {
    				  markup_lista_autisti += "<span onclick='javascript:pager("+index_to_print+",\"pager-trigger-assegnamenti-generati\")' class='pager-trigger pager-trigger-assegnamenti-generati'>";
    				  System.out.println("after_mk_ass"+markup_lista_autisti);
    				}
    				markup_lista_autisti += index;
    				markup_lista_autisti += "</span>";
    				markup_lista_autisti += "</li>";
    			}
    			markup_lista_autisti += "</ul></div>";			
    		}*/
            if(results_counter==0){
            	return "<tbody>Non ci sono Autisti, inserirli nella sezione \"Gestione Autisti\" nella dashboard</tbody>";
            }
			System.out.println(markup_lista_autisti);
			return markup_lista_autisti;
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
		
		public String Stampa_Form_Segnalazione_Problemi(String usernameautista) {
			String Responso = "";
			String Select_list_markup = "";
			AssegnamentiDao Assegnamentidbconnection = new AssegnamentiDao();
			ClienteDao Clientidbconnection = new ClienteDao();
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DAY_OF_MONTH, 7);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(cal.getTime());
			String data = format1.format(cal.getTime());
			System.out.println(data);
			AutistaDao Autistadbconn = new AutistaDao();
			String id_autista = Autistadbconn.get_id_autista_by_username(usernameautista);
			ResultSet ListaAssegnamenti = Assegnamentidbconnection.ReadListaAssegnamentiofAutistaInData(id_autista,data);			
			try {
				System.out.println("okkkbueno");
				Responso += "<form method = 'POST' action='segnala-problema-serverpage'>";
				while(ListaAssegnamenti.next()){
					/*Responso += "<tr>";
					Responso += "<td>"+	Automezzidbconnection.GetTargaAutomezzoById(ListaAssegnamenti.getString("id_automezzo"))+"</td>";
					Responso += "<td>"+	Clientidbconnection.GetNominativoClienteById(ListaAssegnamenti.getString("id_cliente"))+"</td>";
					Responso += "<td>"+	Autistidbconnection.GetNominativoAutistaById(ListaAssegnamenti.getString("id_autista"))+"</td>";
					Responso += "<td>"+	ListaAssegnamenti.getString("data")+"</td>";			
					String link_switch_map = "<a href='client_autista.jsp?id_assegnamento_map=";
					link_switch_map += ListaAssegnamenti.getString("id");
					link_switch_map += "'>Visualizza Percorso</a>";
					Responso += "<td>"+	link_switch_map+"</td>";
					Responso += "</tr>";*/
					String current_option_label = Clientidbconnection.GetNominativoClienteById(ListaAssegnamenti.getString("id_cliente"));
					current_option_label += " - ";
					current_option_label += ListaAssegnamenti.getString("data");
					if(ListaAssegnamenti.getString("Segnalazioni")!= null){
						current_option_label += "(Problema gia' segnalato)";
					}
					
					Select_list_markup += "<option value='"+ListaAssegnamenti.getString("id")+"'>"+current_option_label+"</option>";
				}
				  Responso += "<div class='assegnamenti-field-wrap'>";
				  	Responso += "<label>Seleziona Assegnamento</label>";
				    Responso += "<select name='id_assegnamento'>"+Select_list_markup+"</select>";
				  Responso += "</div>";
				  Responso += "<div class='assegnamenti-field-wrap'>";
				  	Responso += "<div class='testo-segn-lab-wrap'><label>Testo Segnalazione</label></div>";
				    Responso += "<textarea rows='5' cols='50' name='testo_segnalazione'></textarea>";
				  Responso += "</div>";
				  Responso += "<input required name='usernameautista' type='hidden' value='"+usernameautista+"'/>";
				  Responso += "<input class='btn-default btn' type='submit' />";
				Responso += "</form>";
				System.out.println("segnalaprobform");
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


