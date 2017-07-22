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

	public static void main(String[] args) throws MalformedURLException, IOException {
		Document document = (Document) Jsoup.connect("http://seat.ind.br/processo-seletivo/desafio-2017-03.php?nome=luan%20reigota%20lameirinhas").get();
		String content = document.body().toString();
		
		content = content.substring(content.indexOf("<body>")+6,content.indexOf("</body>"));
		
		Gson gson = new GsonBuilder().create();
		RespostaGet respostaGet = gson.fromJson(content, RespostaGet.class);

		respostaGet.setInput(respostaGet.removeAtendidas(respostaGet.getInput()));
		respostaGet.eliminaDuplicadas(respostaGet.getInput());
		respostaGet.sortFila(respostaGet.getInput());

		int naFrente = 0;
		long espera = 300000;
		for (Fila f : respostaGet.getInput()) {
			f.setNaFrente(naFrente);
			f.setEspera(espera);
			naFrente++;
			espera += 300000;
		}

		document = (Document) Jsoup.connect(respostaGet.getPostTo().getUrl())
				.data("nome", respostaGet.getNome()).data("chave", respostaGet.getChave())
				.data("resultado", gson.toJson(respostaGet.getInput())).post();

		content = document.body().toString();
		content = content.substring(content.indexOf("<body>")+6,content.indexOf("</body>"));
		respostaGet= gson.fromJson(content, RespostaGet.class);

		System.out.println(respostaGet.getMailTo());

	}
}