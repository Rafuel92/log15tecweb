package log15tecweb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AssegnamentiDao extends Dao {
	public Connection connessione;
	public AssegnamentiDao() {
		connessione = super.getDatabaseConnection();
	}
	
	
	public int get_num_assegnamenti(String id_autista,Calendar cal){
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(cal.getTime());
		String formatted = format1.format(cal.getTime());
		ResultSet rs=null;
		try{
			  PreparedStatement ps=connessione.prepareStatement("select count(*) as tot from assegnamenti WHERE assegnamenti.id_autista="+id_autista+" and DATE_FORMAT(assegnamenti.data,'%Y-%m-%d')='"+formatted+"' And assegnamenti.approvato=1");
			  rs=ps.executeQuery();
			  if(rs.next()){
				int n=Integer.parseInt(rs.getString("tot"));
				System.out.println("RISULTATO GET NUM ASSEGNAMENTI;" +n);
			    return n;
			  }
		    }catch (Exception e){e.printStackTrace();}
		return 0;
	}
	
	
	

	public ResultSet ReadListaAssegnamenti(){
	    try{
		  PreparedStatement ps=connessione.prepareStatement("select * from assegnamenti WHERE assegnamenti.approvato=1 ORDER BY assegnamenti.data DESC");
		  ResultSet rs=ps.executeQuery();
		  if(null!= rs){
			System.out.println("ok_ci_sono_assegnamenti_buoni");
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

	public ResultSet ReadListaAssegnamentiofAutistaInData(String id_autista,String data){
	    try{
	      String Query = "select * from assegnamenti WHERE assegnamenti.approvato=1 AND id_autista="+id_autista+ " AND data LIKE '%"+data+"%' ORDER BY assegnamenti.data DESC";
	      System.out.println("read_lista_assegnamenti_in_data_query");
	      System.out.println(Query);
		  PreparedStatement ps=connessione.prepareStatement(Query);
		  ResultSet rs=ps.executeQuery();
		  if(null!= rs){
			System.out.println("ok_ci_sono_assegnamenti_buoni");
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
	
	public ResultSet GetAssegnamentoById(String id_assegnamento){
	    try{
	      String Query = "select * from assegnamenti WHERE id="+id_assegnamento;
		  PreparedStatement ps=connessione.prepareStatement(Query);
		  ResultSet rs=ps.executeQuery();
		  if(null!= rs){
			System.out.println("ok_ci_sono_assegnamenti_buoni");
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
	      Query += "assegnamenti.approvato=0 AND ";
	      Query += "assegnamenti.id_cliente="+id_cliente+" AND ";
	      Query += "assegnamenti.id_autista="+id_autista+" AND ";
	      Query += "assegnamenti.id_automezzo="+id_automezzo+" AND ";
          Query +="DATE_FORMAT(assegnamenti.data,'%Y-%m-%d-%H-%i')='"+data+"'";
	      //Query += "assegnamenti.data LIKE '%"+data+"%'";
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


	public Boolean AggiornaAssegnamentoConSegnalazione(String id_assegnamento, String testo_segnalazione) {
	    try{ 
		      String Query = "UPDATE assegnamenti SET Segnalazioni = '"+testo_segnalazione+"' WHERE assegnamenti.id = '"+id_assegnamento+"'";
		      //Query += "assegnamenti.data LIKE '%"+data+"%'";
		      System.out.println("check_assegnamento_con_segnalazione");
		      System.out.println(Query);
			  PreparedStatement ps=connessione.prepareStatement(Query);
			  int rs=ps.executeUpdate();
			  System.out.println("result_ass_con_segnalazione"+rs);
			if(rs >= 1){
				System.out.println("assegnamento_aggiornato_problema_segnalato");
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

