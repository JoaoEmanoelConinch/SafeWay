package modelo.entidade.mapa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import modelo.entidade.formulario.Formulario;
import modelo.enumeracao.mapa.NivelBloqueio;
import modelo.excecao.mapa.StatusInvalidoException;

@Entity
@Table(name = "ponto_avaliado")
@PrimaryKeyJoinColumn(name="id_ponto")
public class PontoAvaliado extends Ponto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_ponto", nullable = false, unique = true, columnDefinition = "UNSIGNED INT")
	private Long idPontoAvaliado;

	private List<Formulario> avaliacoes;

	@Column(name = "nivel_Criminalidade_Ponto_Avaliado", precision = 4, scale = 2, nullable = false)
	private double nivelDeCriminalidade;

	@Column(name = "nivel_Estrutura_Ponto_Avaliado", nullable = false, columnDefinition = "UNSIGNED INT")
	private int nivelDeEstruturaDaRua;

	@Column(name = "nivel_Iluminacao_Ponto_Avaliado", nullable = false)
	private int nivelDeIluminacao;

	@Column(name = "nivel_Trasito_Ponto_Avaliado", nullable = false)
	private int nivelDeTransito;

	@Column(name = "nivel_Bloqueio_Ponto_Avaliado", nullable = false)
	@Enumerated(EnumType.STRING)
	private NivelBloqueio bloqueio;

	@Column(name = "media_Avaliacao_Ponto_Avaliado", nullable = false)
	private int mediaDeAvaliacao;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "id_cliente")
	private Ponto ponto;

	public PontoAvaliado() {
	}

	public PontoAvaliado(double latitude, double longitude, Long idPontoAvaliado, List<Formulario> avaliacoes,
			double nivelDeCriminalidade, int nivelDeEstruturaDaRua, int nivelDeIluminacao, int nivelDeTransito,
			NivelBloqueio bloqueio, int mediaDeAvaliacao) throws StatusInvalidoException, JsonMappingException, JsonProcessingException {
		super(latitude, longitude);
		this.setIdPontoAvaliado(idPontoAvaliado);
		this.setAvaliacoes(avaliacoes);
		this.setNivelDeCriminalidade();
		this.setNivelDeEstruturaDaRua();
		this.setNivelDeIluminacao();
		this.setNivelDeTransito();
		this.setBloqueio();
		this.setMediaDeAvaliacao();

	}

	public PontoAvaliado(double latitude, double longitude, List<Formulario> avaliacoes, double nivelDeCriminalidade,
			int nivelDeEstruturaDaRua, int nivelDeIluminacao, int nivelDeTransito, NivelBloqueio bloqueio,
			int mediaDeAvaliacao) throws StatusInvalidoException, JsonMappingException, JsonProcessingException {
		super(latitude, longitude);
		this.setAvaliacoes(avaliacoes);
		this.setNivelDeCriminalidade();
		this.setNivelDeEstruturaDaRua();
		this.setNivelDeIluminacao();
		this.setNivelDeTransito();
		this.setBloqueio();
		this.setMediaDeAvaliacao();
	}

	public PontoAvaliado(Long idPontoAvaliado, Ponto ponto, Formulario avaliacao)
			throws StatusInvalidoException, NullPointerException, JsonMappingException, JsonProcessingException {
		super(ponto.getLatitude(), ponto.getLatitude());

		this.setIdPontoAvaliado(idPontoAvaliado);
		this.addAvaliacao(avaliacao);

	}

	public PontoAvaliado(Ponto ponto, Formulario avaliacao) throws StatusInvalidoException, NullPointerException, JsonMappingException, JsonProcessingException {
		super(ponto.getLatitude(), ponto.getLatitude());
		this.addAvaliacao(avaliacao);

	}

	public PontoAvaliado(Ponto ponto) throws StatusInvalidoException, NullPointerException, JsonMappingException, JsonProcessingException {
		super(ponto.getLatitude(), ponto.getLatitude());
	}

	public Long getIdPontoAvaliado() {
		return idPontoAvaliado;
	}

	public void setIdPontoAvaliado(Long idPontoAvaliado) {
		this.idPontoAvaliado = idPontoAvaliado;
	}

	public int getMediaDeAvaliacao() {
		return mediaDeAvaliacao;
	}

	private void setMediaDeAvaliacao() {
		double soma = 0;
		for (Formulario formulario : getAvaliacoes()) {
			soma += formulario.getMedia();
		}
		this.nivelDeCriminalidade = soma / getAvaliacoes().size();
	}

	public List<Formulario> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Formulario> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public void addAvaliacao(Formulario avaliacao) throws NullPointerException {

		if (avaliacao == null) {
			throw new NullPointerException();
		}

		this.avaliacoes.add(avaliacao);
		this.setNivelDeCriminalidade();
		this.setNivelDeEstruturaDaRua();
		this.setNivelDeIluminacao();
		this.setBloqueio();
		this.setNivelDeTransito();
		this.setMediaDeAvaliacao();
	}

	public void removeAvaliacao(Formulario avaliacao) throws NullPointerException {

		if (avaliacao == null) {
			throw new NullPointerException();
		}

		this.avaliacoes.remove(avaliacao);

		if (getAvaliacoes().size() > 0) {

			this.setNivelDeCriminalidade();
			this.setNivelDeEstruturaDaRua();
			this.setNivelDeIluminacao();
			this.setBloqueio();
			this.setNivelDeTransito();
			this.setMediaDeAvaliacao();

		}
	}

	public double getNivelDeCriminalidade() {
		return nivelDeCriminalidade;
	}

	private void setNivelDeCriminalidade() {

		double soma = 0;
		for (Formulario formulario : getAvaliacoes()) {
			soma += formulario.getOcorrencia().getPeso();
		}
		this.nivelDeCriminalidade = soma / getAvaliacoes().size();
	}

	public int getNivelDeEstruturaDaRua() {
		return nivelDeEstruturaDaRua;
	}

	private void setNivelDeEstruturaDaRua() {
		double soma = 0;
		for (Formulario formulario : getAvaliacoes()) {
			soma += formulario.getNivelEstrutura().getPeso();
		}
		this.nivelDeCriminalidade = soma / getAvaliacoes().size();
	}

	public int getNivelDeIluminacao() {
		return nivelDeIluminacao;
	}

	private void setNivelDeIluminacao() {

		double soma = 0;
		for (Formulario formulario : getAvaliacoes()) {
			soma += formulario.getNivelIluminacao().getPeso();
		}
		this.nivelDeCriminalidade = soma / getAvaliacoes().size();
	}

	public NivelBloqueio getBloqueio() {
		return bloqueio;
	}

	private void setBloqueio() {
		this.bloqueio = getAvaliacoes().get(getAvaliacoes().size()).getNivelBloqueio();
	}

	public int getNivelDeTransito() {
		return nivelDeTransito;
	}

	public Ponto getPonto() {
		return ponto;

	}

	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}

	private void setNivelDeTransito() {

		double soma = 0;
		for (Formulario formulario : getAvaliacoes()) {
			soma += formulario.getNivelTransito().getPeso();
		}
		this.nivelDeCriminalidade = soma / getAvaliacoes().size();
	}

	public static PontoAvaliado CriarPonto(Ponto ponto, Formulario avaliacao)
			throws NullPointerException, StatusInvalidoException, JsonMappingException, JsonProcessingException {

		PontoAvaliado pontoAvaliado = new PontoAvaliado(ponto, avaliacao);

		return pontoAvaliado;
	}

	public static PontoAvaliado criarPontoAvaliado(Ponto ponto)
			throws NullPointerException, StatusInvalidoException, JsonMappingException, JsonProcessingException {

		PontoAvaliado pontoAvaliado = new PontoAvaliado(ponto);

		return pontoAvaliado;
	}
}
