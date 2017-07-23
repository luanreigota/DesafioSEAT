package main;

import java.io.IOException;
import java.net.MalformedURLException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Fila;
import entity.RespostaGet;

public class Main {

	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
		Document document = (Document) Jsoup
				.connect("http://seat.ind.br/processo-seletivo/desafio-2017-03.php?nome=luan%20reigota%20lameirinhas")
				.get();
		String content = document.body().toString();

		content = content.substring(content.indexOf("<body>") + 6, content.indexOf("</body>"));

		Gson gson = new GsonBuilder().create();
		RespostaGet respostaGet = gson.fromJson(content, RespostaGet.class);

		respostaGet.removeAtendidas();
		respostaGet.eliminaDuplicadas();
		respostaGet.setInput(respostaGet.sortFila());
		respostaGet.calcOrdenamentoTempo();

		for (Fila f : respostaGet.getInput()) {
			System.out.println("senha: " + f.getSenha() + 
					" \nPrioridade: " + f.getPrioridade() + 
					" \nEmissao: "+ f.getEmissao() +
					" \nteste  : " + System.currentTimeMillis() +
					" \nfim: " + f.getFim() +
					" \nnaFrente: " + f.getNaFrente() +
					" \nespera: " + f.getEspera() +
					"\n\n");
		}

		document = (Document) Jsoup.connect(respostaGet.getPostTo().getUrl()).data("nome", respostaGet.getNome())
				.data("chave", respostaGet.getChave()).data("resultado", gson.toJson(respostaGet.getInput())).post();

		content = document.body().toString();
		content = content.substring(content.indexOf("<body>") + 6, content.indexOf("</body>"));
		System.out.println(content);
	}
}