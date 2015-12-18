import java.sql.SQLException;

public class Autista {
	
	boolean inserimentocorretto;
	public Autista(String username,String email,String password,String nome,String cognome){
		System.out.println("COSTRUTTORE AUTISTA");
		AutistaDao autista=new AutistaDao();
		inserimentocorretto=autista.insert_data(username, email, password, nome, cognome);
		try {
			autista.connessione.close();
		} catch (SQLException e) {
			System.out.println("IMPOSSIBILE CHIUDERE LA CONNESSIONE");
			e.printStackTrace();
		}
	}

}
