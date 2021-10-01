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

                case "/login":
                    mostrarTelaLogin(request, response);
                    break;

                case "/logar-usuario":
                    logarUsuario(request, response);
                    break;

                case "/menu":
                    mostrarMenu(request, response);
                    break;

                case "/formulario-trageto":
                    mostrarFormularioTrajeto(request, response);
                    break;

                case "/criar-trajeto":
                    criarTrajeto(request, response);
                    break;

                case "/trajeto":
                    mostrarTrajeto(request, response);
                    break;

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

}
