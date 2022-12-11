package lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ScoreDAO {
	public ScoreDAO() {		
		
		try(Connection con = getConnection()) {
			initTable(con);							//zdes vytvori tabulka
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Score> load() {		
		List<Score> result = new LinkedList<>();
		try(Connection con = getConnection()) {		//
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT score, name FROM score");
			while(rs.next()) { //pokud rs neni vrati false
				int value = rs.getInt(1);
				String name = rs.getString(2);
				result.add(new Score(value, name)); // jeto vse kakraz odpovi za load
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result ;
	}

	public void save(List<Score> highScores) {
		try( Connection con = getConnection()) {
			getConnection().createStatement().execute("DELETE FROM score");
			PreparedStatement pstm = con.prepareStatement("INSERT INTO score (Score, Name) VALUES (?, ?)");	//misto konkretnich hodnot tam otazniky
			for (Score score: highScores) {
				pstm.setInt(1, score.getScore());	// vrode v sql indeksovany od jednicky //nastavi skore
				pstm.setString(2, score.getName());	//nastvi jmeno
				pstm.execute();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void initTable(Connection con) {
		Statement stmt;							
		try {
			stmt = con.createStatement();
			stmt.execute("CREATE TABLE Score ("					//sozdanie tablici sql
					+ "   Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"	// Id typu int nemusi byt nulovy i generue jak identita
					+ "   Score INT NOT NULL,"
					+ "   Name VARCHAR(255),"
					+ "   PRIMARY KEY (Id))"); 		//ten primary key moc nepouziva
		} catch (SQLException e) {
			if(e.getSQLState().equals("X0Y32")) {
				//ignore this code - table exists
				return;
			}
			e.printStackTrace();
		}
		
	}

	private Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:derby:scoreDB;create=true"); //zde kak raz vseshna sueta s databaze
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
