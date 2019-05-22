package com.example.demo;

import java.time.LocalTime;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloJsonController {
	
	@RequestMapping("/helloJs")
	public Greets getGreet(@RequestParam(value = "name", defaultValue = "World", required = false ) String name) {
		return new Greets(name, this.getGreeting());
	}
	
	@RequestMapping("/timeJs")
	public Time getTime() {
		LocalTime lt = LocalTime.now();
		String hora = lt.getHour() + ":" + lt.getMinute() + ":" + lt.getSecond();
		return new Time(hora);
	}
	

	public String getGreeting() {
		LocalTime lt = LocalTime.now();
		int hora = lt.getHour();
		
		String result = "";
		
		if(hora < 12 ) {
			result = "Bom Dia";
		} else if (hora >= 12 && hora < 18){
			result = "Boa Tarde";
		} else {
			result = "Boa Noite";
		}
		
		return result;
	}

}
