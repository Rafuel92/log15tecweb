package log15tecweb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
	public static Connection getDatabaseConnection() {
		//DATI DATABASE
		String nomedb="log15tecnweb";
		String address="localhost";
		String porta="3306";
	    String userDB="root";
		String passDB="root";
	    try {
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection conn= DriverManager.getConnection("jdbc:mysql://"+address+":"+porta + "/" + nomedb, userDB, passDB);
		  return conn;
	    } catch (SQLException e) {
		  String error="Problemi di connessione col DB" + e.getMessage();
		  System.out.println("2"+error);
	    } catch(Exception e){
		 String error="Driver JDBC non trovato"+ e.getMessage();
		 System.out.println("3"+error);
	     }
	     return null;
	}
}