package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.acao.AlteraEmpresa;
import br.com.alura.gerenciador.acao.CriandoNovaEmpresa;
import br.com.alura.gerenciador.acao.ListaEmpresas;
import br.com.alura.gerenciador.acao.MostraEmpresa;
import br.com.alura.gerenciador.acao.NovaEmpresa;
import br.com.alura.gerenciador.acao.RemovaEmpresa;

@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String paramAcao = request.getParameter("acao");
		String nome = null;

		if (paramAcao.equals("ListaEmpresas")) {

			ListaEmpresas acao = new ListaEmpresas();
			nome = acao.executa(request, response);

		} else if (paramAcao.equals("RemovaEmpresa")) {

			RemovaEmpresa acao = new RemovaEmpresa();
			nome = acao.executa(request, response);

		} else if (paramAcao.equals("MostraEmpresa")) {

			MostraEmpresa acao = new MostraEmpresa();
			nome = acao.executa(request, response);
			
		} else if (paramAcao.equals("AlteraEmpresa")) {

			AlteraEmpresa acao = new AlteraEmpresa();
			nome = acao.executa(request, response);
			
		} else if (paramAcao.equals("NovaEmpresa")) {

			NovaEmpresa acao = new NovaEmpresa();
			nome = acao.executa(request, response);
			
		} else if(paramAcao.equals("CriandoNovaEmpresa")) {
			
			CriandoNovaEmpresa acao = new CriandoNovaEmpresa();
			nome = acao.executa(request, response);
		}
		
		String[] tipoEEntrada = nome.split(":");

		if (tipoEEntrada[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEntrada[1]);
			rd.forward(request, response);
		} else {
			response.sendRedirect(tipoEEntrada[1]);
		}

	}

}
