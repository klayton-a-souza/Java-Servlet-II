package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

@WebServlet("/empresas")
public class EmpresasService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Empresa> empresas = new Banco().getEmpresas();

		String valor = request.getHeader("Accept");

		if (valor.contains("xml")) {

			XStream xStream = new XStream(); // Criando o elemento XStream
			xStream.alias("empresa", Empresa.class); // Exibir "empresas" quando encontra elemento do tipo Empresa

			String xml = xStream.toXML(empresas);

			response.setContentType("application/xml");
			response.getWriter().print(xml);
			
		} else if (valor.contains("json")) {

			Gson gson = new Gson();
			String json = gson.toJson(empresas);

			response.setContentType("application/json");
			response.getWriter().print(json);

		}else {
			response.setContentType("application/json");
			response.getWriter().print("{'message':'no content'}");
		}
	}

}
