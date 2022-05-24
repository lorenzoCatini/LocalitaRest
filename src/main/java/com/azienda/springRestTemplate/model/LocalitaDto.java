package com.azienda.springRestTemplate.model;

public class LocalitaDto {
	
	private Integer id;
	private String nome;
	private Float temperatura;
	
	public LocalitaDto() {
		super();
	}
	public LocalitaDto(Integer id,String nome, Float temperatura) {
		super();
		this.id = id;
		this.nome = nome;
		this.temperatura = temperatura;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Float getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(Float temperatura) {
		this.temperatura = temperatura;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return id + ", " + nome + ", " + temperatura;
	}
		
}