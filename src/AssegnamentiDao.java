import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AssegnamentiDao extends Dao {
	public Connection connessione;
	public AssegnamentiDao() {
		connessione = super.getDatabaseConnection();
	}

	public ResultSet ReadListaAssegnamenti(){
	    try{
	    	
	      
		  PreparedStatement ps=connessione.prepareStatement("select * from assegnamenti WHERE assegnamenti.approvato=1 ORDER BY assegnamenti.data DESC");
		  ResultSet rs=ps.executeQuery();
		  if(null!= rs){
			System.out.println("ok_ci_sono_automezzi2");
		    return rs;
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
	
	public Boolean inserisciAssegnamento(String approvato,String data, String id_cliente, String id_automezzo, String id_autista){
	try{
		String query = "INSERT INTO assegnamenti "
				+ "(`id`, `approvato`, `data`, `id_cliente`, `id_automezzo`,`id_autista`)"
				+ "VALUES ("
				+ "NULL, ?, ?, ?, ?, ?)";
		System.out.println(query);
		PreparedStatement ps=connessione.prepareStatement(query);
		ps.setString(1, approvato);
		ps.setString(2, data);
		ps.setString(3, id_cliente);
		ps.setString(4, id_automezzo);
		ps.setString(5, id_autista);
		ps.execute();
		System.out.println("okbuono");
		return true;
		} catch (SQLException e) {
			  String error="Problemi di connessione col DB" + e.getMessage();
			  System.out.println("2"+error);
		} catch(Exception e){
			 String error="Driver JDBC non trovato"+ e.getMessage();
			 System.out.println(error);
		}
		return null;
	}
	
	public Boolean modifica_assegnamento(String id_assegnamento, String targa,String modello, String data_acquisto){
	try{
		String query = "UPDATE Assegnamenti "
				+ " SET targa=?"
				+ " , modello=?"
				+ " , data_acquisto=?"
				+ " WHERE id=?;";
		System.out.println(query);
		PreparedStatement ps=connessione.prepareStatement(query);
		ps.setString(1,targa);
		ps.setString(2, modello);
		ps.setString(3, data_acquisto);
		ps.setString(4, id_assegnamento);
		ps.execute();
		System.out.println("okbuono");
		return true;
		} catch (SQLException e) {
			  String error="Problemi di connessione col DB" + e.getMessage();
			  System.out.println("2"+error);
		} catch(Exception e){
			 String error="Driver JDBC non trovato"+ e.getMessage();
			 System.out.println(error);
		}
		return null;
	}

	public String RejectedAssegnamenti(String data) {
	    try{
	      String Query = "select * from assegnamenti WHERE ";
	      Query += "assegnamenti.approvato=0 AND ";
	      Query += "assegnamenti.data="+data;
	      System.out.println("check");
	      System.out.println(Query);
		  PreparedStatement ps=connessione.prepareStatement(Query);
		  ResultSet rs=ps.executeQuery();
		  String str = "";
			try {
				while(rs.next()){
				str=str+rs.getString("targa")+",";
				}
				if(str.length()>0){
					str=str.substring(0,str.length()-1);//TOGLIE LA VIRGOLA FINALE
				}
				System.out.println("assegnamenti_rigettati_1 "+str);
				return str;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		  
		} catch (SQLException e) {
			  String error="Problemi di connessione col DB" + e.getMessage();
			  System.out.println("2"+error);
		} catch(Exception e){
			 String error="Driver JDBC non trovato"+ e.getMessage();
			 System.out.println(error);
		}
	    

	    
		return "";
	}

	public boolean CheckIfAssegnamentoHasRejected(String id_automezzo, String id_cliente, String id_autista, String data) {
	    try{
	      String Query = "select * from assegnamenti WHERE ";
	     // Query += "assegnamenti.approvato=0 AND ";
	      Query += "assegnamenti.id_cliente="+id_cliente+" AND ";
	      Query += "assegnamenti.id_autista="+id_autista+" AND ";
	      Query += "assegnamenti.id_automezzo="+id_automezzo+" AND ";

	      Query += "assegnamenti.data LIKE '%"+data+"%'";
	      System.out.println("check");
	      System.out.println(Query);
		  PreparedStatement ps=connessione.prepareStatement(Query);
		  ResultSet rs=ps.executeQuery();
		if(rs.next()){
			System.out.println("assegnamento_rigettato");
			return true;
		}
		  
		} catch (SQLException e) {
			  String error="Problemi di connessione col DB" + e.getMessage();
			  System.out.println("2"+error);
		} catch(Exception e){
			 String error="Driver JDBC non trovato"+ e.getMessage();
			 System.out.println(error);
		}
	    

	    
		return false;
	}
}

