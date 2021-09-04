package com.autocomplete;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class uploadToH2 {

	public static void UploadToH2(String fileName) 
			throws Exception {

		Connection conn = DriverManager.getConnection("jdbc:h2:~/AutoComplete", "sa", "");
		var stm = conn.createStatement();
		//stm.executeUpdate("DROP TABLE words");

		
		

		try {//try to create new table
			stm.executeUpdate("CREATE TABLE words(word VARCHAR(255) PRIMARY KEY)");
			conn.commit();

			try {//try to open the file
				File myObj = new File(fileName);
				Scanner myReader = new Scanner(myObj);

				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					data=data.toLowerCase();
					stm.executeUpdate("INSERT INTO words(word) VALUES ('" +data +"')");
					conn.commit();
				}

				myReader.close();

			} catch (FileNotFoundException e) {//catch if can't open the text file
				e.printStackTrace();
			}

		}catch (Exception e) {//catch if table already exists
			System.out.println("table exists");
		}

		conn.close();
	}

}
