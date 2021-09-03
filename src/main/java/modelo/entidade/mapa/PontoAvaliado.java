package modelo.entidade.mapa;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import modelo.entidade.formulario.Formulario;
import modelo.excecao.mapa.StatusInvalidoException;

@Entity
@Table(name = "ponto_avaliado")
@PrimaryKeyJoinColumn(name="id_ponto")
public class PontoAvaliado extends Ponto{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_ponto", nullable = false, unique = true, columnDefinition = "UNSIGNED INT")
	private Long idPontoAvaliado;

	@OneToMany(
        mappedBy = "ponto_avaliado",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
	private ArrayList<Formulario> avaliacoes;

	@Column(name = "quantidade_lezoes_corporais_ponto-avaliado", nullable = false)
	private long quantidadeLezoesCorporais;
	
	@Column(name = "quantidade_furtos_ponto-avaliado", nullable = false)
	private long quantidadeFurtos;

	@Column(name = "quantidade_roubos_ponto-avaliado", nullable = false)
	private long quantidadeRoubos;

	@Column(name = "quantidade_homicidios_ponto-avaliado", nullable = false)
	private long quantidadeHomicidios;

	@Column(name = "quantidade_latrocinio_ponto-avaliado", nullable = false)
	private long quantidadeLatrocinio;

	@Column(name = "nivel_Bloqueio_Ponto_Avaliado", nullable = false)
	private boolean bloqueio;

	@Column(name = "media_Avaliacao_Ponto_Avaliado", nullable = false)
	private double mediaDeAvaliacao;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Ponto ponto;

	public PontoAvaliado() {}

	public PontoAvaliado(long idPontoAvaliado, Ponto ponto, ArrayList<Formulario> avaliacoes,
	long quantidadeLezoesCorporais, long quantidadeFurtos, long quantidadeRoubos, long quantidadeHomicidios,
	long quantidadeLatrocinio, boolean bloqueio, double mediaDeAvaliacao){
		
		setId(idPontoAvaliado);
		setPonto(ponto);
		setAvaliacoes(avaliacoes);
		setQuantidadeLezoesCorporais(quantidadeLezoesCorporais);
		setQuantidadeFurtos(quantidadeFurtos);
		setQuantidadeRoubos(quantidadeRoubos);
		setQuantidadeHomicidios(quantidadeHomicidios);
		setQuantidadeLatrocinio(quantidadeLatrocinio);
		setBloqueio(bloqueio);
		setMediaDeAvaliacao(mediaDeAvaliacao);
	}

	public PontoAvaliado(Ponto ponto, Formulario avaliacao){
		
		setPonto(ponto);
		addAvaliacao(avaliacao);
	}

	public PontoAvaliado(Ponto ponto){
		setPonto(ponto);
	}

	public Long getIdPontoAvaliado() {
		return idPontoAvaliado;
	}

	public void setIdPontoAvaliado(Long idPontoAvaliado) {
		this.idPontoAvaliado = idPontoAvaliado;
	}

	public long getQuantidadeLatrocinio() {
		return quantidadeLatrocinio;
	}

	public void setQuantidadeLatrocinio(long quantidadeLatrocinio) {
		this.quantidadeLatrocinio = quantidadeLatrocinio;
	}

	public long getQuantidadeHomicidios() {
		return quantidadeHomicidios;
	}

	public void setQuantidadeHomicidios(long quantidadeHomicidios) {
		this.quantidadeHomicidios = quantidadeHomicidios;
	}

	public long getQuantidadeRoubos() {
		return quantidadeRoubos;
	}

	public void setQuantidadeRoubos(long quantidadeRoubos) {
		this.quantidadeRoubos = quantidadeRoubos;
	}

	public long getQuantidadeFurtos() {
		return quantidadeFurtos;
	}

	public void setQuantidadeFurtos(long quantidadeFurtos) {
		this.quantidadeFurtos = quantidadeFurtos;
	}

	public long getQuantidadeLezoesCorporais() {
		return quantidadeLezoesCorporais;
	}

	public void setQuantidadeLezoesCorporais(long quantidadeLezoesCorporais) {
		this.quantidadeLezoesCorporais = quantidadeLezoesCorporais;
	}

	public double getMediaDeAvaliacao() {
		return mediaDeAvaliacao;
	}

	public void setMediaDeAvaliacao(double mediaDeAvaliacao) {
		this.mediaDeAvaliacao = mediaDeAvaliacao;
	}

	public ArrayList<Formulario> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(ArrayList<Formulario> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public void addAvaliacao(Formulario avaliacao) throws NullPointerException {

		if (avaliacao == null) {
			throw new NullPointerException();
		}
		this.avaliacoes.add(avaliacao);
		
		if (avaliacao.isLesaoCorporal()){
			setQuantidadeLezoesCorporais(getQuantidadeLezoesCorporais()+1);
		}
		if (avaliacao.isFurto()){
			setQuantidadeFurtos(getQuantidadeFurtos()+1);
		}
		if (avaliacao.isRoubo()){
			setQuantidadeRoubos(getQuantidadeRoubos()+1);
		}
		if (avaliacao.isHomicidio()){
			setQuantidadeHomicidios(getQuantidadeHomicidios()+1);
		}
		if (avaliacao.isLatrocinio()){
			setQuantidadeLatrocinio(getQuantidadeLatrocinio()+1);
		}
		this.verificarBloqueio();

		this.setMediaDeAvaliacao(calcularMediaPonto());
	}

	public void removeAvaliacao(Formulario avaliacao) throws NullPointerException {

		if (avaliacao == null) {
			throw new NullPointerException();
		}

		this.avaliacoes.remove(avaliacao);

		if (getAvaliacoes().size() > 0) {
			
			if (avaliacao.isLesaoCorporal()){
				setQuantidadeLezoesCorporais(getQuantidadeLezoesCorporais()-1);
			}
			if (avaliacao.isFurto()){
				setQuantidadeFurtos(getQuantidadeFurtos()-1);
			}
			if (avaliacao.isRoubo()){
				setQuantidadeRoubos(getQuantidadeRoubos()-1);
			}
			if (avaliacao.isHomicidio()){
				setQuantidadeHomicidios(getQuantidadeHomicidios()-1);
			}
			if (avaliacao.isLatrocinio()){
				setQuantidadeLatrocinio(getQuantidadeLatrocinio()-1);
			}
			this.verificarBloqueio();

			this.setMediaDeAvaliacao(calcularMediaPonto());
		}
	}

	public boolean isBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(boolean bloqueio) {
		this.bloqueio = bloqueio;
	}

	private void verificarBloqueio() {
		this.bloqueio = getAvaliacoes().get(getAvaliacoes().size()).isBloqueioRuas();
	}

	public Ponto getPonto() {
		return ponto;
	}

	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}

	private double calcularMediaPonto(){
		long media = 0;
		ArrayList<Formulario> avaliacoesList = this.getAvaliacoes();

		for (int i = 0; i < avaliacoesList.size(); i++){
			media += avaliacoesList.get(i).getMedia();
		}
		return media/avaliacoesList.size();
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
