package br.com.alura.cliente;

import org.apache.http.client.fluent.Request;

public class ClienteWebService {

	public static void main(String[] args) throws Exception {
		
		String tipoDeRetorno = "json";
		
		String conteudo = Request
			.Post("http://localhost:8080/gerenciador/empresas")
			.addHeader("Accept", "application/"+tipoDeRetorno)
			.execute()
			.returnContent()
			.asString();

		System.out.println(conteudo);
		
	}

}
