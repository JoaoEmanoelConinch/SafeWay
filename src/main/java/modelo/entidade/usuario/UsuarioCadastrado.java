package modelo.entidade.usuario;

import java.io.IOException;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import modelo.entidade.formulario.Formulario;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.Trajeto;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.NumeroMaiorQueLimiteException;
import modelo.excecao.mapa.NumeroMenorQueZeroException;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

@Entity
@Table(name = "UsuarioCadastrado")
public class UsuarioCadastrado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false, unique = true)
    private long id;
	
	@Column(name = "nome_usuario", length = 45, nullable = false, unique = true)
	private String nome;

	@Column(name = "senha_usuario", length = 45, nullable = false)
	private String senha;

	@Column(name = "email_usuario", length = 45, nullable = false, unique = true)
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Formulario> formulariosDoUsuario;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "usuario_trajeto",
	joinColumns = @JoinColumn(name = "id_usuario"),
	inverseJoinColumns = @JoinColumn(name = "id_trajeto")
	)
	private List<Trajeto> trajetos;

	public UsuarioCadastrado() {
	}
	
	public UsuarioCadastrado(long id) {
		this.setId(id);
	}

	public UsuarioCadastrado(String email, String senha) throws StringVaziaException, SenhaPequenaException, EmailInvalidoException {
		
		this.setEmail(email);
		this.setSenha(senha);
		
	}

	public UsuarioCadastrado(long idUsuario, String nome, String senha, String email, List<Formulario> formulariosDoUsuario, List<Trajeto> trajetos)
			throws StringVaziaException, EmailInvalidoException, SenhaPequenaException {
		this.setId(idUsuario);
		this.setNome(nome);
		this.setSenha(senha);
		this.setEmail(email);
		this.setFormulariosDoUsuario(formulariosDoUsuario);
		this.setTrajetos(trajetos);
	}

	public UsuarioCadastrado(long id, String nome, String senha, String email) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException{
		this.setId(id);
		this.setNome(nome);
		this.setEmail(email);
		this.setSenha(senha);
		
	}

	public UsuarioCadastrado(String nome, String senha, String email)
			throws StringVaziaException, EmailInvalidoException, SenhaPequenaException {
		
		this.setNome(nome);
		this.setSenha(senha);
		this.setEmail(email);
		this.setFormulariosDoUsuario(new ArrayList<Formulario>());
		this.setTrajetos(new ArrayList<Trajeto>());
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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

		if (senha.isEmpty()) {
			throw new StringVaziaException("A senha n�o pode ser vazia!");
		}

		this.senha = senha;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws EmailInvalidoException, StringVaziaException {

		if (email.isEmpty()) {
			throw new StringVaziaException("O nome de Usu�rio � inv�lido!");
		}

		if (validarEmail(email) == false) {
			throw new EmailInvalidoException("Email Inv�lido!");
		}

		this.email = email;

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

	public Trajeto trajeto(Ponto inicio, Ponto chegada, MeioDeTransporte transporteUsado) throws JsonParseException, JsonMappingException, IOException, StatusInvalidoException {


		Trajeto trajeto = new Trajeto(inicio, chegada, transporteUsado);

		this.addTrajeto(trajeto);
		trajeto.addUsuarioCadastrado(this);

		return trajeto;

	}

	public Formulario avaliacao(boolean lesaoCorporal, boolean furto, boolean roubo, boolean homicidio, boolean latrocinio,
			boolean bloqueioRuas, String comentario, Ponto Ponto, UsuarioCadastrado idUsuario)
			throws NullPointerException, StatusInvalidoException{

		Formulario formulario = new Formulario(lesaoCorporal, furto, roubo, homicidio, latrocinio, comentario, bloqueioRuas, Ponto, idUsuario);

		this.addFormulario(formulario);
		Ponto.addAvaliacao(formulario);

		return formulario;

	}

	public void addFormulario(Formulario formulario) {
		formulariosDoUsuario.add(formulario);
	}

	public void removerFormulario(Formulario formulario) {

		formulariosDoUsuario.remove(formulario);
	}

	public void addTrajeto(Trajeto trajeto) {
		trajetos.add(trajeto);
	}

	public void removerTrajeto(Trajeto trajeto) {
		trajetos.remove(trajeto);
	}
	
	public static List<Ponto> informarLocais(String local){
		return Ponto.informarLocais(local);
	} 

	public Ponto DefinirLocal (String local) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException{
		return Ponto.informarLocal(local);
	}

	public Ponto DefinirLocal (String local, int posicao) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException{
		return Ponto.informarLocal(local, posicao);
	}
	
	public Ponto DefinirPartida(String inicio) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		return DefinirLocal(inicio);
	}

	public Ponto DefinirDestino(String chegada) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		return DefinirLocal(chegada);
	}

	public Ponto DefinirPartida(String inicio, int posicao) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		return DefinirLocal(inicio, posicao);
	}

	public Ponto DefinirDestino(String chegada, int posicao) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		return DefinirLocal(chegada, posicao);
	}

	public MeioDeTransporte DefinirTransporte(MeioDeTransporte transporte) {
		return transporte;
	}

	public Trajeto trajeto(String inicio, String chegada, MeioDeTransporte transporte)
			throws JsonParseException, JsonMappingException, StatusInvalidoException, IOException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException{

		return new Trajeto(DefinirPartida(inicio), DefinirDestino(chegada), DefinirTransporte(transporte));
	}

	public Trajeto trajeto(String inicio, int posicaoInicio, String chegada, int posicaoChegada, MeioDeTransporte transporte)
			throws JsonParseException, JsonMappingException, StatusInvalidoException, IOException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException{

		return new Trajeto(DefinirPartida(inicio, posicaoInicio), DefinirDestino(chegada, posicaoChegada),DefinirTransporte(transporte));
	}
	
}