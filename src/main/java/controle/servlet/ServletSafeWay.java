package controle.servlet;

import java.io.IOException;
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
public class ServletSafeWay extends HttpServlet{
    
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

                case "/cadastro":
                    mostrarTelaCadastro(request, response);
                    break;

                case "/inserir-usuario":
                    inserirUsuario(request, response);
                    break;

                case "/atualizar-usuario":
                    atualizarUsuario(request, response);
                    break;
                
                case "/deletar-usuario":
                    deletarUsuario(request, response);
                    break;

                case "/login":
                    mostrarTelaLogin(request, response);
                    break;

                case "/logar-usuario":
                    logarUsuario(request, response);
                    break;

                // case "/menu":
                //     mostrarMenu(request, response);
                //     break;

                case "/formulario-trageto":
                    mostrarFormularioTrajeto(request, response);
                    break;

                case "/criar-trajeto":
                    criarTrajeto(request, response);
                    break;

//                case "/trajeto":
//                    mostrarTrajeto(request, response);
//                    break;

                case "/formolario-denuncia":
                    mostrarFormularioDenuncia(request, response);
                    break;

                case "/inserir-denuncia":
                    inserirDenuncia(request, response);
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

    private void mostrarTelaInicial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
    }
    private void mostrarTelaCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro-usuario.jsp");
		dispatcher.forward(request, response);
    }

    private void inserirUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, IOException, ServletException {

		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");

        UsuarioCadastrado usuario = new UsuarioCadastrado(nome, senha, email);
		usuarioDAO.inserirUsuario(usuario);

        request.setAttribute("usuario", usuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("formulario-trajeto.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, IOException, ServletException {
        long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		
        UsuarioCadastrado usuario = new UsuarioCadastrado(idUsuario, nome, senha, email);
		usuarioDAO.inserirUsuario(usuario);

        request.setAttribute("usuario", usuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("formulario-trajeto.jsp");
        dispatcher.forward(request, response);
    }


    private void deletarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
		UsuarioCadastrado usuarioCadastrado = usuarioDAO.recuperarUsuario(new UsuarioCadastrado(idUsuario));
		usuarioDAO.deletarUsuario(usuarioCadastrado);
		response.sendRedirect("inicio");
    }

    private void mostrarTelaLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("erro404.jsp");
		dispatcher.forward(request, response);
    }

    private void logarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //logar!
        response.sendRedirect("erro");
    }

    // private void mostrarMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //     RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
	// 	dispatcher.forward(request, response);
    // }

    private void mostrarFormularioTrajeto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("FormularioTrajeto.jsp");
		dispatcher.forward(request, response);
    }

    private void criarTrajeto(HttpServletRequest request, HttpServletResponse response) 
            throws JsonParseException, JsonMappingException, IOException, StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException, ServletException {
		String p1 = request.getParameter("inicio");
		String p2 = request.getParameter("chegada");
		int meioDeTransporte = Integer.parseInt(request.getParameter("MeioDeTransporte"));

		// long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
		// UsuarioCadastrado usuario = usuarioDAO.recuperarUsuario(new UsuarioCadastrado(idUsuario));
		
        MeioDeTransporte meio = MeioDeTransporte.values()[meioDeTransporte];
		
        Ponto partida = Ponto.informarLocal(p1);
        if (pontoDAO.verificarPonto(partida)==null){
        	pontoDAO.inserirPonto(partida);
        }
        Ponto partidaTrajeto = pontoDAO.verificarPonto(partida);
        
        Ponto chegada = Ponto.informarLocal(p2);
        if (pontoDAO.verificarPonto(chegada)==null){
        	pontoDAO.inserirPonto(chegada);
        }
        Ponto chegadaTrajeto = pontoDAO.verificarPonto(partida);
        
		Trajeto trajeto = new Trajeto(partidaTrajeto, chegadaTrajeto, meio);
        for (int i = 0; i < trajeto.getPontos().size(); i++) {
			Ponto ponto = trajeto.getPontos().get(i);
			if (pontoDAO.verificarPonto(ponto) == null) {
				pontoDAO.inserirPonto(ponto);
			}
			Ponto pontoBD = pontoDAO.verificarPonto(ponto);
			trajeto.getPontos().get(i).setIdPonto(pontoBD.getIdPonto());
		}
		trajetoDAO.inserirTrajeto(trajeto);
		// usuarioDAO.atualizarUsuario(usuario);

		trajetoDAO.inserirTrajeto(trajeto);
//		usuarioDAO.atualizarUsuario(usuario);

        List<Ponto> pontos = trajetoDAO.recuperarTrajeto(trajeto).getPontos();
        request.setAttribute("pontos", pontos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("trajeto.jsp");
		dispatcher.forward(request, response);

    }

    private void mostrarFormularioDenuncia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("avaliacao.jsp");
		dispatcher.forward(request, response);
    }

    private void inserirDenuncia(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, StatusInvalidoException {
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

    private void mostrarErro404(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("erro404.jsp");
		dispatcher.forward(request, response);
    }

}