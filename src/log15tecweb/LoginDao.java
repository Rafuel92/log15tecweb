package log15tecweb;
import java.sql.*;

public class LoginDao extends Dao {
	public Connection connessione;
	public LoginDao() {
		connessione = super.getDatabaseConnection();
	}
	public ResultSet validate(String name,String pass){
	try{
		PreparedStatement ps=connessione.prepareStatement("select usertype from user where username=\""+name+" \" and password=\""+ pass +"\"");
		ResultSet rs=ps.executeQuery();
		if(rs.next()){ //se trova utenti con quelle credenziali
			System.out.println("ok");
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
}

