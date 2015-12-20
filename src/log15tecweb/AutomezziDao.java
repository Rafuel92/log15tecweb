package log15tecweb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AutomezziDao extends Dao {
	public Connection connessione;
	public AutomezziDao() {
		connessione = super.getDatabaseConnection();
	}

	public ResultSet GetListaAutomezzi(){
		try{
			  PreparedStatement ps=connessione.prepareStatement("select targa from automezzi");
			  ResultSet rs=ps.executeQuery();
			  return rs;
			} catch (SQLException e) {
				  String error="Problemi di connessione col DB" + e.getMessage();
				  System.out.println("2"+error);
			} catch(Exception e){
				 String error="Driver JDBC non trovato"+ e.getMessage();
				 System.out.println(error);
			}
		return null;
	}
	
	public ResultSet ReadListaAutomezzi(){
	    try{
		  PreparedStatement ps=connessione.prepareStatement("select * from automezzi");
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
	
	public ResultSet GetDatiAutomezzo(String id_automezzo){
	    try{
		  PreparedStatement ps=connessione.prepareStatement("select * from automezzi WHERE id=?");
		  ps.setString(1,id_automezzo);
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
	
	public String Get_id_AutomezzoByTarga(String targa){
	    try{
		  PreparedStatement ps=connessione.prepareStatement("select id from automezzi WHERE targa=?");
		  ps.setString(1,targa);
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
	
    public String GetTargaAutomezzoById(String id_automezzo){
	    try{	    	
	      System.out.println("GetTargaStarted" + id_automezzo);
	      String Query = "select targa from automezzi WHERE id ="+id_automezzo;
	      System.out.println("aah");
	      System.out.println(Query);
		  PreparedStatement ps=connessione.prepareStatement(Query);
		  ResultSet rs=ps.executeQuery();
		  if(rs.next()){
			System.out.println("ok_ci_sono_automezzi2s");
			String str1 = rs.getString("targa");
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
	
	public Boolean CancellaAutomezzo(String identificativo){
		try {
			String query = "DELETE FROM automezzi WHERE id="+identificativo;
			System.out.println("query_cancellazione_automezzo");
			System.out.println(query);
			PreparedStatement ps=connessione.prepareStatement(query);
			ps.execute();
			return true;
		} catch (SQLException e) {
			  String error="Problemi di connessione col DB" + e.getMessage();
			  System.out.println("2automezzodelete"+error);
		} catch(Exception e){
			 String error="Driver JDBC non trovato"+ e.getMessage();
			 System.out.println("2automezzodelete"+error);
		}
		return false;
	}
	
	public Boolean insert_automezzo(String targa,String modello, String data_acquisto){
	try{
		String query = "INSERT INTO automezzi "
				+ "(`id`, `targa`, `stato_attuale`, `modello`, `data_acquisto`)"
				+ "VALUES ("
				+ "NULL, ?, '1', ?, ?)";
		System.out.println(query);
		PreparedStatement ps=connessione.prepareStatement(query);
		ps.setString(1,targa);
		ps.setString(2, modello);
		ps.setString(3, data_acquisto+" 00:00:00");
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
	
	public Boolean modifica_automezzo(String id_automezzo, String targa,String modello, String data_acquisto){
	try{
		String query = "UPDATE automezzi "
				+ " SET targa=?"
				+ " , modello=?"
				+ " , data_acquisto=?"
				+ " WHERE id=?;";
		System.out.println(query);
		PreparedStatement ps=connessione.prepareStatement(query);
		ps.setString(1,targa);
		ps.setString(2, modello);
		ps.setString(3, data_acquisto);
		ps.setString(4, id_automezzo);
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
}

