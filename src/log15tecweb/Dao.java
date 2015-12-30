package log15tecweb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
	static Connection conn;
	public static Connection getDatabaseConnection() {
		try {
			if(conn!=null && !conn.isClosed()){
				System.out.println("connessione_singleton");
				return conn;
			}
		} catch (SQLException e1) {
			System.out.println("errore_connessione_singleton");
			return null;
		}
		//DATI DATABASE
		String nomedb="log15tecweb";
		String address="localhost";
		String porta="3306";
	    String userDB="admin";
		String passDB="admin";
	    try {
		  Class.forName("com.mysql.jdbc.Driver");
		  conn= DriverManager.getConnection("jdbc:mysql://"+address+":"+porta + "/" + nomedb, userDB, passDB);
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
	
	public static boolean closeDatabaseConnection(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}