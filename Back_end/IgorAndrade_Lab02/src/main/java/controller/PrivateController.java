package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {
	
	@GetMapping("/private")
	public String privateMsg() {
		return "Mensagem privatda apenas paras usuários com token!";
	}
	
	@GetMapping("/public")
	public String publicMsg() {
		return "Mensagem pública! Todos podem acessar!!";
	}

}
