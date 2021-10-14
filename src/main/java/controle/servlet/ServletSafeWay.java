package controle.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class ServletSafeWay extends HttpServlet {

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

		HttpSession session = request.getSession();

		try {

			switch (action) {

			case "/inicio":
				mostrarTelaInicial(request, response);
				break;

			case "/cadastro":
				mostrarTelaCadastro(request, response);
				break;

			case "/inserir-usuario":
				inserirUsuario(request, response, session);
				break;

			case "/atualizar-usuario":
				atualizarUsuario(request, response, session);
				break;

			case "/deletar-usuario":
				deletarUsuario(request, response, session);
				break;

			case "/login":
				mostrarTelaLogin(request, response);
				break;

			case "/logar-usuario":
				logarUsuario(request, response, session);
				break;

			case "/formulario-trageto":
				mostrarFormularioTrajeto(request, response);
				break;

			case "/criar-trajeto":
				criarTrajeto(request, response, session);
				break;

			case "/trajeto":
				mostrarTrajeto(request, response, session);
				break;

			case "/formolario-denuncia":
				mostrarFormularioDenuncia(request, response);
				break;

			case "/inserir-denuncia":
				inserirDenuncia(request, response, session);
				break;

			default:
				mostrarErro404(request, response);
				break;

			}
		}

		catch (Exception ex) {

			throw new ServletException(ex);
		}

	}

	private void mostrarTelaInicial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);

	}

	private void mostrarTelaCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro-usuario.jsp");
		dispatcher.forward(request, response);
	}

	private void inserirUsuario(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, IOException, ServletException {

		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		String pagDestino = "formulario-trajeto.jsp";

		UsuarioCadastrado usuario = new UsuarioCadastrado(nome, senha, email);

		if (senha.isEmpty() || senha.length() < 8 || nome.isEmpty() || !usuario.validarEmail(email)
				|| email.isEmpty()) {
			pagDestino = "cadastro-usuario.jsp";
		}

		if (usuarioDAO.verificarUsuarioNome(usuario) || usuarioDAO.verificarUsuarioEmail(usuario)) {
			pagDestino = "cadastro-usuario.jsp";
		} else {
			usuarioDAO.inserirUsuario(usuario);
			session.setAttribute("usuario", usuario);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(pagDestino);
		dispatcher.forward(request, response);
	}

	private void atualizarUsuario(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, IOException, ServletException {
		long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");

		UsuarioCadastrado usuario = new UsuarioCadastrado(nome, senha, email);
		usuarioDAO.atualizarUsuario(usuario);
		session.setAttribute("usuario", usuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("formulario-trajeto.jsp");
		dispatcher.forward(request, response);
	}

	private void deletarUsuario(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
		UsuarioCadastrado usuarioCadastrado = usuarioDAO.login(new UsuarioCadastrado(idUsuario));
		usuarioDAO.deletarUsuario(usuarioCadastrado);
		session.invalidate();
		response.sendRedirect("inicio");
	}

	private void mostrarTelaLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	private void logarUsuario(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException, StringVaziaException, SenhaPequenaException, EmailInvalidoException, ServletException {

		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String pagDestino = "login.jsp";

		UsuarioCadastrado usuario = usuarioDAO.login(new UsuarioCadastrado(email, senha));

		if (usuario != null) {
			session.setAttribute("usuario", usuario);
			pagDestino = "formulario-trajeto.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(pagDestino);
		dispatcher.forward(request, response);

	}

	private void mostrarFormularioTrajeto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("FormularioTrajeto.jsp");
		dispatcher.forward(request, response);
	}

	private void criarTrajeto(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws JsonParseException, JsonMappingException, IOException, StatusInvalidoException,
			NumeroMenorQueZeroException, NumeroMaiorQueLimiteException, ServletException {
		String p1 = request.getParameter("inicio");
		String p2 = request.getParameter("rua-chegada");
		int meioDeTransporte = Integer.parseInt(request.getParameter("meio-transporte"));
		UsuarioCadastrado usuario = (UsuarioCadastrado) session.getAttribute("usuario");
		usuario.setTrajetos( trajetoDAO.recuperarTrajetosUsuario(usuario));
		
		MeioDeTransporte meio = MeioDeTransporte.values()[meioDeTransporte];

		Ponto partida = Ponto.informarLocal(p1);

		Ponto partidaSoLatLong = new Ponto();
		partidaSoLatLong.setLongitude(partida.getLongitude());
		partidaSoLatLong.setLatitude(partida.getLatitude());

		if (pontoDAO.verificarPonto(partidaSoLatLong) == null) {
			pontoDAO.inserirPonto(partida);
		}
		Ponto partidaTrajeto = pontoDAO.verificarPonto(partidaSoLatLong);

		Ponto chegada = Ponto.informarLocal(p2);

		Ponto chegadaSoLatLong = new Ponto();
		chegadaSoLatLong.setLongitude(chegada.getLongitude());
		chegadaSoLatLong.setLatitude(chegada.getLatitude());

		if (pontoDAO.verificarPonto(chegadaSoLatLong) == null) {
			pontoDAO.inserirPonto(chegada);
		}
		Ponto chegadaTrajeto = pontoDAO.verificarPonto(chegadaSoLatLong);

		Trajeto trajeto = usuario.trajeto(partidaTrajeto, chegadaTrajeto, meio);

		System.out.println(trajeto.getPontos().size());

		for (int i = 0; i < trajeto.getPontos().size(); i++) {
			Ponto ponto = trajeto.getPontos().get(i);

			Ponto pontoSoLatLong = new Ponto();
			pontoSoLatLong.setLongitude(ponto.getLongitude());
			pontoSoLatLong.setLatitude(ponto.getLatitude());

			if (pontoDAO.verificarPonto(pontoSoLatLong) == null) {
				pontoDAO.inserirPonto(ponto);
			}

			Ponto pontoVerificado = pontoDAO.verificarPonto(pontoSoLatLong);
			
			trajeto.getPontos().set(i, pontoVerificado);
			
			pontoDAO.atualizarPonto(pontoVerificado);

		}

		trajeto.addUsuarioCadastrado(usuario);
		trajetoDAO.inserirTrajeto(trajeto);
		usuario.addTrajeto(trajeto);
		
		
		usuarioDAO.atualizarUsuario(usuario);

		session.setAttribute("trajeto", trajeto);

		response.sendRedirect("trajeto");
	}
	
	private void mostrarTrajeto(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
	
		Trajeto trajeto = (Trajeto)session.getAttribute("trajeto");
		
		List<Ponto> pontos = trajeto.getPontos();
		request.setAttribute("pontos", pontos);

		RequestDispatcher dispatcher = request.getRequestDispatcher("Trajeto.jsp");
		dispatcher.forward(request, response);

	}

	private void mostrarFormularioDenuncia(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Ponto ponto = pontoDAO.recuperarPonto(new Ponto(id));
		RequestDispatcher dispatcher = request.getRequestDispatcher("denuncia.jsp");
		request.setAttribute("ponto", ponto);
		dispatcher.forward(request, response);
	}

	private void inserirDenuncia(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws NullPointerException, StatusInvalidoException, IOException {

		boolean lesaoCorporal = Boolean.parseBoolean(request.getParameter("lesaoCorporal"));
		boolean furto = Boolean.parseBoolean(request.getParameter("furto"));
		boolean roubo = Boolean.parseBoolean(request.getParameter("roubo"));
		boolean homicidio = Boolean.parseBoolean(request.getParameter("homicidio"));
		boolean latrocinio = Boolean.parseBoolean(request.getParameter("latrocinio"));
//		boolean bloqueio = Boolean.parseBoolean(request.getParameter("bloqueio"));
		String comentario = request.getParameter("comentario");
		long idPonto = Long.parseLong(request.getParameter("idPonto"));

		Ponto ponto = pontoDAO.recuperarPonto(new Ponto(idPonto));

		UsuarioCadastrado usuario = (UsuarioCadastrado) session.getAttribute("usuario");
		
		List<Formulario> avaliacoesDoUsuario = formularioDAO.recuperarAvaliacoesDoUsuario(usuario);
		if (avaliacoesDoUsuario == null) {
			avaliacoesDoUsuario = new ArrayList<Formulario>();
		}
		
		usuario.setFormulariosDoUsuario(avaliacoesDoUsuario);
		
		List<Formulario> avaliacoesDoPonto = formularioDAO.recuperarAvaliacoes(ponto);
		if (avaliacoesDoPonto == null) {
			avaliacoesDoPonto = new ArrayList<Formulario>();
		}

		ponto.setAvaliacoes(avaliacoesDoPonto);

		Formulario avaliacao = usuario.avaliacao(lesaoCorporal, furto, roubo, homicidio, latrocinio, false, comentario, ponto);

		formularioDAO.inserirAvaliacao(avaliacao);

		usuarioDAO.atualizarUsuario(usuario);
		pontoDAO.atualizarPonto(ponto);

		Trajeto trajeto = (Trajeto) session.getAttribute("trajeto");

		for (int i = 0; i < trajeto.getPontos().size(); i++) {
			if (trajeto.getPontos().get(i).getLongitude() == ponto.getLongitude()
				& trajeto.getPontos().get(i).getLatitude() == ponto.getLatitude()) {
				
				List<Formulario> avaliacoesDoPontoDoTrjeto = formularioDAO.recuperarAvaliacoes(trajeto.getPontos().get(i));
				if (avaliacoesDoPontoDoTrjeto == null) {
					avaliacoesDoPontoDoTrjeto = new ArrayList<Formulario>();
				}
				
				trajeto.getPontos().get(i).setAvaliacoes(avaliacoesDoPontoDoTrjeto);
				
				trajeto.getPontos().get(i).addAvaliacao(avaliacao);
			}
		}

		session.setAttribute("trajeto", trajeto);
		response.sendRedirect("trajeto");
	}

	private void mostrarErro404(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("erro404.jsp");
		dispatcher.forward(request, response);
	}

}