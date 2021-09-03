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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import modelo.entidade.formulario.Formulario;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.PontoFavorito;
import modelo.entidade.mapa.Trajeto;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioCadastrado", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PontoFavorito> favoritos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioCadastrado", cascade = CascadeType.ALL, orphanRemoval = true)
	private ArrayList<Formulario> formulariosDoUsuario;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "usuario_trajeto",
	joinColumns = @JoinColumn(name = "id_usuario"),
	inverseJoinColumns = @JoinColumn(name = "id_trajeto"))
	private ArrayList<Trajeto> trajetos;

	public UsuarioCadastrado() {
	}

	public UsuarioCadastrado(Long idUsuario, String nome, String senha, String email)
			throws StringVaziaException, EmailInvalidoException, SenhaPequenaException {
		super();

		this.setIdUsuario(idUsuario);
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
			throw new StringVaziaException("Nome invalido!");
		}

		this.nome = nome;

		// (FALTA FAZER) verificar nomes iguais
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws StringVaziaException, SenhaPequenaException {

		if (senha.length() < 8) {
			throw new SenhaPequenaException("A senha nao pode ter menos que 8 digitos");
		}

		if (nome.isEmpty()) {
			throw new StringVaziaException("A senha nao pode ser vazia!");
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
			throw new StringVaziaException("Email invalido!");
		}

		if (validarEmail(email) == false) {
			throw new EmailInvalidoException("Email Invalido!");
		}

		this.email = email;

	}

	public void setFormulariosDoUsuario(ArrayList<Formulario> formulariosDoUsuario) {
		this.formulariosDoUsuario = formulariosDoUsuario;
	}

	public ArrayList<Formulario> getFormulariosDoUsuario() {
		return formulariosDoUsuario;
	}

	public ArrayList<Trajeto> getTrajetos() {
		return trajetos;
	}

	public void setTrajetos(ArrayList<Trajeto> trajetos) {
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







	
	public void favoritarENomear(Ponto ponto, String nomePonto) throws StatusInvalidoException, JsonMappingException, JsonProcessingException{
		PontoFavorito pontoFavorito = PontoFavorito.favoritarPontoENomear(ponto, nomePonto);
		addFavorito(pontoFavorito);
	}

	public void desfavoritar(PontoFavorito pontoFavorito){
		favoritos.remove(pontoFavorito);
	}
	
	public void removeFavorito(PontoFavorito pontoFavorito){
		favoritos.remove(pontoFavorito);
	}

	public void addFavorito(PontoFavorito pontoFavorito){
		favoritos.add(pontoFavorito);
	}

	public void addFormulario(Formulario formulario){
		formulariosDoUsuario.add(formulario);
	}

	public void remuveFormulario(Formulario formulario){
		formulariosDoUsuario.remove(formulario);
	}

	public void addTrajeto(Trajeto trajeto){
		trajetos.add(trajeto);
	}

	public void RemuveTrajeto(Trajeto trajeto){
		trajetos.remove(trajeto);
	}
}