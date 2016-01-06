package log15tecweb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AutistaDao extends Dao{
	public Connection connessione;
	public AutistaDao() {
		connessione = super.getDatabaseConnection();
	}

	public ResultSet GetListaAutisti(){
		try{
			  PreparedStatement ps=connessione.prepareStatement("select username from user,autisti where user.id=autisti.user_reference");
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
	
	public ResultSet GetListaAssegnamentiByUsernameAndDate(String username_autista,String date){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(cal.getTime());
		String formatted = format1.format(cal.getTime());
		if (date!="CURRENT_DATE") {
			formatted = date;
		}
		String id_autista = get_id_autista_by_username(username_autista);
		//query con data odierna
		String query =  "SELECT * FROM assegnamenti WHERE approvato=1 AND data LIKE '%"+formatted+"%' AND id_autista="+id_autista+" ORDER BY data ASC";
		//SELECT * FROM assegnamenti WHERE assegnamenti.data LIKE '%2015-12-29%' AND assegnamenti.id_autista="7" ORDER BY data ASC

		
		System.out.println("query_estrazione_ass_odierni"+query);
		try{
			  PreparedStatement ps=connessione.prepareStatement(query);
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
	
	public boolean modificaAutista(String id,String nome,String cognome) {
		try{
			String query = "UPDATE user "
					+ " SET nome=?"
					+ " , cognome=?"
					+ " WHERE id=?;";
			System.out.println(query);
			PreparedStatement ps=connessione.prepareStatement(query);
			ps.setString(1,nome);
			ps.setString(2,cognome);
			ps.setString(3,id);
			ps.execute();
			System.out.println("MODIFICA AUTISTA EFFETTUATA");
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
	
	public String get_id_autista_by_username(String nome_autista){
	    try{
		  PreparedStatement ps=connessione.prepareStatement("select autisti.id FROM autisti JOIN user ON (autisti.user_reference = user.id) WHERE username=?");
		  ps.setString(1,nome_autista);
		  ResultSet rs=ps.executeQuery();
		  if(rs.next()){
			System.out.println("ok_ci_sono_autisti");
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
	
	public String GetNominativoAutistaById(String id_autista){
	    try{
	      String Query = "select username FROM autisti JOIN user ON (autisti.user_reference = user.id) WHERE autisti.id="+id_autista;
	      System.out.println("algood");
	      System.out.println(Query);
		  PreparedStatement ps=connessione.prepareStatement(Query);
		  ResultSet rs=ps.executeQuery();
		  if(rs.next()){
			System.out.println("ok_ci_sono_autisti");
			String str1 = rs.getString("username");
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
	
	
	public boolean cancellaautista(String id,String id_autista){
		try {
			System.out.println("AUTISTADAO CANCELLAAUTISTA");
			String query = "DELETE FROM autisti WHERE id="+id_autista;
			System.out.println(query);
			PreparedStatement ps=connessione.prepareStatement(query);
			ps.execute();
			System.out.println("CANCELLATO AUTISTA");
			query = "DELETE FROM user WHERE id="+id;
			System.out.println(query);
			ps=connessione.prepareStatement(query);
			ps.execute();
			System.out.println("CANCELLATO USER");
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
	
	
	public ResultSet read_list_autisti(){
		//String query="select autisti.id as id_autista,user.id,user.nome,user.cognome,IFNULL((Select automezzi.targa from assegnamenti,automezzi where autisti.id=assegnamenti.id_autista and automezzi.id=assegnamenti.id_automezzo),null) as targa from user,autisti where user.id=autisti.user_reference;";
		String query="select autisti.id as id_autista,user.id,user.nome,user.cognome,user.username from user,autisti where user.id=autisti.user_reference;";
		try {
			PreparedStatement ps=connessione.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public boolean insert_data(String username,String email,String password,String nome,String cognome){	
    	String idquery="select max(id) from "; //query che ottiene il valore dell'id maggiore
		try{
			//OTTENGO L'ID DI USER
			System.out.println("AUTISTADAO INSERT DATA");
			PreparedStatement ps=connessione.prepareStatement(idquery + "user");
			ResultSet rs=ps.executeQuery();
			System.out.println("QUERY PER OTTENERE ID IN USER FATTA");
			rs.next();
			Long nextidvalue= (rs.getLong(1)+1);   //incremento l'id massimo per ottenere il prossimo id 
			String iduser=nextidvalue.toString();
			System.out.println("OTTENUTO ID PER TABELL AUTISTI");
			//INSERISCO I DATI DELL'AUTISTA IN USER
	    	String query="INSERT INTO user value ("+iduser+",\"d\",\""+username+"\",\""+email+"\",MD5(\""+password+"\"),\""+nome+"\",\""+cognome+"\")";
			ps=connessione.prepareStatement(query);
			ps.executeUpdate();
			System.out.println("INSERITA TUPLA IN USER");
			//OTTENGO L'ID DI AUTISTI
			ps=connessione.prepareStatement(idquery + "autisti");
			rs=ps.executeQuery();
			rs.next();
			nextidvalue= (rs.getLong(1)+1);   //incremento l'id massimo per ottenere il prossimo id 
			String idautisti=nextidvalue.toString();
			//INSERISCO I DATI DELL'AUTISTA IN AUTISTI
			query="Insert into autisti value("+idautisti+","+iduser+")";
			ps=connessione.prepareStatement(query);
			ps.executeUpdate();
			} catch(Exception e){
				System.out.println("ECCO L'ERRORE: "+ e.getMessage());
				return false;
			}
	return true;
	}
}
