package com.jsp.controle;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.jsp.dao.usuariodao.UsuarioDAO;
import com.jsp.modelo.Usuario;
/**
 * Servlet implementation class AdminControl
 */

@WebServlet("/auth/admin")
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioDAO usuarioDAO;
	
	public AdminControl() {
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
			case "listar":
				listarUsuario(request, response);
				break;	
			case "apagar":
				apagarUsuario(request, response);
				break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

	}


	private void listarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		List<Usuario> usuarios = usuarioDAO.listarTodosUsuarios();
		
        request.setAttribute("listaUsuarios", usuarios);
		
		String path =  request.getServletPath() + "/admin-listar-usuario.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		
		dispatcher.forward(request, response);

		
	}
	
	private void apagarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("id"));
		
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuarioDAO.apagarUsuario(usuario);
		
		String path = request.getContextPath() + request.getServletPath() + "?acao=listar";
		response.sendRedirect(path);

	}

}
