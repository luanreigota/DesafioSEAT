package entity;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RespostaGet {

	private String mensagem;
	
	private String nome;
	
	private String chave;
	
	private List<Fila> input;
	
	private PostTo postTo;
	
	private String mailTo;
	
	private int milestone;
	
	private String subject;
	

	
	
	public int getMilestone() {
		return milestone;
	}

	public void setMilestone(int milestone) {
		this.milestone = milestone;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

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

	public List<Fila> getInput() {
		return input;
	}

	public void setInput(List<Fila> input) {
		this.input = input;
	}

	public PostTo getPostTo() {
		return postTo;
	}

	public void setPostTo(PostTo postTo) {
		this.postTo = postTo;
	}
	
	
	public List<Fila> sortFila(List<Fila> fila){
		
		Collections.sort(fila, new Comparator<Fila>() {
			public int compare(Fila f1, Fila f2) {
				// TODO Auto-generated method stub
				return f1.getEmissao().compareTo(f2.getEmissao());
			}
		});
		
		return fila;
	}
	
	
	public List<Fila> eliminaDuplicadas(List<Fila> fila){
		
		for (int i = 0; i < fila.size(); i++) {
			Fila f = fila.get(i);
			fila.remove(i);
			if(fila.contains(f)){
				i = 0;
			}else{
				fila.add(f);
			}
		}
		
		return fila;
	}
	
	public List<Fila> removeAtendidas(List<Fila> fila){
		List<Fila> f = new ArrayList<Fila>();
		for (int i = 0; i < fila.size(); i++) {
			if(Strings.isNullOrEmpty(fila.get(i).getAtendente())){
				f.add(fila.get(i));
			}
		}
		return f;
	}
	
}