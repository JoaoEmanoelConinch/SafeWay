package modelo.entidade.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import modelo.entidade.formulario.Formulario;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.PontoAvaliado;
import modelo.entidade.mapa.PontoFavorito;
import modelo.entidade.mapa.Trajeto;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

@Entity
@Table(name = "UsuarioCadastrado")
public class UsuarioCadastrado extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome_usuario", length = 45, nullable = false, unique = true)
	private String nome;

	@Column(name = "senha_usuario", length = 45, nullable = false)
	private String senha;

	@Column(name = "email_usuario", length = 45, nullable = false, unique = true)
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PontoFavorito> favoritos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Formulario> formulariosDoUsuario;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "usuario_trajeto",
	joinColumns = @JoinColumn(name = "id"),
	inverseJoinColumns = @JoinColumn(name = "id_trajeto")
	)
	private List<Trajeto> trajetos;

	public UsuarioCadastrado() {
	}
	
	public UsuarioCadastrado(long id) {
		setId(id);
	}

	public UsuarioCadastrado(Long idUsuario, String nome, String senha, String email,
	List<PontoFavorito> favoritos, List<Formulario> formulariosDoUsuario, List<Trajeto> trajetos)
			throws StringVaziaException, EmailInvalidoException, SenhaPequenaException {
		super(idUsuario);

		this.setNome(nome);
		this.setSenha(senha);
		this.setEmail(email);
		this.setFavoritos(favoritos);
		this.setFormulariosDoUsuario(formulariosDoUsuario);
		this.setTrajetos(trajetos);
	}

	public UsuarioCadastrado(String nome, String senha, String email)
			throws StringVaziaException, EmailInvalidoException, SenhaPequenaException {
		super();
		this.setNome(nome);
		this.setSenha(senha);
		this.setEmail(email);
		this.setFavoritos(new ArrayList<PontoFavorito>());
		this.setFormulariosDoUsuario(new ArrayList<Formulario>());
		this.setTrajetos(new ArrayList<Trajeto>());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws StringVaziaException {

		if (nome.isEmpty()) {
			throw new StringVaziaException("O nome de Usu�rio � inv�lido!");
		}

		this.nome = nome;
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

	private void setEmail(String email) throws EmailInvalidoException, StringVaziaException {

		if (nome.isEmpty()) {
			throw new StringVaziaException("O nome de Usu�rio � inv�lido!");
		}

		if (validarEmail(email) == false) {
			throw new EmailInvalidoException("Email Inv�lido!");
		}

		this.email = email;

	}

	public List<PontoFavorito> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(List<PontoFavorito> favoritos) {
		this.favoritos = favoritos;
	}

	public void setFormulariosDoUsuario(List<Formulario> formulariosDoUsuario) {
		this.formulariosDoUsuario = formulariosDoUsuario;
	}

	public List<Formulario> getFormulariosDoUsuario() {
		return formulariosDoUsuario;
	}

	public List<Trajeto> getTrajetos() {
		return trajetos;
	}

	public void setTrajetos(List<Trajeto> trajetos) {
		this.trajetos = trajetos;
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

//	public void avaliacao(boolean lesaoCorporal, boolean furto, boolean roubo, boolean homicidio, boolean latrocinio,
//			boolean bloqueioRuas, String comentario, Ponto idPontoAvaliado, UsuarioCadastrado idUsuario)
//			throws NullPointerException, StatusInvalidoException, Throwable, JsonProcessingException {
//
//		Formulario formulario = new Formulario();
//
//		if (idPontoAvaliado.getClass().equals("Ponto")) {
//			idPontoAvaliado = PontoAvaliado.criarPontoAvaliado(idPontoAvaliado);
//
//			formulario = new Formulario(lesaoCorporal, furto, roubo, homicidio, latrocinio, comentario, bloqueioRuas,
//					idPontoAvaliado, idUsuario);
//
//			((PontoAvaliado) idPontoAvaliado).addAvaliacao(formulario);
//
//		}
//	}
//
//	public void favoritarENomear(Ponto ponto, String nomePonto)
//			throws StatusInvalidoException, JsonMappingException, JsonProcessingException {
//		PontoFavorito pontoFavorito = PontoFavorito.favoritarPontoENomear(ponto, nomePonto, this);
//		addFavorito(pontoFavorito);
//	}

	public void desfavoritar(PontoFavorito pontoFavorito) {
		favoritos.remove(pontoFavorito);
	}

	public void removeFavorito(PontoFavorito pontoFavorito) {
		favoritos.remove(pontoFavorito);
	}

	public void addFavorito(PontoFavorito pontoFavorito) {
		favoritos.add(pontoFavorito);
	}

	public void addFormulario(Formulario formulario) {
		formulariosDoUsuario.add(formulario);
	}

	public void remuveFormulario(Formulario formulario) {

		formulariosDoUsuario.remove(formulario);
	}

	public void addTrajeto(Trajeto trajeto) {
		trajetos.add(trajeto);
	}

	public void RemuveTrajeto(Trajeto trajeto) {
		trajetos.remove(trajeto);
	}
}