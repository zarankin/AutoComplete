package com.autocomplete;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Service.Build_Data_Struct;
import Service.Prefix_Tree;

@RestController
@RequestMapping("/")

public class AutoCompleteController {

	private Build_Data_Struct st;
	boolean built=false;

	
	@GetMapping
	public String greeting() {
		
		String greeting= "Greetings dear client! Please enter input from english letter only";
		

		return greeting ;
	}
	
	@PostMapping
	public  String[] read_from_user(@RequestBody String input) {
		
		if(!built) {
			 this.st=new Build_Data_Struct(new Prefix_Tree('\0'));
			 built=true;
			}
				
		return st.getWords(input.toLowerCase());
	}

}
