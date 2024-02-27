package api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {

		String cep = "72327015";

		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("http://viacep.com.br/ws/" + cep + "/json/")).build();
			HttpResponse<String> response = client.send(request, 
					HttpResponse.BodyHandlers.ofString());
			System.out.println(response.statusCode());
			
			Gson gson = new GsonBuilder().create();
			Endereco meuEndereco = gson.fromJson(response.body(), Endereco.class);
			System.out.println(meuEndereco);
		} catch (Exception e) {
			System.out.println("Erro:");
			System.out.println(e.getMessage());
		}


		
	}
}
