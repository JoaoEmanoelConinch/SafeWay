package modelo.entidade.mapa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import modelo.consultaAPI.ConsultaPonto;
import modelo.entidade.formulario.Formulario;
import modelo.excecao.mapa.NumeroMaiorQueLimiteException;
import modelo.excecao.mapa.NumeroMenorQueZeroException;
import modelo.excecao.mapa.StatusInvalidoException;

@Entity
@Table(name = "ponto")
public class Ponto {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ponto", nullable = false, unique = true)
	private long id_ponto;
	 
	@Column(name = "latitude", nullable = false)
	@Type(type = "double")
	private double latitude;

	@Column(name = "longitude", nullable = false)
	@Type(type = "double")
	private double longitude;
	
	// @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	// @JoinTable(name = "ponto_trajeto", joinColumns = @JoinColumn(name = "id_ponto"))
	// @Fetch(FetchMode.JOIN)

	@ManyToMany(mappedBy = "pontos")
	private List<Trajeto> trajetos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idPonto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Formulario> avaliacoes;

	@Column(name = "quantidade_lesoes_corporais_ponto_avaliado", nullable = false)
	private long quantidadeLesoesCorporais;
	
	@Column(name = "quantidade_furtos_ponto_avaliado", nullable = false)
	private long quantidadeFurtos;

	@Column(name = "quantidade_roubos_ponto_avaliado", nullable = false)
	private long quantidadeRoubos;

	@Column(name = "quantidade_homicidios_ponto_avaliado", nullable = false)
	private long quantidadeHomicidios;

	@Column(name = "quantidade_latrocinio_ponto_avaliado", nullable = false)
	private long quantidadeLatrocinio;

	@Column(name = "nivel_Bloqueio_Ponto_Avaliado", nullable = false)
	private boolean bloqueio;

	@Column(name = "endereco", nullable = true, length = 100)
	private String endereco;
	
	public Ponto(long idPonto, double latitude, double longitude, List<Trajeto> trajetos, List<Formulario> avaliacoes) {
		this.setIdPonto(idPonto);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setTrajetos(trajetos);
		this.setAvaliacoes(avaliacoes);
		this.setEndereco(informarLatLong());
		
	}
	
	public Ponto(double latitude, double longitude) throws StatusInvalidoException {
		
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setTrajetos(new ArrayList<Trajeto>());
		this.setEndereco(informarLatLong());
	}

	
	public Ponto(long id) {
		setIdPonto(id);
	}
	
	public Ponto(){}

	public long getIdPonto() {
		return id_ponto;
	}
	
	public void setIdPonto(long idPonto) {
		this.id_ponto = idPonto;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
		;
	}

	public double getLongitude() {
		return this.longitude;

	}

	public void setTrajetos(List<Trajeto> trajetos) {
		this.trajetos = trajetos;
	}

	public List<Trajeto> getTrajetos() {
		return trajetos;

	}
	
	public List<Formulario> getAvaliacoes(){
		return avaliacoes;
	}
	
	public void setAvaliacoes(List<Formulario> avaliacoes) {
		this.avaliacoes = avaliacoes;
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

	public long getQuantidadeLesoesCorporais() {
		return quantidadeLesoesCorporais;
	}

	public void setQuantidadeLesoesCorporais(long quantidadeLezoesCorporais) {
		this.quantidadeLesoesCorporais = quantidadeLezoesCorporais;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEndereco() {
		return this.endereco;
	}
	
	public static Ponto informarLocal(String local) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		return ConsultaPonto.informarLocal(local);
	}

	public static Ponto informarLocal(String local, int posicao)
			throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		return ConsultaPonto.informarLocal(local, posicao);
	}

	public static List<Ponto> informarLocais(String local) throws StatusInvalidoException {
		return ConsultaPonto.informarLocais(local);
	}

	@Override
	public boolean equals(Object objeto) {
		
		if (this == objeto)
			return true;

		if (objeto == null)
			return false;

		if (getClass() != objeto.getClass())
			return false;
		
		Ponto ponto = (Ponto) objeto;
		
		if (!(this.getLatitude() == ponto.getLatitude())||!(this.getLongitude() == ponto.getLongitude())) {
			return false;
		}
		
		return true;
		
	}

	private void verificarBloqueio() {
		this.bloqueio = getAvaliacoes().get((getAvaliacoes().size()-1)).isBloqueioRuas();
	}

	public void addTrajeto(Trajeto trajeto) {
		trajetos.add(trajeto);
	}

	public void removeTrajeto(Trajeto trajeto) {
		trajetos.remove(trajeto);
	}
	
	
	// public void addAvaliacao(Formulario form) {
	// 	avaliacoes.add(form);
	// }

	// public void removeAvaliacao(Formulario form) {
	// 	avaliacoes.remove(form);
	// }

	public void addAvaliacao(Formulario avaliacao) throws NullPointerException {

		if (avaliacao == null) {
			throw new NullPointerException();
		}
		this.avaliacoes.add(avaliacao);
		
		if (avaliacao.isLesaoCorporal()){
			setQuantidadeLesoesCorporais(getQuantidadeLesoesCorporais()+1);
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

	}

	public void removeAvaliacao(Formulario avaliacao) throws NullPointerException {

		if (avaliacao == null) {
			throw new NullPointerException();
		}

		this.avaliacoes.remove(avaliacao);

		if (getAvaliacoes().size() > 0) {
			
			if (avaliacao.isLesaoCorporal()){
				setQuantidadeLesoesCorporais(getQuantidadeLesoesCorporais()-1);
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
		}
	}
  
  public List<Double> criarVetor(){
		List<Double> pontoVetor = new ArrayList<Double>(2);
		
		pontoVetor.add(this.getLongitude());
		pontoVetor.add(this.getLatitude());
		
		return pontoVetor;
	}

	public String informarLatLong() {
		return ConsultaPonto.informarLatLong(this);
	
}
}
