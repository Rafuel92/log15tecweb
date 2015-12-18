
	import java.sql.SQLException;

	public class Cliente {
		
		boolean inserimentocorretto;
		public Cliente(String username,String email,String password,String nome,String cognome,String sede_di_partenza,String sede_di_arrivo){
			System.out.println("COSTRUTTORE CLIENTE");
			ClienteDao cliente=new ClienteDao();
			inserimentocorretto=cliente.insert_data(username, email, password, nome, cognome,sede_di_partenza,sede_di_arrivo);
			try {
				cliente.connessione.close();
			} catch (SQLException e) {
				System.out.println("IMPOSSIBILE CHIUDERE LA CONNESSIONE");
				e.printStackTrace();
			}
		}

	}