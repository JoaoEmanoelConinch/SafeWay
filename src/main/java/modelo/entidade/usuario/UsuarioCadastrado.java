package modelo.entidade.usuario;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import modelo.entidade.formulario.Formulario;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.PontoAvaliado;
import modelo.entidade.mapa.PontoFavorito;
import modelo.enumeracao.mapa.Estrelas;
import modelo.enumeracao.mapa.NivelBloqueio;
import modelo.enumeracao.mapa.Ocorrencia;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

@Entity
@Table(name = "UsuarioCadastrado")
public class UsuarioCadastrado extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario",nullable = false, unique = true, columnDefinition = "UNSIGNED INT")
	private Long idUsuario;

	@Column(name = "nome_usuario", length = 45, nullable = false, unique = true)
	private String nome;

	@Column(name = "senha_usuario", length = 45, nullable = false)
	private String senha;

	@Column(name = "email_usuario", length = 45, nullable = false, unique = true)
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_usuario")
	private List<PontoFavorito> favoritos;

	public UsuarioCadastrado() {
	}

	public UsuarioCadastrado(Long idUsuario, String nome, String senha, String email)
			throws StringVaziaException, EmailInvalidoException, SenhaPequenaException {
		super();

		this.setNome(nome);
		this.setSenha(senha);
		this.setEmail(email);
	}

	public UsuarioCadastrado(String nome, String senha, String email)
			throws StringVaziaException, EmailInvalidoException, SenhaPequenaException {
		super();
		this.setNome(nome);
		this.setSenha(senha);
		this.setEmail(email);
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;

	}

	public void setNome(String nome) throws StringVaziaException {

		if (nome.isEmpty()) {
			throw new StringVaziaException("O nome de Usu�rio � inv�lido!");
		}

		this.nome = nome;

		// (FALTA FAZER) verificar nomes iguais
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws StringVaziaException, SenhaPequenaException {

		if (senha.length() < 8) {
			throw new SenhaPequenaException("A senha n�o pode ter menos que 8 digitos");
		}

		if (nome.isEmpty()) {
			throw new StringVaziaException("A senha n�o pode ser vazia!");
		}

		this.senha = senha;

	}

	public String getEmail() {
		return email;
	}

	public List<PontoFavorito> getFavoritos() {
		return favoritos;
	}

	private void setEmail(String email) throws EmailInvalidoException, StringVaziaException {

		if (nome.isEmpty()) {
			throw new StringVaziaException("O nome de Usu�rio � inv�lido!");
		}

		if (validarEmail(email) == false) {
			throw new EmailInvalidoException("Email Inv�lido!");
		}

		this.email = email;

	}

	public boolean validarEmail(String email) {

		boolean isEmailValid = false;

		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isEmailValid = true;
			}
		}

		return isEmailValid;
	}

	public void avaliacao(Ocorrencia ocorrencias, Estrelas nivelEstrutura, Estrelas nivelIluminacao,
			NivelBloqueio bloqueioRuas, Estrelas nivelTransito, String comentario, Ponto idPontoAvaliado, UsuarioCadastrado usuario)
			throws NullPointerException, StatusInvalidoException {

		Formulario formulario = new Formulario();

		if (idPontoAvaliado.getClass().equals("Ponto")) {
			idPontoAvaliado = PontoAvaliado.criarPontoAvaliado(idPontoAvaliado);

			formulario = new Formulario(ocorrencias, nivelEstrutura, nivelIluminacao, bloqueioRuas, nivelTransito, comentario, idPontoAvaliado,  usuario);

			((PontoAvaliado) idPontoAvaliado).addAvaliacao(formulario);

		}
	}

	public void favoritarENomear(Ponto ponto, String nomePonto) throws StatusInvalidoException, JsonMappingException, JsonProcessingException {
		PontoFavorito.favoritarPontoENomear(ponto, nomePonto);
		favoritos.add((PontoFavorito) ponto);

	}
}
