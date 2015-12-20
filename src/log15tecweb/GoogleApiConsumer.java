package log15tecweb;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;


public class GoogleApiConsumer {
   private static final String g_maps_url = "https://maps.googleapis.com/maps/api/geocode/json?address=";

   public String GetCoordinateFromIndirizzo(){
	   //via+Pagano+Pozzuoli
	   return g_maps_url;
   }
   
   public String GenerateIframeFromAddress() {
		String Responso = "";
		AssegnamentiDao Assegnamentidbconnection = new AssegnamentiDao();
		ResultSet ListaAssegnamenti = Assegnamentidbconnection.ReadListaAssegnamenti();
		AutomezziDao Automezzidbconnection = new AutomezziDao();
		ClienteDao Clientidbconnection = new ClienteDao();
		AutistaDao Autistidbconnection = new AutistaDao();

		try {
			System.out.println("okkkbueno");
			Boolean passed = false;
			while(ListaAssegnamenti.next() && !passed){
				passed = true;
				String indirizzo_di_partenza = Clientidbconnection.GetIndirizzoPartenzaClienteById(ListaAssegnamenti.getString("id_cliente"));
				String indirizzo_di_arrivo = Clientidbconnection.GetIndirizzoArrivoClienteById(ListaAssegnamenti.getString("id_cliente"));
				String nome_cliente = Clientidbconnection.GetNominativoClienteById(ListaAssegnamenti.getString("id_cliente"));
				indirizzo_di_partenza =  indirizzo_di_partenza.replaceAll(" ", "%20");
				indirizzo_di_arrivo =  indirizzo_di_arrivo.replaceAll(" ", "%20");
				String gmap = "https://www.google.com/maps/embed/v1/directions?origin="+indirizzo_di_partenza+"&destination="+indirizzo_di_arrivo+"&key=AIzaSyATpudtzylAgWqpiQYe-uD-v8dbJ3zexiw";
			    Responso += "<h2>Assegnamento per il cliente : "+nome_cliente+"</h2>";
				Responso += "<iframe width='100%' height='450' frameborder='0' style='border:0' src='"+gmap+"' allowfullscreen></iframe>";
			}
			System.out.println(Responso);
		    System.out.println("Consumer");
		    return Responso;
		} catch (Exception e){
			return Responso;
		}
   }
}
