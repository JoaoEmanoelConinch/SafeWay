package controle.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import modelo.dao.formulario.FormularioDAO;
import modelo.dao.formulario.FormularioDAOImpl;
import modelo.dao.ponto.PontoDAO;
import modelo.dao.ponto.PontoDAOImpl;
import modelo.dao.trajeto.TrajetoDAO;
import modelo.dao.trajeto.TrajetoDAOImpl;
import modelo.dao.usuario.UsuarioDAO;
import modelo.dao.usuario.UsuarioDAOImpl;
import modelo.entidade.formulario.Formulario;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.Trajeto;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.NumeroMaiorQueLimiteException;
import modelo.excecao.mapa.NumeroMenorQueZeroException;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

@WebServlet("/")
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UsuarioDAO usuarioDAO;
	private PontoDAO pontoDAO;
	private TrajetoDAO trajetoDAO;
	private FormularioDAO formularioDAO;

	public void init() {
		
		usuarioDAO = new UsuarioDAOImpl();
		pontoDAO = new PontoDAOImpl();
		trajetoDAO = new TrajetoDAOImpl();
		formularioDAO = new FormularioDAOImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		try {

			switch (action) {

			case "/inicio":
				mostrarTelaInicial(request, response);
				break;

			case "/novo-Usuario":
				mostrarFormularioDeCadastroDeUsuario(request, response);
				break;

			case "/login":
				mostrarFormularioDeLogin(request, response);
				break;

			//logar submit...

			case "/inserir-Usuario":
				inserirUsuario(request, response);
				break;

			case "/atualizar-Usuario":
				atualizarUsuario(request, response);
				break;

			case "/editar-Usuario":
				mostrarFormularioEditarUsuario(request, response);
				break;

			case "/deletar-Usuario":
				deletarUsuario(request, response);
				break;

			case "/Avaliacao":
				mostrarTelaAvaliacao(request, response);
				break;

			case "/inserir-Avaliacao":
				inserirAvaliacao(request, response);
				break;

			case "/deletar-Avaliacao":
				deletarAvaliacao(request, response);
				break;

			case "/criar-trajeto-pontos":
				inserirTrajetoComPontos(request, response);
				break;
				
			case "/criar-trajeto-Locais":
				inserirTrajetoComLocais(request, response);
				break;

			case "/deletar-trajeto":
				deletarTrajeto(request, response);
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

	private void recuperarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
		UsuarioCadastrado usuarioCadastrado = usuarioDAO.recuperarUsuario(new UsuarioCadastrado(idUsuario));
		request.setAttribute("usuario", usuarioCadastrado);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cabecalho.jsp");
		dispatcher.forward(request, response);
	}

	private void mostrarTelaInicial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void mostrarTela404(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("erro404.jsp");
		dispatcher.forward(request, response);
	}

	private void mostrarFormularioDeCadastroDeUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro.jsp");
		dispatcher.forward(request, response);
	}

	private void mostrarFormularioDeLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	// mapa que ñ é mapa, é tabela

	private void mostrarTelaAvaliacao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("formulario.jsp");
		dispatcher.forward(request, response);
	}

	private void mostrarFormularioEditarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
		UsuarioCadastrado usuarioCadastrado = usuarioDAO.recuperarUsuario(new UsuarioCadastrado(idUsuario));
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro.jsp");
		request.setAttribute("usuarioCadastrado", usuarioCadastrado);
		dispatcher.forward(request, response);
	}

	private void inserirUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, StringVaziaException, EmailInvalidoException, SenhaPequenaException {

		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");

		usuarioDAO.inserirUsuario(new UsuarioCadastrado(nome, senha, email));

		response.sendRedirect("mapa");
	}

	private void atualizarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, IOException {
		long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		usuarioDAO.atualizarUsuario(new UsuarioCadastrado(idUsuario, nome, senha, email));
		response.sendRedirect("mapa");
	}

	private void deletarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
		UsuarioCadastrado usuarioCadastrado = usuarioDAO.recuperarUsuario(new UsuarioCadastrado(idUsuario));
		usuarioDAO.deletarUsuario(usuarioCadastrado);
		response.sendRedirect("inicio");
	}

	private void inserirTrajetoComPontos(HttpServletRequest request, HttpServletResponse response)
			throws StatusInvalidoException, NumeroMenorQueZeroException, JsonParseException, JsonMappingException,
			IOException {
		Ponto partida = (Ponto) request.getAttribute("inicio");
		Ponto chegada = (Ponto) request.getAttribute("chegada");
		MeioDeTransporte meioDeTransporte = (MeioDeTransporte) request.getAttribute("meioDeTransporte");

		long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
		UsuarioCadastrado usuario = usuarioDAO.recuperarUsuario(new UsuarioCadastrado(idUsuario));
		
		Trajeto trajeto = usuario.trajeto(partida, chegada, meioDeTransporte);

		for (int i = 0; i < trajeto.getPontos().size(); i++) {
			Ponto ponto = trajeto.getPontos().get(i);
			if (pontoDAO.verificarPonto(ponto) == null) {
				pontoDAO.inserirPonto(ponto);
			}
			Ponto pontoBD = pontoDAO.verificarPonto(ponto);
			trajeto.getPontos().get(i).setIdPonto(pontoBD.getIdPonto());
		}
		trajetoDAO.inserirTrajeto(trajeto);
		usuarioDAO.atualizarUsuario((UsuarioCadastrado)usuario);

		request.setAttribute("points", trajeto.getPontos());

		response.sendRedirect("mapa");
	}
	
	private void inserirTrajetoComLocais(HttpServletRequest request, HttpServletResponse response) 
			throws JsonParseException, JsonMappingException, StatusInvalidoException, IOException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		
		String partida = request.getParameter("inicio");
		String chegada = request.getParameter("chegada");
		int meioDeTransporte = Integer.parseInt(request.getParameter("MeioDeTransporte"));
		
		MeioDeTransporte meio = MeioDeTransporte.values()[meioDeTransporte];
		
		
		Trajeto trajeto = new Trajeto(partida, chegada, meio);
		
		for (int i = 0; i < trajeto.getPontos().size(); i++) {
			Ponto ponto = trajeto.getPontos().get(i);
			if (pontoDAO.verificarPonto(ponto) == null) {
				pontoDAO.inserirPonto(ponto);
			}
			Ponto pontoBD = pontoDAO.verificarPonto(ponto);
			trajeto.getPontos().get(i).setIdPonto(pontoBD.getIdPonto());
		}
		trajetoDAO.inserirTrajeto(trajeto);

		List<List<Double>> points = new ArrayList<List<Double>>();
		
		for (int i = 0; i <= trajeto.getPontos().size(); i++) {
			points.add(trajeto.getPontos().get(i).criarVetor());
		}
		
		request.setAttribute("points", points);

		response.sendRedirect("mapa");
		
	}
	
	private void deletarTrajeto(HttpServletRequest request, HttpServletResponse response) {
		long idTrajeto = Long.parseLong(request.getParameter("idTrajeto"));
		Trajeto trajeto = trajetoDAO.recuperarTrajeto(new Trajeto(idTrajeto));
		trajetoDAO.deletarTrajeto(trajeto);
	}

	private void inserirAvaliacao(HttpServletRequest request, HttpServletResponse response)
			throws NullPointerException, StatusInvalidoException {
		boolean lesaoCorporal = Boolean.parseBoolean(request.getParameter("lesaoCorporal"));
		boolean furto = Boolean.parseBoolean(request.getParameter("furto"));
		boolean roubo = Boolean.parseBoolean(request.getParameter("roubo"));
		boolean homicidio = Boolean.parseBoolean(request.getParameter("homicidio"));
		boolean latrocinio = Boolean.parseBoolean(request.getParameter("latrocinio"));
		boolean bloqueio = Boolean.parseBoolean(request.getParameter("bloqueio"));
		String comentario = request.getParameter("comentario");
		Ponto ponto = (Ponto) request.getAttribute("ponto");

		long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
		UsuarioCadastrado usuario = usuarioDAO.recuperarUsuario(new UsuarioCadastrado(idUsuario));

		if (pontoDAO.verificarPonto(ponto) == null) {
			pontoDAO.inserirPonto(ponto);
		}
		Ponto pontoUsavel = pontoDAO.verificarPonto(ponto);

		Formulario avaliacao = usuario.avaliacao(lesaoCorporal, furto, roubo, homicidio, latrocinio, bloqueio, comentario, pontoUsavel, usuario);
		
		formularioDAO.inserirAvaliacao(avaliacao);

		pontoUsavel.addAvaliacao(avaliacao);

		pontoDAO.atualizarPonto(pontoUsavel);

	}

	private void deletarAvaliacao(HttpServletRequest request, HttpServletResponse response) {
		long idAvaliacao = Long.parseLong(request.getParameter("idAvaliacao"));
		Formulario formulario = formularioDAO.recuperarAvaliacaoId(new Formulario(idAvaliacao));
		formularioDAO.deletarAvaliacao(formulario);
	}

}