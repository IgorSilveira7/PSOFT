package com.example.demo;

public class Greets {
	
	private String nome;
	private String saudacao;
	
	public Greets(String nome, String saudacao) {
		this.nome = nome;
		this.saudacao = saudacao;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSaudacao() {
		return saudacao;
	}
	public void setSaudacao(String saudacao) {
		this.saudacao = saudacao;
	}
	
	

}
