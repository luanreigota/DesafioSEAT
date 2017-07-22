package entity;

public class Fila {
	
	private String senha;
	
	private Long emissao;
	
	private String prioridade;

	private Long chamada;
	
	private String guiche;
	
	private String atendente;
	
	private Long fim;

	private int naFrente;
	
	private long espera;
	
	
	public long getEspera() {
		return espera;
	}

	public void setEspera(long espera) {
		this.espera = espera;
	}

	public int getNaFrente() {
		return naFrente;
	}

	public void setNaFrente(int naFrente) {
		this.naFrente = naFrente;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getEmissao() {
		return emissao;
	}

	public void setEmissao(Long emissao) {
		this.emissao = emissao;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public Long getChamada() {
		return chamada;
	}

	public void setChamada(Long chamada) {
		this.chamada = chamada;
	}

	public String getGuiche() {
		return guiche;
	}

	public void setGuiche(String guiche) {
		this.guiche = guiche;
	}

	public String getAtendente() {
		return atendente;
	}

	public void setAtendente(String atendente) {
		this.atendente = atendente;
	}

	public Long getFim() {
		return fim;
	}

	public void setFim(Long fim) {
		this.fim = fim;
	}

}
