package Service;


import Model.data_struct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Build_Data_Struct {

	private data_struct struct;

	public Build_Data_Struct(data_struct s) {
		struct=s;
		try {
			uploadFromSql();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void uploadFromSql() throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:h2:~/AutoComplete", "sa", "");
		var stm = conn.createStatement();
		var rs = stm.executeQuery( "SELECT word FROM words");

		while(rs.next()) { 

			String word = rs.getString("word"); 
			struct.add(word);           
		}	
		conn.close();
	}

	
	public String[] getWords(String word) {

		return struct.get_words(word);
	}
	
	
	public List<String> listForTest(String pre) throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:h2:~/AutoComplete", "sa", "");
		var stm = conn.createStatement();
		var rs = stm.executeQuery( "SELECT word FROM words");
		List<String> allWords=new ArrayList<String>();
		
		while(rs.next()) { 

			String word = rs.getString("word"); 
			if(pre=="") {
			allWords.add(word);  
			}
			
			else {
				if(word.substring(0, pre.length()).equals(pre)) {
					allWords.add(word);
					
				}
			}
		}
		
		return allWords;

	}


}
