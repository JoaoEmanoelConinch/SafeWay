package controlador.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.Usuario.UsuarioDAO;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

@WebServlet("/")
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UsuarioDAO usuarioDAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		
		try {
			
			switch(action) {

				case "/novo Usuario":
					mostrarFormularioDeCadastroDeUsuario(request, response);
					break;

				case "/inserir Usuario":
					inserirUsuario(request, response);
					break;

				case "/atualizar Usuario":
					atualizarUsuario(request, response);
					break;

				case "/editar Usuario":
					mostrarFormularioEditarUsuario(request, response);
					break;

				case "/deletar Usuario":
					deletarUsuario(request, response);
					break;

				default:
					mostrarTela404(request, response);
					break;
			
			}
			
		}
		
		catch (Exception ex) {
			throw new ServletException(ex);
		}	
	}

	private void mostrarTela404 (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("erro404.jsp");
		dispatcher.forward(request, response);
	}
	
	private void mostrarFormularioDeCadastroDeUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro.jsp");
		dispatcher.forward(request, response);
	}

	private void mostrarFormularioEditarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		long id = Long.parseLong(request.getParameter("id"));
		UsuarioCadastrado usuarioCadastrado = usuarioDAO.recuperarUsuario(new UsuarioCadastrado(id));
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro.jsp");
		request.setAttribute("usuarioCadastrado", usuarioCadastrado);
		dispatcher.forward(request, response);
	}

	private void inserirUsuario(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, StringVaziaException, EmailInvalidoException, SenhaPequenaException{
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		usuarioDAO.inserirUsuario(new UsuarioCadastrado(nome, senha, email));
	}

	private void atualizarUsuario (HttpServletRequest request, HttpServletResponse response) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException{
		long id = Long.parseLong(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		usuarioDAO.atualizarUsuario(new UsuarioCadastrado(id, nome, senha, email));
	}

	private void deletarUsuario (HttpServletRequest request, HttpServletResponse response){
		long id = Long.parseLong(request.getParameter("id"));
		UsuarioCadastrado usuarioCadastrado = usuarioDAO.recuperarUsuario(new UsuarioCadastrado(id));
		usuarioDAO.deletarUsuario(usuarioCadastrado);
	}

}
