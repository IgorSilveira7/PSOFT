package com.example.demo;

import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World", required = false ) String name, Model modelo) {
		modelo.addAttribute("name", name);
		return "hello";
	}
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World", required = false ) String name, Model modelo) {
		modelo.addAttribute("name", name);
		modelo.addAttribute("greeting", getGreeting());
		return "greeting";
	}
	
	@GetMapping("/time")
	public String time(Model modelo) {
		LocalTime lt = LocalTime.now();
		String hora = lt.getHour() + ":" + lt.getMinute() + ":" + lt.getSecond();
		modelo.addAttribute("time",hora);
		return "time";
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
