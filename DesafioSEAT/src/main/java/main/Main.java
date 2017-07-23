package main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Fila;
import entity.RespostaGet;

public class Main {

	private static Document document;
	private static String content;
	private static Gson gson;
	private static RespostaGet respostaGet;
	
	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
		//fazendo um get para o endereco informado e obtendo um json
		document = (Document) Jsoup
				.connect("http://seat.ind.br/processo-seletivo/desafio-2017-03.php?nome=luan%20reigota%20lameirinhas")
				.get();
		//obtendo resultado do get
		content = document.body().toString();
		//isolando json
		content = content.substring(content.indexOf("<body>") + 6, content.indexOf("</body>"));
		
		//contertendo String json para objeto
		gson = new GsonBuilder().create();
		respostaGet = gson.fromJson(content, RespostaGet.class);

		//separa pedidos que ja foram atendidos
		List<Fila> filaAtendida = respostaGet.separarAtendidas();
		respostaGet.setInput(respostaGet.sortFila());
		respostaGet.calcOrdenamentoTempo();

		//faz o post para a url retorna do get passando nome, chave e resultado
		document = (Document) Jsoup.connect(respostaGet.getPostTo().getUrl()).data("nome", respostaGet.getNome())
				.data("chave", respostaGet.getChave()).data("resultado", gson.toJson(respostaGet.getInput())).post();

		//isolando json
		content = document.body().toString();
		content = content.substring(content.indexOf("<body>") + 6, content.indexOf("</body>"));
		
		//imprime resposta
		System.out.println(content);

		//imprime histograma
		while (true) {
			printaHistograma(filaAtendida);
			Thread.sleep(300000);
		}

	}

	public static void printaHistograma(List<Fila> fila) {

		int menosDe5 = 0, menosDe10 = 0, menosDe15 = 0, maisDe15 = 0;

		for (Fila f : fila) {
			menosDe5 = (Integer) ((((f.getChamada() - f.getEmissao()) / 1000) / 60 < 5) ? menosDe5 + 1 : menosDe5 + 0);
			menosDe10 = (Integer) ((((f.getChamada() - f.getEmissao()) / 1000) / 60 > 5
					&& ((f.getChamada() - f.getEmissao()) / 1000) / 60 < 10) ? menosDe10 + 1 : menosDe10 + 0);
			menosDe15 = (Integer) ((((f.getChamada() - f.getEmissao()) / 1000) / 60 > 10
					&& ((f.getChamada() - f.getEmissao()) / 1000) / 60 < 15) ? menosDe15 + 1 : menosDe15 + 0);
			maisDe15 = (Integer) ((((f.getChamada() - f.getEmissao()) / 1000) / 60 > 15) ? maisDe15 + 1 : maisDe15 + 0);
		}
		System.out.print("< 05min: ");
		for (int i = 0; i < menosDe5; i++) {
			System.out.print("#");
		}
		System.out.println(" " + menosDe5);

		System.out.print("< 10min: ");
		for (int i = 0; i < menosDe10; i++) {
			System.out.print("#");
		}
		System.out.println(" " + menosDe10);

		System.out.print("< 15min: ");
		for (int i = 0; i < menosDe15; i++) {
			System.out.print("#");
		}
		System.out.println(" " + menosDe15);

		System.out.print("> 15min: ");
		for (int i = 0; i < maisDe15; i++) {
			System.out.print("#");
		}
		System.out.println(" " + maisDe15);

	}

}