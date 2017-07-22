package response;

import java.util.List;

import entity.Fila;

public class Resposta {
	
	private String nome;
	
	private String chave;
	
	private List<Fila> resultado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public List<Fila> getResultado() {
		return resultado;
	}

	public void setResultado(List<Fila> resultado) {
		this.resultado = resultado;
	}
	
	

}
