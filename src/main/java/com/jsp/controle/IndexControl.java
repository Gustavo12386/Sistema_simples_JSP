package com.jsp.controle;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import com.jsp.controle.util.ManipulacaoData;
import com.jsp.dao.usuariodao.UsuarioDAO;
import com.jsp.modelo.Usuario;

/**
 * Servlet implementation class IndexControl
 */
@WebServlet("/publica")
public class IndexControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDAO;
    
    public IndexControl() {
        super();
        
    }
    
    public void init() {
		usuarioDAO = new UsuarioDAO();
	}


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   processarRequisicao(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processarRequisicao(request, response);	
	}
	

	protected void processarRequisicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");	
		try {
			switch (acao) {
			case "novo":
				novoUsuario(request, response);
				break;
			case "inserir":
				gravarUsuario(request, response);
				break;
				
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

	}
	
	protected void novoUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("publica/publica-novo-usuario.jsp");
	    dispatcher.forward(request, response);
	}
	
	protected void gravarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {	
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String login = request.getParameter("login");		
		String data = request.getParameter("nascimento");

		ManipulacaoData manipulacaoData = new ManipulacaoData();
		Date dataNascimento = manipulacaoData.converterStringData(data);
		
		Usuario usuario = new Usuario( nome, cpf, dataNascimento, email, password, login, false);

		Usuario usuarioGravado = usuarioDAO.inserirUsuario(usuario);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("publica/publica-novo-usuario.jsp");
		request.setAttribute("mensagem", "Usuário cadastrado com sucesso");
		dispatcher.forward(request, response);

	}

}
