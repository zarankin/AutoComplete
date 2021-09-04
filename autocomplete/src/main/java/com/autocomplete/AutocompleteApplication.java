package com.autocomplete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutocompleteApplication {

	public static void main(String[] args) {
		String fileName="BoyNames.txt";
		if(args.length>0) {
			fileName=args[0];
		}
		
		try {
			uploadToH2.UploadToH2(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SpringApplication.run(AutocompleteApplication.class, args);
	}

}

