package controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import exception.ServletException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.User;
import service.UserService;

@RestController
@RequestMapping({"/v1/auth"})
public class LoginController {
	
	private final String TOKEN_KEY = "itsATrap";
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	@ResponseBody
	public LoginResponse authenticate(@RequestBody User user) throws ServletException {

		// Recupera o usuario
		User authUser = userService.findByLogin(user.getLogin());
		
		// verificacoes
		if(authUser == null) {
			throw new ServletException("Usuario nao encontrado!");
		}
		
		if(!authUser.getPassword().equals(user.getPassword())) {
			throw new ServletException("Senha invalida!");
		}
		
		String token = Jwts.builder().
				setSubject(authUser.getLogin()).
				signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
				setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
				.compact();
		
		return new LoginResponse(token);
		
		
	}
	
	private class LoginResponse {
		public String token;
		
		public LoginResponse(String token) {
			this.token = token;
		}
	}
}
