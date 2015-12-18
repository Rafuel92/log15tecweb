import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class GestoreAssegnamenti {
  public List<String> Clienti;
  public List<String> Automezzi;
  public List<String> Autisti;
  public List<String> AssegnamentiInCorso;
  public String StringaAssegnamenti;
  public List<String> ClientiInAttesa;
  public List<String> AutistiLiberi;
  public List<String> AutomezziLiberi;
  public String ClientiInAttesaRaw;
  public String AutistiLiberiRaw;
  public String AutomezziLiberiRaw;
 
  public List<String> GetListaClienti(){
	    ClienteDao clienti=new ClienteDao();
		ResultSet rs=clienti.get_lista_clienti();
		String str="";
		try {
			while(rs.next()){
			str=str+rs.getString("nome")+",";
			}
			if(str.length()>0){
				str=str.substring(0,str.length()-1);//TOGLIE LA VIRGOLA FINALE
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClientiInAttesaRaw = str;
	    List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
      return items;
}

public List<String> GetListaAutomezzi() {
    AutomezziDao automezzi=new AutomezziDao();
	ResultSet rs=automezzi.GetListaAutomezzi();
	String str="";
	try {
		while(rs.next()){
		str=str+rs.getString("targa")+",";
		}
		if(str.length()>0){
			str=str.substring(0,str.length()-1);//TOGLIE LA VIRGOLA FINALE
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	AutomezziLiberiRaw = str;
    List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
  return items;
}

public List<String> GetListaAutisti() {
	    AutistaDao autisti=new AutistaDao();
		ResultSet rs=autisti.GetListaAutisti();
		String str="";
		try {
			while(rs.next()){
			str=str+rs.getString("username")+",";
			}
			if(str.length()>0){
				str=str.substring(0,str.length()-1);//TOGLIE LA VIRGOLA FINALE
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AutistiLiberiRaw = str;
	    List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
      return items;
}
  
  public List<String> GetAssegnamentiInCorso() {
    String str = "Guillizzoni-Coca Cola Spa1-DRYSZO,Rossi-Coca Cola Spa2-DRYSZ2";
	List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
    return items;
  }
  
  public void SetClientiInAttesa() {
	  int i=0;
	  String clienti_in_attesa_raw = "";
		while (i < Clienti.size()) {
			System.out.println("itera e ricerca un assegnamento per "+Clienti.get(i));
			/**
			 *  controllo se questo Cliente ha gia' un assegnamento in corso
			 */
			if(StringaAssegnamenti.toLowerCase().contains(Clienti.get(i).toLowerCase())){
			  System.out.println("assegnamento gia' in corso per"+Clienti.get(i)+"continua");
			  i++;
			  continue;
			}
		    System.out.println("Cliente "+Clienti.get(i)+ "in attesa");
		    clienti_in_attesa_raw+=Clienti.get(i)+",";
			//cliente non ha assegnamenti in corso
			i++;
		}
		System.out.println(clienti_in_attesa_raw);
		clienti_in_attesa_raw = clienti_in_attesa_raw.substring(0, clienti_in_attesa_raw.length()-1);
		System.out.println("Clienti In Attesa" + clienti_in_attesa_raw);
		ClientiInAttesaRaw = clienti_in_attesa_raw;
		List<String> items = Arrays.asList(clienti_in_attesa_raw.split("\\s*,\\s*"));
		ClientiInAttesa = items;
  }

  public void SetAutistiLiberi() {
	  int k=0;
	  String autisti_liberi_raw ="";
		while(k < Autisti.size()){
			System.out.println("Autisti "+Autisti.get(k));
			if(StringaAssegnamenti.toLowerCase().contains(Autisti.get(k).toLowerCase())){
			  System.out.println("assegnamento gia' in corso per "+Autisti.get(k)+"continua");
			  k++;
			  continue;
			}
			autisti_liberi_raw += Autisti.get(k)+",";
			System.out.println("Autista "+Autisti.get(k)+ "libero");
			//autista non ha assegnamenti in corso
			k++;
		}
		autisti_liberi_raw = autisti_liberi_raw.substring(0, autisti_liberi_raw.length()-1);
		AutistiLiberiRaw = autisti_liberi_raw;
		System.out.println("Autisti Liberi" + autisti_liberi_raw);
		List<String> items = Arrays.asList(autisti_liberi_raw.split("\\s*,\\s*"));
		AutistiLiberi = items;  
  }
  
  public void SetAutomezziLiberi() {
		int j = 0;
		String automezzi_liberi_raw ="";
		while(j < Automezzi.size()){
			System.out.println("Automezzo "+Automezzi.get(j));
			if(StringaAssegnamenti.toLowerCase().contains(Automezzi.get(j).toLowerCase())){
			  System.out.println("assegnamento gia' in corso per "+Automezzi.get(j)+"continua");
			  j++;
			  continue;
			}
			System.out.println("Automezzo "+Automezzi.get(j)+ "libero");
			//automezzi non ha assegnamenti in corso
			System.out.println(j);
			System.out.println(Automezzi.size());
			automezzi_liberi_raw += Automezzi.get(j)+",";
			j++;
		}
		System.out.println("Automezzi liberi" + automezzi_liberi_raw);
		automezzi_liberi_raw = automezzi_liberi_raw.substring(0, automezzi_liberi_raw.length()-1);
		AutomezziLiberiRaw = automezzi_liberi_raw;
		System.out.println("Automezzi liberi" + automezzi_liberi_raw);
		List<String> items = Arrays.asList(automezzi_liberi_raw.split("\\s*,\\s*"));
		AutomezziLiberi = items;  
  }

  public GestoreAssegnamenti() {
    Clienti = GetListaClienti();
    Automezzi = GetListaAutomezzi();
    Autisti = GetListaAutisti();
    AssegnamentiInCorso = GetAssegnamentiInCorso();
    StringaAssegnamenti = "Guillizzoni-Coca Cola Spa1-DRYSZ1,Guillizzoni-Coca Cola Spa2-DRYSZ2";
    SetClientiInAttesa();
    SetAutistiLiberi();
    SetAutomezziLiberi();
  }
  public String GeneratePossibiliAssegnamenti() {
	  	System.out.println("GeneratePossibiliAssegnamenti");
	  	System.out.println("AutomezziLiberi "+ AutomezziLiberiRaw);
	  	System.out.println("AutistiLiberi "+AutistiLiberiRaw);
	  	System.out.println("ClientiInAttesa "+ClientiInAttesaRaw);
	  	AssegnamentiDao assegnamentocheck = new AssegnamentiDao();
	  	String markup_assegnamenti = "";
	  	int i = 0;
		int j = 0;
		int k = 0;
		markup_assegnamenti += "<tbody>";
		if(ClientiInAttesa.size() == 0 || AutistiLiberi.size()==0 || AutomezziLiberi.size()==0){
			return "Non ci sono Assegnamenti Possibili";
		}
		while(i < ClientiInAttesa.size()){
			String att_cliente = ClientiInAttesa.get(i);
			while(j < AutistiLiberi.size()){
				String att_autista = AutistiLiberi.get(j);
				while(k < AutomezziLiberi.size()){
					String att_automezzo = AutomezziLiberi.get(k);
					System.out.println("inside_test "+att_automezzo);
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DAY_OF_MONTH, 7);
					SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
					System.out.println(cal.getTime());
					String formatted = format1.format(cal.getTime());
					System.out.println(formatted);
					System.out.println("superdata");
					AutomezziDao SearchAutomezzi= new AutomezziDao();
				    String id_automezzo = SearchAutomezzi.Get_id_AutomezzoByTarga(att_automezzo);
				    ClienteDao SearchCliente = new ClienteDao();
					String id_cliente = SearchCliente.get_id_cliente_by_nome(att_cliente);
				    AutistaDao SearchAutista = new AutistaDao();
				    String id_autista = SearchAutista.get_id_autista_by_username(att_autista);
				    
				    if(assegnamentocheck.CheckIfAssegnamentoHasRejected(id_automezzo,id_cliente,id_autista,formatted)){
						k++;
				    	continue;
				    }
					
					markup_assegnamenti += "<tr>";
					markup_assegnamenti += "<td>"+att_automezzo+"</td>";
					markup_assegnamenti += "<td>"+att_autista+"</td>";
					markup_assegnamenti += "<td>"+att_cliente+"</td>";
					markup_assegnamenti += "<td>"+formatted+"</td>";
					markup_assegnamenti += "<td>"+GenerateApprovaAssegnamentoLink(att_cliente,att_autista,att_automezzo)+"</td>";					
					markup_assegnamenti += "<td>"+GenerateRifiutaAssegnamentoLink(att_cliente,att_autista,att_automezzo)+"</td>";					
					markup_assegnamenti += "</tr>";
					k++;
				}
				j++;
			}
		  i++;
		}
		markup_assegnamenti += "</tbody>";
		System.out.println("markup_assegnamenti_generati");
		
		return markup_assegnamenti;
  }
  
  public String PrintAssegnamentiApprovati() {
		String Responso = "";
		AssegnamentiDao Assegnamentidbconnection = new AssegnamentiDao();
		ResultSet ListaAssegnamenti = Assegnamentidbconnection.ReadListaAssegnamenti();
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
				Responso += "</tr>";
			}
			Responso += "</tbody>";
			System.out.println(Responso);
			
			return Responso;
		} catch (Exception e){
			return Responso;
		}
  }
  
  public String GenerateApprovaAssegnamentoLink(String att_cliente, String att_autista, String att_automezzo){
	AutomezziDao SearchAutomezzi= new AutomezziDao();
    String id_automezzo = SearchAutomezzi.Get_id_AutomezzoByTarga(att_automezzo);
    ClienteDao SearchCliente = new ClienteDao();
	String id_cliente = SearchCliente.get_id_cliente_by_nome(att_cliente);
    AutistaDao SearchAutista = new AutistaDao();
    String id_autista = SearchAutista.get_id_autista_by_username(att_autista);
    if(id_autista!=null && id_cliente!=null &&id_automezzo!=null){
        return "<a class='approve-link' href='approva-assegnamento?id_automezzo="+id_automezzo+"&id_cliente="+id_cliente+"&id_autista="+id_autista+"'>Approva</a>";
    }
    return "Non disponibile";
  }
  
  public String GenerateRifiutaAssegnamentoLink(String att_cliente, String att_autista, String att_automezzo){
	AutomezziDao SearchAutomezzi= new AutomezziDao();
    String id_automezzo = SearchAutomezzi.Get_id_AutomezzoByTarga(att_automezzo);
    ClienteDao SearchCliente = new ClienteDao();
	String id_cliente = SearchCliente.get_id_cliente_by_nome(att_cliente);
    AutistaDao SearchAutista = new AutistaDao();
    String id_autista = SearchAutista.get_id_autista_by_username(att_autista);
    if(id_autista!=null && id_cliente!=null &&id_automezzo!=null){
        return "<a class='rifiuta-link' href='rifiuta-assegnamento?id_automezzo="+id_automezzo+"&id_cliente="+id_cliente+"&id_autista="+id_autista+"'>Rifiuta</a>";
    }
    return "Non disponibile";
  }
}
