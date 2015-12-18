import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.attribute.standard.PrinterLocation;

public class ClienteDao extends Dao{
	public Connection connessione;
	public ClienteDao() {
		connessione = super.getDatabaseConnection();
	}
	
	public ResultSet read_list_clienti(){
		String query="select user.id as id_user,user.nome,clienti.id as id_client,clienti.sede_di_partenza,clienti.sede_di_arrivo from user,clienti where user.id=clienti.user_reference";
		try {
			PreparedStatement ps=connessione.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet get_lista_clienti(){
	   String query="select user.nome from user,clienti where clienti.user_reference=user.id";
	   try {
	    	PreparedStatement ps=connessione.prepareStatement(query);
	    	ResultSet rs=ps.executeQuery();
		    return rs;
	   } catch (SQLException e) {
	        e.printStackTrace();
		    return null;
	   }
	}	
	
	public boolean cancellacliente(String id){
		try {
			System.out.println("CLIENTDAO CANCELLACLIENTE");
			String query = "Select user_reference as id_user FROM clienti WHERE id="+id;
			System.out.println(query);
			PreparedStatement ps=connessione.prepareStatement(query);
			ResultSet rs= ps.executeQuery();
			rs.next();
			String id_user=rs.getString("id_user");
			System.out.println("PRESO ID USER");
			query = "DELETE FROM clienti WHERE id="+id;
			System.out.println(query);
			ps=connessione.prepareStatement(query);
			ps.execute();
			System.out.println("CANCELLATO CLIENTI");
			query = "DELETE FROM user WHERE id="+id_user;
			System.out.println(query);
			ps=connessione.prepareStatement(query);
			ps.execute();
			return true;
		} catch (SQLException e) {
			  String error="Problemi di connessione col DB" + e.getMessage();
			  System.out.println("2"+error);
		} catch(Exception e){
			 String error="Driver JDBC non trovato"+ e.getMessage();
			 System.out.println(error);
		}
		return false;
	}
	
	public String get_id_cliente_by_nome(String nome_cliente){
	    try{
		  PreparedStatement ps=connessione.prepareStatement("select clienti.id FROM clienti JOIN user ON (clienti.user_reference = user.id) WHERE nome=?");
		  ps.setString(1,nome_cliente);
		  ResultSet rs=ps.executeQuery();
		  if(rs.next()){
			System.out.println("ok_ci_sono_automezzi2s");
			String str1 = rs.getString("id");
		    return str1;
		  }
		} catch (SQLException e) {
			  String error="Problemi di connessione col DB" + e.getMessage();
			  System.out.println("2"+error);
		} catch(Exception e){
			 String error="Driver JDBC non trovato"+ e.getMessage();
			 System.out.println(error);
		}
		return null;		
	}
	
	public String GetIndirizzoPartenzaClienteById(String id_cliente){
	    try{
	      String Query = "select clienti.sede_di_partenza FROM clienti WHERE clienti.id="+id_cliente;
	      System.out.println(Query);
		  PreparedStatement ps=connessione.prepareStatement(Query);
		  ResultSet rs=ps.executeQuery();
		  if(rs.next()){
			System.out.println("ok_ci_sono_clients2s");
			String str1 = rs.getString("sede_di_partenza");
		    return str1;
		  }
		} catch (SQLException e) {
			  String error="Problemi di connessione col DB" + e.getMessage();
			  System.out.println("2"+error);
		} catch(Exception e){
			 String error="Driver JDBC non trovato"+ e.getMessage();
			 System.out.println(error);
		}
		return null;		
	}
	
	public String GetIndirizzoArrivoClienteById(String id_cliente){
	    try{
	      String Query = "select clienti.sede_di_arrivo FROM clienti WHERE clienti.id="+id_cliente;
	      System.out.println(Query);
		  PreparedStatement ps=connessione.prepareStatement(Query);
		  ResultSet rs=ps.executeQuery();
		  if(rs.next()){
			System.out.println("ok_ci_sono_clients2s");
			String str1 = rs.getString("sede_di_arrivo");
		    return str1;
		  }
		} catch (SQLException e) {
			  String error="Problemi di connessione col DB" + e.getMessage();
			  System.out.println("2"+error);
		} catch(Exception e){
			 String error="Driver JDBC non trovato"+ e.getMessage();
			 System.out.println(error);
		}
		return null;		
	}
	
	public boolean modificaCliente(String id_user,String id_client,String sede_di_partenza,String sede_di_arrivo,String nome) {
		try{
			String query = "UPDATE clienti "
					+ " SET sede_di_partenza=?"
					+ " , sede_di_arrivo=?"
					+ " WHERE id=?;";
			System.out.println(query);
			PreparedStatement ps=connessione.prepareStatement(query);
			ps.setString(1,sede_di_partenza);
			ps.setString(2,sede_di_arrivo);
			ps.setString(3,id_client);
			ps.execute();
			System.out.println("okbuono");
			query="UPDATE user SET nome=? WHERE id=?";
			ps=connessione.prepareStatement(query);
			ps.setString(1,nome);
			ps.setString(2,id_user);
			ps.execute();
			System.out.println("okbuono2");
			} catch (SQLException e) {
				  String error="Problemi di connessione col DB" + e.getMessage();
				  System.out.println("2"+error);
				  return false;
			} catch(Exception e){
				 String error="Driver JDBC non trovato"+ e.getMessage();
				 System.out.println(error);
				 return false;
			}
		return true;
	}
	
	public boolean insert_data(String username,String email,String password,String nome,String cognome,String sede_di_partenza, String sede_di_arrivo){	
    	String idquery="select max(id) from "; //query che ottiene il valore dell'id maggiore
		try{
			//OTTENGO L'ID DI USER
			System.out.println("ClienteDAO INSERT DATA");
			PreparedStatement ps=connessione.prepareStatement(idquery + "user");
			ResultSet rs=ps.executeQuery();
			System.out.println("QUERY PER OTTENERE ID IN USER FATTA");
			rs.next();
			Long nextidvalue= (rs.getLong(1)+1);   //incremento l'id massimo per ottenere il prossimo id 
			String iduser=nextidvalue.toString();
			//INSERISCO I DATI DEL CLIENTE IN USER
	    	String query="INSERT INTO user value ("+iduser+",\"c\",\""+username+"\",\""+email+"\",\""+password+"\",\""+nome+"\",\""+cognome+"\")";
			ps=connessione.prepareStatement(query);
			ps.executeUpdate();
			System.out.println("INSERITA TUPLA IN USER");
			//OTTENGO L'ID DI CLIENTE
			ps=connessione.prepareStatement(idquery + "clienti");
			rs=ps.executeQuery();
			rs.next();
			nextidvalue= (rs.getLong(1)+1);   //incremento l'id massimo per ottenere il prossimo id 
			String idclienti=nextidvalue.toString();
			System.out.println("ECCO ID CLIENTI: "+ idclienti);
			//INSERISCO I DATI DEL CLIENTE IN CLIENTI
			query="Insert into clienti value("+idclienti+","+iduser+",\""+sede_di_partenza +"\",\""+sede_di_arrivo+"\")";
			System.out.println("ECCO LA QUERY: ");
			System.out.println(query);
			ps=connessione.prepareStatement(query);
			ps.executeUpdate();
			} catch(Exception e){
				System.out.println("ECCO L'ERRORE: "+ e.getMessage());
				return false;
			}
	return true;
	}

	public String GetNominativoClienteById(String id_cliente) {
	    try{
	      String Query = "select nome FROM clienti JOIN user ON (clienti.user_reference = user.id) WHERE clienti.id="+id_cliente;
	      System.out.println(Query);
		  PreparedStatement ps=connessione.prepareStatement(Query);
		  ResultSet rs=ps.executeQuery();
		  if(rs.next()){
			System.out.println("ok_ci_sono_clienti_good2s");
			String str1 = rs.getString("nome");
		    return str1;
		  }
		} catch (SQLException e) {
			  String error="Problemi di connessione col DB" + e.getMessage();
			  System.out.println("2"+error);
		} catch(Exception e){
			 String error="Driver JDBC non trovato"+ e.getMessage();
			 System.out.println(error);
		}
		return null;
	}
}
